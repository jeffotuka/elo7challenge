package elo7challenge.transfersystem.transfer.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import elo7challenge.transfersystem.transfer.TaxCalculator;

public class TypeATaxCalculator implements TaxCalculator {

	public BigDecimal calculate(BigDecimal value, Date scheduledDate) {
		if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
			return null;
		}
		
		BigDecimal proportional = value.multiply(new BigDecimal("0.03"));
		BigDecimal tax = new BigDecimal("2.00").add(proportional);
		BigDecimal taxAdjusted = tax.setScale(2, RoundingMode.UP);
		
		return taxAdjusted;
	}
	
}
