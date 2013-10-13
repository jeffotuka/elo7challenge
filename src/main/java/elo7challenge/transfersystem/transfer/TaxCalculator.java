package elo7challenge.transfersystem.transfer;

import java.math.BigDecimal;
import java.util.Date;

public interface TaxCalculator {
	
	BigDecimal calculate(BigDecimal value, Date scheduledDate);
	
}
