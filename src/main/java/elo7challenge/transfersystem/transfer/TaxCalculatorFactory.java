package elo7challenge.transfersystem.transfer;

import java.math.BigDecimal;

import elo7challenge.transfersystem.domain.FinancialTransferType;
import elo7challenge.transfersystem.time.Clock;
import elo7challenge.transfersystem.transfer.calculation.TypeATaxCalculator;
import elo7challenge.transfersystem.transfer.calculation.TypeBTaxCalculator;
import elo7challenge.transfersystem.transfer.calculation.TypeCTaxCalculator;

public class TaxCalculatorFactory {

	public TaxCalculator build(FinancialTransferType type, BigDecimal value) {
		if (type == null) {
			throw new IllegalArgumentException("type is null");
		} else if (FinancialTransferType.D.equals(type) && value == null) {
			throw new IllegalArgumentException("value is null");
		}
		
		TaxCalculator calculator = null;

		FinancialTransferType derivedType = type;
		if (FinancialTransferType.D.equals(type)) {
			if (value.compareTo(new BigDecimal("25001.00")) < 0) {
				derivedType = FinancialTransferType.A;
			} else if (value.compareTo(new BigDecimal("120001.00")) < 0) {
				derivedType = FinancialTransferType.B;
			} else {
				derivedType = FinancialTransferType.C;
			}
		}
		
		if (FinancialTransferType.A.equals(derivedType)) {
			calculator = new TypeATaxCalculator();
		} else if (FinancialTransferType.B.equals(derivedType)) {
			calculator = new TypeBTaxCalculator(new Clock());
		} else if (FinancialTransferType.C.equals(derivedType)) {
			calculator = new TypeCTaxCalculator(new Clock());
		} 
		
		return calculator;
	}
	
}
