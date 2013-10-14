package elo7challenge.transfersystem.transfer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import elo7challenge.transfersystem.dao.FinancialTransferDAO;
import elo7challenge.transfersystem.domain.FinancialTransfer;
import elo7challenge.transfersystem.domain.FinancialTransferType;

@RunWith(MockitoJUnitRunner.class)
public class TransferManagerTest {

	@InjectMocks
	private TransferManager manager;

	public @Captor ArgumentCaptor<FinancialTransfer> transferCaptor;
	
	private @Mock FinancialTransferDAO transferDao;
	private @Mock TaxCalculatorFactory calculatorFactory;
	
	@Before
	public void setUp() {
		TaxCalculator calculator = Mockito.mock(TaxCalculator.class);
		when(calculatorFactory.build(any(FinancialTransferType.class), any(BigDecimal.class))).thenReturn(calculator);
		when(calculator.calculate(any(BigDecimal.class), any(Date.class))).thenReturn(new BigDecimal("0.00"));
	}
	
	@Test
	public void shouldGetListOfTransfers() {
		List<FinancialTransfer> listExpected = new ArrayList<FinancialTransfer>();
		
		when(transferDao.list()).thenReturn(listExpected);
		
		List<FinancialTransfer> list = manager.getAllTransfers();
		
		assertSame(listExpected, list);
	}

	@Test
	public void shouldSaveATransfer() {
		FinancialTransfer transfer = new FinancialTransfer();
		
		manager.saveTransfer(transfer);
		
		verify(transferDao).save(transfer);
	}

	@Test
	public void shouldIncludeTaxWhenSavingATransfer() throws ParseException {
		FinancialTransfer transfer = new FinancialTransfer();
		transfer.setSenderAccount("01212-3");
		transfer.setRecipientAccount("45454-6");
		transfer.setValue(new BigDecimal("120.99"));
		transfer.setTax(null);
		transfer.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2020"));
		transfer.setType(FinancialTransferType.B);

		BigDecimal taxExpected = new BigDecimal("111.22");

		TaxCalculator calculator = Mockito.mock(TaxCalculator.class);
		when(calculatorFactory.build(transfer.getType(), transfer.getValue())).thenReturn(calculator);
		when(calculator.calculate(transfer.getValue(), transfer.getScheduledDate())).thenReturn(taxExpected);

		manager.saveTransfer(transfer);
		
		verify(transferDao).save(transferCaptor.capture());
		FinancialTransfer transferSaved = transferCaptor.getValue();
		
		assertSame(transfer, transferSaved);
		assertEquals(taxExpected, transferSaved.getTax());
	}
}
