package elo7challenge.transfersystem.console;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import elo7challenge.transfersystem.domain.FinancialTransfer;
import elo7challenge.transfersystem.domain.FinancialTransferType;

public class TransferConverterTest {

	private TransferConverter converter;
	
	@Before
	public void setUp() {
		this.converter = new TransferConverter();
	}
	
	@Test
	public void shouldConvertReadTransferToFinancialTransfer() throws ParseException {
		Map<String, String> readTransfer = this.buildDefaultReadTransfer();

		TransferConverterResult converterResult = converter.convertToTransfer(readTransfer);
		FinancialTransfer transfer = converterResult.getConverted();
	
		assertTrue(converterResult.isSuccess());
		assertEquals("01212-3", transfer.getSenderAccount());
		assertEquals("45454-6", transfer.getRecipientAccount());
		assertEquals(new BigDecimal("120.99"), transfer.getValue());
		assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2020"), transfer.getScheduledDate());
		assertEquals(FinancialTransferType.B, transfer.getType());
	}
	
	@Test
	public void shouldHaveErrorsWhenSenderAccountIsEmpty() throws ParseException {
		Map<String, String> readTransfer = this.buildDefaultReadTransfer();
		readTransfer.put("senderAccount", "");
		
		TransferConverterResult converterResult = converter.convertToTransfer(readTransfer);
		List<ErrorMessage> errors = converterResult.getErrors();
		
		assertFalse(converterResult.isSuccess());
		assertEquals("senderAccount", errors.get(0).getField());
		assertEquals("invalid value []", errors.get(0).getMessage());
	}

	@Test
	public void shouldHaveErrorsWhenRecipientAccountIsEmpty() throws ParseException {
		Map<String, String> readTransfer = this.buildDefaultReadTransfer();
		readTransfer.put("recipientAccount", "");
		
		TransferConverterResult converterResult = converter.convertToTransfer(readTransfer);
		List<ErrorMessage> errors = converterResult.getErrors();
		
		assertFalse(converterResult.isSuccess());
		assertEquals("recipientAccount", errors.get(0).getField());
		assertEquals("invalid value []", errors.get(0).getMessage());
	}

	@Test
	public void shouldHaveErrorsWhenValueIsInvalid() throws ParseException {
		Map<String, String> readTransfer = this.buildDefaultReadTransfer();
		readTransfer.put("value", "xpto");
		
		TransferConverterResult converterResult = converter.convertToTransfer(readTransfer);
		List<ErrorMessage> errors = converterResult.getErrors();
		
		assertFalse(converterResult.isSuccess());
		assertEquals("value", errors.get(0).getField());
		assertEquals("invalid value [xpto]", errors.get(0).getMessage());
	}

	@Test
	public void shouldHaveErrorsWhenScheduledDateIsInvalid() throws ParseException {
		Map<String, String> readTransfer = this.buildDefaultReadTransfer();
		readTransfer.put("scheduledDate", "1234-56-78");
		
		TransferConverterResult converterResult = converter.convertToTransfer(readTransfer);
		List<ErrorMessage> errors = converterResult.getErrors();
		
		assertFalse(converterResult.isSuccess());
		assertEquals("scheduledDate", errors.get(0).getField());
		assertEquals("invalid value [1234-56-78]", errors.get(0).getMessage());
	}

	@Test
	public void shouldHaveErrorsWhenTypeIsNonExistant() throws ParseException {
		Map<String, String> readTransfer = this.buildDefaultReadTransfer();
		readTransfer.put("type", "ZZ");
		
		TransferConverterResult converterResult = converter.convertToTransfer(readTransfer);
		List<ErrorMessage> errors = converterResult.getErrors();
		
		assertFalse(converterResult.isSuccess());
		assertEquals("type", errors.get(0).getField());
		assertEquals("invalid value [ZZ]", errors.get(0).getMessage());
	}

	private Map<String, String> buildDefaultReadTransfer() {
		Map<String, String> readTransfer = new HashMap<String, String>();
		readTransfer.put("senderAccount", "01212-3");
		readTransfer.put("recipientAccount", "45454-6");
		readTransfer.put("value", "120.99");
		readTransfer.put("scheduledDate", "21/07/2020");
		readTransfer.put("type", "B");

		return readTransfer;
	}
}
