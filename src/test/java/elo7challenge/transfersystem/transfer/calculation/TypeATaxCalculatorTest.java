package elo7challenge.transfersystem.transfer.calculation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class TypeATaxCalculatorTest {

	private TypeATaxCalculator calculator;
	
	@Before
	public void setUp() {
		this.calculator = new TypeATaxCalculator();
	}
	
	@Test
	public void shouldCalculateForValue10() {
		BigDecimal tax = calculator.calculate(new BigDecimal("10.00"), null);
		
		assertEquals(new BigDecimal("2.30"), tax);
	}

	@Test
	public void shouldCalculateForValue7777_77() {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), null);
		
		assertEquals(new BigDecimal("235.34"), tax);
	}

	@Test
	public void shouldCalculateForValueZero() {
		BigDecimal tax = calculator.calculate(new BigDecimal("0.00"), null);
		
		assertEquals(new BigDecimal("2.00"), tax);
	}

	@Test
	public void shouldCalculateForInvalidValueNegative() {
		BigDecimal tax = calculator.calculate(new BigDecimal("-5555.55"), null);
		
		assertNull(tax);
	}

	@Test
	public void shouldCalculateForInvalidValueNull() {
		BigDecimal tax = calculator.calculate(null, null);
		
		assertNull(tax);
	}

}
