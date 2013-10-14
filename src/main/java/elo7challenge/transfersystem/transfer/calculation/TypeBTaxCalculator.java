package elo7challenge.transfersystem.transfer.calculation;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import elo7challenge.transfersystem.time.Clock;
import elo7challenge.transfersystem.transfer.TaxCalculator;

public class TypeBTaxCalculator implements TaxCalculator {

	private Clock clock;
	
	public TypeBTaxCalculator(Clock clock) {
		this.clock = clock;
	}
	
	public BigDecimal calculate(BigDecimal value, Date scheduledDate) {
		if (scheduledDate == null || scheduledDate.before(clock.getCurrentDate())) {
			return null;
		}
		
		Calendar days30Later = clock.getCurrentDateAsCalendar();
		days30Later.add(Calendar.DAY_OF_MONTH, +30);
		
		BigDecimal tax;
		if (scheduledDate.after(days30Later.getTime())) {
			tax = new BigDecimal("8.00");
		} else {
			tax = new BigDecimal("10.00");
		}
		
		return tax;
	}
	
}
