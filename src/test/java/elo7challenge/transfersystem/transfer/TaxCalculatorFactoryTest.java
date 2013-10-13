package elo7challenge.transfersystem.transfer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import elo7challenge.transfersystem.domain.FinancialTransferType;
import elo7challenge.transfersystem.transfer.calculation.TypeATaxCalculator;

public class TaxCalculatorFactoryTest {

	private TaxCalculatorFactory factory;
	
	@Before
	public void setUp() {
		this.factory = new TaxCalculatorFactory();
	}
	
	@Test
	public void shouldBuildTypeATaxCalculator() {
		TaxCalculator calculator = factory.build(FinancialTransferType.A);
		
		assertEquals(TypeATaxCalculator.class, calculator.getClass());
	}

}
