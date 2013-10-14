package elo7challenge.transfersystem.transfer.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import elo7challenge.transfersystem.time.Clock;
import elo7challenge.transfersystem.transfer.TaxCalculator;

public class TypeCTaxCalculator implements TaxCalculator {

	private Clock clock;
	
	public TypeCTaxCalculator(Clock clock) {
		this.clock = clock;
	}
	
	@Override
	public BigDecimal calculate(BigDecimal value, Date scheduledDate) {
		if (scheduledDate == null || scheduledDate.before(clock.getCurrentDate())) {
			return null;
		}
		
		TypeCTaxTier tier = TypeCTaxTier.getTierByScheduledDate(this.clock.getCurrentDate(), scheduledDate);
		
		BigDecimal tax = value.multiply(tier.getTaxPercentage());
		BigDecimal taxAdjusted = tax.setScale(2, RoundingMode.UP);
		
		return taxAdjusted;
	}

}
