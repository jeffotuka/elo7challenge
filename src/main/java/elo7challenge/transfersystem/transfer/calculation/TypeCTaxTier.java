package elo7challenge.transfersystem.transfer.calculation;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public enum TypeCTaxTier {

	DAYS_5(5, new BigDecimal("0.083")),
	DAYS_10(10, new BigDecimal("0.074")),
	DAYS_15(15, new BigDecimal("0.067")),
	DAYS_20(20, new BigDecimal("0.054")),
	DAYS_25(25, new BigDecimal("0.043")),
	DAYS_30(30, new BigDecimal("0.021")),
	DAYS_OVER_30(null, new BigDecimal("0.012"));
	
	private Integer days;
	private BigDecimal taxPercentage;
	
	private TypeCTaxTier(Integer days, BigDecimal taxPercentage) {
		this.days = days;
		this.taxPercentage = taxPercentage;
	}
	
	public static TypeCTaxTier getTierByScheduledDate(Date currentDate, Date scheduledDate) {
		TypeCTaxTier result = null;
		for (TypeCTaxTier tier : TypeCTaxTier.values()) {
			if (result == null) {
				if (tier.getDays() != null) {
					Calendar tierDate = Calendar.getInstance();
					tierDate.setTime(currentDate);
					tierDate.add(Calendar.DAY_OF_MONTH, tier.getDays());
					
					if (scheduledDate.compareTo(tierDate.getTime()) <= 0) {
						// scheduled date is within this tier
						result = tier;
					}
				} else {
					// scheduled date is in last tier
					result = tier;
				}
			}
		}
		
		return result;
	}
	
	public Integer getDays() {
		return this.days;
	}
	
	public BigDecimal getTaxPercentage() {
		return this.taxPercentage;
	}
}
