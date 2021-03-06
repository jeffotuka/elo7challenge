package elo7challenge.transfersystem.dao.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import elo7challenge.transfersystem.dao.DAOException;
import elo7challenge.transfersystem.dao.FinancialTransferDAO;
import elo7challenge.transfersystem.db.DBTransferSetup;
import elo7challenge.transfersystem.domain.FinancialTransfer;
import elo7challenge.transfersystem.domain.FinancialTransferType;

public class FinancialTransferSQLDAOTest {

	private FinancialTransferDAO dao;
	private Connection connection;
	
	@Before
	public void setUp() throws SQLException {
		// database setup
		this.connection = DriverManager.getConnection("jdbc:hsqldb:mem:db/temptesting;shutdown=true", "SA", "");;
		DBTransferSetup dbTransferSetup = new DBTransferSetup(connection);
		dbTransferSetup.execute();
				
		this.dao = new FinancialTransferSQLDAO(connection);
	}
	
	@After
	public void tearDown() throws SQLException {
		connection.close();
	}
	
	@Test
	public void shouldReturnListWithOneTransfer() throws SQLException, ParseException {		
		FinancialTransfer transferToCreate = new FinancialTransfer();
		transferToCreate.setSenderAccount("01212-3");
		transferToCreate.setRecipientAccount("45454-6");
		transferToCreate.setValue(new BigDecimal("120.99"));
		transferToCreate.setTax(new BigDecimal("15.67"));
		transferToCreate.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2020"));
		transferToCreate.setType(FinancialTransferType.B);
		
		dao.save(transferToCreate);
		List<FinancialTransfer> list = dao.list();
		
		assertEquals(1, list.size());
		FinancialTransfer transferSelected = list.get(0);
		assertEquals(transferToCreate.getSenderAccount(), transferSelected.getSenderAccount());
		assertEquals(transferToCreate.getRecipientAccount(), transferSelected.getRecipientAccount());
		assertEquals(transferToCreate.getValue(), transferSelected.getValue());
		assertEquals(transferToCreate.getTax(), transferSelected.getTax());
		assertEquals(transferToCreate.getScheduledDate(), transferSelected.getScheduledDate());
		assertEquals(transferToCreate.getType(), transferSelected.getType());
	}

	@Test
	public void shouldReturnListWithNoTransfer() throws SQLException, ParseException {		
		List<FinancialTransfer> list = dao.list();
		
		assertEquals(0, list.size());
	}

	@Test
	public void shouldReturnListWithOneMinimalTransfer() throws SQLException, ParseException {		
		FinancialTransfer transferToCreate = new FinancialTransfer();
		transferToCreate.setSenderAccount("01212-3");
		transferToCreate.setRecipientAccount("45454-6");
		transferToCreate.setValue(new BigDecimal("120.99"));
		transferToCreate.setTax(null);
		transferToCreate.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2020"));
		transferToCreate.setType(null);
		
		dao.save(transferToCreate);
		List<FinancialTransfer> list = dao.list();
		
		assertEquals(1, list.size());
		FinancialTransfer transferSelected = list.get(0);
		assertEquals(transferToCreate.getSenderAccount(), transferSelected.getSenderAccount());
		assertEquals(transferToCreate.getRecipientAccount(), transferSelected.getRecipientAccount());
		assertEquals(transferToCreate.getValue(), transferSelected.getValue());
		assertNull(transferSelected.getTax());
		assertEquals(transferToCreate.getScheduledDate(), transferSelected.getScheduledDate());
		assertNull(transferSelected.getType());
	}

	@Test
	public void shouldReturnListWithMultipleTransfers() throws ParseException {
		FinancialTransfer transferToCreate1 = new FinancialTransfer();
		transferToCreate1.setSenderAccount("01212-3");
		transferToCreate1.setRecipientAccount("45454-6");
		transferToCreate1.setValue(new BigDecimal("120.99"));
		transferToCreate1.setTax(new BigDecimal("15.67"));
		transferToCreate1.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2020"));
		transferToCreate1.setType(FinancialTransferType.B);

		FinancialTransfer transferToCreate2 = new FinancialTransfer();
		transferToCreate2.setSenderAccount("00000-0");
		transferToCreate2.setRecipientAccount("99999-9");
		transferToCreate2.setValue(new BigDecimal("400300200100.00"));
		transferToCreate2.setTax(new BigDecimal("987654321.98"));
		transferToCreate2.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020"));
		transferToCreate2.setType(FinancialTransferType.D);

		FinancialTransfer transferToCreate3 = new FinancialTransfer();
		transferToCreate3.setSenderAccount("78787-9");
		transferToCreate3.setRecipientAccount("60840-2");
		transferToCreate3.setValue(new BigDecimal("0.01"));
		transferToCreate3.setTax(null);
		transferToCreate3.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("29/02/2020"));
		transferToCreate3.setType(null);

		List<FinancialTransfer> transferToCreateList = Arrays.asList(transferToCreate1, transferToCreate2, transferToCreate3);
		
		for (FinancialTransfer transferToCreate : transferToCreateList) {
			dao.save(transferToCreate);
		}
		
		List<FinancialTransfer> list = dao.list();
		assertEquals(3, list.size());
		
		for (int i = 0; i < list.size(); i++) {
			FinancialTransfer transferToCreate = transferToCreateList.get(i);
			FinancialTransfer transferSelected = list.get(i);

			assertEquals(transferToCreate.getSenderAccount(), transferSelected.getSenderAccount());
			assertEquals(transferToCreate.getRecipientAccount(), transferSelected.getRecipientAccount());
			assertEquals(transferToCreate.getValue(), transferSelected.getValue());
			assertEquals(transferToCreate.getTax(), transferSelected.getTax());
			assertEquals(transferToCreate.getScheduledDate(), transferSelected.getScheduledDate());
			assertEquals(transferToCreate.getType(), transferSelected.getType());
		}
	}

	@Test(expected=DAOException.class)
	public void shouldThrowExceptionWhenFailingToSave() throws ParseException, SQLException {
		FinancialTransfer transferToCreate = new FinancialTransfer();
		transferToCreate.setSenderAccount("01212-3");
		transferToCreate.setRecipientAccount("45454-6");
		transferToCreate.setValue(new BigDecimal("120.99"));
		transferToCreate.setTax(null);
		transferToCreate.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2020"));
		transferToCreate.setType(null);

		this.connection.close();
		this.connection = Mockito.mock(Connection.class);
		when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
		this.dao = new FinancialTransferSQLDAO(connection);
		
		dao.save(transferToCreate);
	}

	@Test(expected=DAOException.class)
	public void shouldThrowExceptionWhenFailingToList() throws SQLException {
		this.connection.close();
		this.connection = Mockito.mock(Connection.class);
		when(connection.prepareStatement(anyString())).thenThrow(new SQLException());
		this.dao = new FinancialTransferSQLDAO(connection);

		dao.list();
	}

}
