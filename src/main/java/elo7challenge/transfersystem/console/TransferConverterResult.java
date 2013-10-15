package elo7challenge.transfersystem.console;

import java.util.List;

import elo7challenge.transfersystem.domain.FinancialTransfer;

public class TransferConverterResult {

	private FinancialTransfer converted;
	private List<ErrorMessage> errors;
	
	public boolean isSuccess() {
		if (errors == null || errors.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	public FinancialTransfer getConverted() {
		return converted;
	}
	public void setConverted(FinancialTransfer converted) {
		this.converted = converted;
	}
	public List<ErrorMessage> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorMessage> errors) {
		this.errors = errors;
	}
}

