package elo7challenge.transfersystem.console;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import elo7challenge.transfersystem.domain.FinancialTransfer;
import elo7challenge.transfersystem.domain.FinancialTransferType;


public class TransferDisplayTest {

	private TransferDisplay display;
	private OutputStream outputStream;
	
	@Before
	public void setUp() {
		this.outputStream = new ByteArrayOutputStream();
		PrintWriter printWriter = new PrintWriter(this.outputStream);
		this.display = new TransferDisplay(printWriter);
	}
	
	@Test
	public void shouldPrintOneTransfer() throws ParseException {
		FinancialTransfer transfer = new FinancialTransfer();
		transfer.setId(5L);
		transfer.setSenderAccount("01212-3");
		transfer.setRecipientAccount("45454-6");
		transfer.setValue(new BigDecimal("120.99"));
		transfer.setTax(new BigDecimal("15.67"));
		transfer.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2020"));
		transfer.setType(FinancialTransferType.B);

		List<FinancialTransfer> transfers = Arrays.asList(transfer);
		display.printAllTransfers(transfers);
		
		assertEquals("\nid 5, sender 01212-3, recipient 45454-6, value 120.99, tax 15.67, date 21/07/2020, type B\n", outputStream.toString());
	}

	@Test
	public void shouldPrintNothingWhenThereAreNoTransfers() throws ParseException {
		List<FinancialTransfer> transfers = new ArrayList<FinancialTransfer>();
		display.printAllTransfers(transfers);
		
		assertEquals("\n", outputStream.toString());
	}

	@Test
	public void shouldPrintMultipleTransfers() throws ParseException, IOException {
		FinancialTransfer transfer1 = new FinancialTransfer();
		transfer1.setId(5L);
		transfer1.setSenderAccount("01212-3");
		transfer1.setRecipientAccount("45454-6");
		transfer1.setValue(new BigDecimal("120.99"));
		transfer1.setTax(new BigDecimal("15.67"));
		transfer1.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2020"));
		transfer1.setType(FinancialTransferType.B);

		FinancialTransfer transfer2 = new FinancialTransfer();
		transfer2.setId(6L);
		transfer2.setSenderAccount("00000-0");
		transfer2.setRecipientAccount("99999-9");
		transfer2.setValue(new BigDecimal("400300200100.00"));
		transfer2.setTax(new BigDecimal("987654321.98"));
		transfer2.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020"));
		transfer2.setType(FinancialTransferType.D);

		FinancialTransfer transfer3 = new FinancialTransfer();
		transfer3.setId(7L);
		transfer3.setSenderAccount("78787-9");
		transfer3.setRecipientAccount("60840-2");
		transfer3.setValue(new BigDecimal("0.01"));
		transfer3.setTax(null);
		transfer3.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse("29/02/2020"));
		transfer3.setType(null);

		List<FinancialTransfer> transfers = Arrays.asList(transfer1, transfer2, transfer3);
		display.printAllTransfers(transfers);

		BufferedReader reader = new BufferedReader(new StringReader(outputStream.toString()));
		reader.readLine();
		assertEquals("id 5, sender 01212-3, recipient 45454-6, value 120.99, tax 15.67, date 21/07/2020, type B", reader.readLine());
		assertEquals("id 6, sender 00000-0, recipient 99999-9, value 400300200100.00, tax 987654321.98, date 31/12/2020, type D", reader.readLine());
		assertEquals("id 7, sender 78787-9, recipient 60840-2, value 0.01, tax null, date 29/02/2020, type null", reader.readLine());
	}

	@Test
	public void shouldPrintAString() {
		String string = "this is a string";
		
		display.print(string);
		
		assertEquals(string, outputStream.toString());
	}
	
	@Test
	public void shouldPrintAnErrorMessage() throws IOException {
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
		errors.add(new ErrorMessage("value", "invalid value [xpto]"));
		
		display.printErrorMessages(errors);
		
		BufferedReader reader = new BufferedReader(new StringReader(outputStream.toString()));
		reader.readLine();
		assertEquals("value" + ": " + "invalid value [xpto]", reader.readLine());
	}

	@Test
	public void shouldPrintMultipleErrorMessages() throws IOException {
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
		errors.add(new ErrorMessage("senderAccount", "invalid value []"));
		errors.add(new ErrorMessage("recipientAccount", "invalid value []"));
		errors.add(new ErrorMessage("value", "invalid value [xpto]"));
		errors.add(new ErrorMessage("scheduledDate", "invalid value [1234-56-78]"));
		errors.add(new ErrorMessage("type", "invalid value [ZZ]"));

		display.printErrorMessages(errors);
		
		BufferedReader reader = new BufferedReader(new StringReader(outputStream.toString()));
		reader.readLine();
		assertEquals("senderAccount" + ": " + "invalid value []", reader.readLine());
		assertEquals("recipientAccount" + ": " + "invalid value []", reader.readLine());
		assertEquals("value" + ": " + "invalid value [xpto]", reader.readLine());
		assertEquals("scheduledDate" + ": " + "invalid value [1234-56-78]", reader.readLine());
		assertEquals("type" + ": " + "invalid value [ZZ]", reader.readLine());
	}

}
