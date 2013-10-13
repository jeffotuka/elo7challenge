package elo7challenge.transfersystem.console;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import elo7challenge.transfersystem.domain.FinancialTransfer;
import elo7challenge.transfersystem.domain.FinancialTransferType;

public class TransferConverter {

	public FinancialTransfer convertToTransfer(Map<String, String> readTransfer) {
		FinancialTransfer transfer = new FinancialTransfer();
		try {
			transfer.setSenderAccount(readTransfer.get("senderAccount"));
			transfer.setRecipientAccount(readTransfer.get("recipientAccount"));
			transfer.setValue(new BigDecimal(readTransfer.get("value")));
			transfer.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse(readTransfer.get("scheduledDate")));
			transfer.setType(FinancialTransferType.valueOfSafe(readTransfer.get("type")));
			
		} catch (ParseException e) {
			throw new IllegalArgumentException("tried to convert an invalid input of transfer data: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("tried to convert an invalid input of transfer data: " + e.getMessage());
		}
		
		return transfer;
	}

	public boolean tryConvertToTransfer(Map<String, String> readTransfer) {
		try {
			this.convertToTransfer(readTransfer);
			return true;
			
		} catch (RuntimeException e) {
			return false;
		}
	}

	public Map<String, String> detectConvertToTransferErrors(Map<String, String> readTransfer) {
		Map<String, String> fieldsWithErrors = new HashMap<String, String>();

		try {
			new BigDecimal(readTransfer.get("value"));
		} catch (NumberFormatException e) {
			fieldsWithErrors.put("value", readTransfer.get("value"));
		}
		
		try {
			new SimpleDateFormat("dd/MM/yyyy").parse(readTransfer.get("scheduledDate"));
		} catch (ParseException e) {
			fieldsWithErrors.put("scheduledDate", readTransfer.get("scheduledDate"));
		}

		try {
			FinancialTransferType.valueOf(readTransfer.get("type"));
		} catch (IllegalArgumentException e) {
			fieldsWithErrors.put("type", readTransfer.get("type"));
		}
		
		return fieldsWithErrors;
	}
	
}
