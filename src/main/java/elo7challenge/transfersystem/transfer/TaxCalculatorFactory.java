package elo7challenge.transfersystem.transfer;

import elo7challenge.transfersystem.domain.FinancialTransferType;
import elo7challenge.transfersystem.transfer.calculation.TypeATaxCalculator;

public class TaxCalculatorFactory {

	public TaxCalculator build(FinancialTransferType type) {
		return new TypeATaxCalculator();
	}
	
}
