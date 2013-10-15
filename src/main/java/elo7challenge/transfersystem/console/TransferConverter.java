package elo7challenge.transfersystem.console;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import elo7challenge.transfersystem.domain.FinancialTransfer;
import elo7challenge.transfersystem.domain.FinancialTransferType;

public class TransferConverter {

	public TransferConverterResult convertToTransfer(Map<String, String> readTransfer) {
		FinancialTransfer transfer = new FinancialTransfer();
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>(); 
		
		transfer.setSenderAccount(readTransfer.get("senderAccount"));
		if (readTransfer.get("senderAccount").isEmpty()) {
			errors.add(new ErrorMessage("senderAccount", "invalid value []"));
		}

		transfer.setRecipientAccount(readTransfer.get("recipientAccount"));
		if (readTransfer.get("recipientAccount").isEmpty()) {
			errors.add(new ErrorMessage("recipientAccount", "invalid value []"));
		}

		try {
			transfer.setValue(new BigDecimal(readTransfer.get("value")));
		} catch (NumberFormatException e) {
			errors.add(new ErrorMessage("value", "invalid value [" + readTransfer.get("value") + "]"));
		}
	
		try {
			transfer.setScheduledDate(new SimpleDateFormat("dd/MM/yyyy").parse(readTransfer.get("scheduledDate")));
		} catch (ParseException e) {
			errors.add(new ErrorMessage("scheduledDate", "invalid value [" + readTransfer.get("scheduledDate") + "]"));
		}

		try {
			transfer.setType(FinancialTransferType.valueOfSafe(readTransfer.get("type")));
		} catch (IllegalArgumentException e) {
			errors.add(new ErrorMessage("type", "invalid value [" + readTransfer.get("type") + "]"));
		}

		TransferConverterResult converterResult = new TransferConverterResult();
		converterResult.setConverted(transfer);
		converterResult.setErrors(errors);
		return converterResult;
	}
	
}
