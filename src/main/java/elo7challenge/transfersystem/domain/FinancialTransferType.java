package elo7challenge.transfersystem.domain;

public enum FinancialTransferType {

	A, B, C, D;
	
	public static FinancialTransferType valueOfSafe(String arg0) {
		if (arg0 != null) {
			return FinancialTransferType.valueOf(arg0);
		} else {
			return null;
		}
	}
}
