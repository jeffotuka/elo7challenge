package elo7challenge.transfersystem.console;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import elo7challenge.transfersystem.domain.FinancialTransfer;
import elo7challenge.transfersystem.domain.FinancialTransferType;

public class TransferConverter {

	public TransferConverterResult convertToTransfer(Map<String, String> readTransfer) {
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
		
		TransferConverterResult converterResult = new TransferConverterResult();
		converterResult.setConverted(transfer);
		return converterResult;
	}
	
}
