package elo7challenge.transfersystem.console;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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

		FinancialTransfer transfer = converter.convertToTransfer(readTransfer);
		
		assertEquals("01212-3", transfer.getSenderAccount());
		assertEquals("45454-6", transfer.getRecipientAccount());
		assertEquals(new BigDecimal("120.99"), transfer.getValue());
		assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2020"), transfer.getScheduledDate());
		assertEquals(FinancialTransferType.B, transfer.getType());
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionWhenConversionDataHasInvalidScheduledDate() {
		Map<String, String> readTransfer = this.buildDefaultReadTransfer();
		readTransfer.put("scheduledDate", "abcd-ef-gh");
		
		converter.convertToTransfer(readTransfer);
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionWhenConversionDataHasInvalidType() {
		Map<String, String> readTransfer = this.buildDefaultReadTransfer();
		readTransfer.put("type", "ZZ");
		
		converter.convertToTransfer(readTransfer);
	}

	@Test
	public void shouldReturnTrueWhenConversionDataIsValid() {
		Map<String, String> readTransfer = this.buildDefaultReadTransfer();
		
		assertTrue(converter.tryConvertToTransfer(readTransfer));
	}

	@Test
	public void shouldReturnFalseWhenConversionDataIsInvalid() {
		Map<String, String> readTransfer = this.buildDefaultReadTransfer();
		readTransfer.put("scheduledDate", "abcd-ef-gh");
		
		assertFalse(converter.tryConvertToTransfer(readTransfer));
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
