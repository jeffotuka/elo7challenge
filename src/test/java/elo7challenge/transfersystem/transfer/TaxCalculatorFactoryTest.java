package elo7challenge.transfersystem.transfer;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import elo7challenge.transfersystem.domain.FinancialTransferType;
import elo7challenge.transfersystem.transfer.calculation.TypeATaxCalculator;
import elo7challenge.transfersystem.transfer.calculation.TypeBTaxCalculator;
import elo7challenge.transfersystem.transfer.calculation.TypeCTaxCalculator;

public class TaxCalculatorFactoryTest {

	private TaxCalculatorFactory factory;
	
	@Before
	public void setUp() {
		this.factory = new TaxCalculatorFactory();
	}
	
	@Test
	public void shouldBuildTypeATaxCalculator() {
		TaxCalculator calculator = factory.build(FinancialTransferType.A, null);
		
		assertEquals(TypeATaxCalculator.class, calculator.getClass());
	}

	@Test
	public void shouldBuildTypeBTaxCalculator() {
		TaxCalculator calculator = factory.build(FinancialTransferType.B, null);
		
		assertEquals(TypeBTaxCalculator.class, calculator.getClass());
	}

	@Test
	public void shouldBuildTypeCTaxCalculator() {
		TaxCalculator calculator = factory.build(FinancialTransferType.C, null);
		
		assertEquals(TypeCTaxCalculator.class, calculator.getClass());
	}

	@Test
	public void shouldBuildTypeATaxCalculatorForTypeDWithLowValue() {
		TaxCalculator calculator = factory.build(FinancialTransferType.D, new BigDecimal("25000.99"));
		
		assertEquals(TypeATaxCalculator.class, calculator.getClass());
	}

	@Test
	public void shouldBuildTypeBTaxCalculatorForTypeDWithMiddleStartValue() {
		TaxCalculator calculator = factory.build(FinancialTransferType.D, new BigDecimal("25001.00"));
		
		assertEquals(TypeBTaxCalculator.class, calculator.getClass());
	}

	@Test
	public void shouldBuildTypeBTaxCalculatorForTypeDWithMiddleEndValue() {
		TaxCalculator calculator = factory.build(FinancialTransferType.D, new BigDecimal("120000.99"));
		
		assertEquals(TypeBTaxCalculator.class, calculator.getClass());
	}

	@Test
	public void shouldBuildTypeCTaxCalculatorForTypeDWithHighStartValue() {
		TaxCalculator calculator = factory.build(FinancialTransferType.D, new BigDecimal("120001.00"));
		
		assertEquals(TypeCTaxCalculator.class, calculator.getClass());
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionWhenTypeIsDAndValueIsNull() {
		factory.build(FinancialTransferType.D, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionWhenTypeIsNull() {
		factory.build(null, null);
	}

}
