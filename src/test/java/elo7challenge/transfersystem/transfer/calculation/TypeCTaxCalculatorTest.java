package elo7challenge.transfersystem.transfer.calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import elo7challenge.transfersystem.time.Clock;

@RunWith(MockitoJUnitRunner.class)
public class TypeCTaxCalculatorTest {

	@InjectMocks
	private TypeCTaxCalculator calculator;
	
	private @Mock Clock clock;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	@Before
	public void setUp() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse("13/10/2013 20:17:30"));
		when(clock.getCurrentDate()).thenReturn(calendar.getTime());
		when(clock.getCurrentDateAsCalendar()).thenReturn(calendar);
	}

	@Test
	public void shouldCalculateForDateExactlyNow() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("13/10/2013 20:17:30"));
		
		assertEquals(new BigDecimal("645.56"), tax); // tax 8.3%
	}

	@Test
	public void shouldCalculateForDate5DaysLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("18/10/2013 20:17:30"));
		
		assertEquals(new BigDecimal("645.56"), tax); // tax 8.3%
	}
	
	@Test
	public void shouldCalculateForDate5Days1SecondLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("18/10/2013 20:17:31"));
		
		assertEquals(new BigDecimal("575.56"), tax); // tax 7.4%
	}
	
	@Test
	public void shouldCalculateForDate10DaysLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("23/10/2013 20:17:30"));
		
		assertEquals(new BigDecimal("575.56"), tax); // tax 7.4%
	}

	@Test
	public void shouldCalculateForDate10Days1SecondLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("23/10/2013 20:17:31"));
		
		assertEquals(new BigDecimal("521.12"), tax); // tax 6.7%
	}

	@Test
	public void shouldCalculateForDate15DaysLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("28/10/2013 20:17:30"));
		
		assertEquals(new BigDecimal("521.12"), tax); // tax 6.7%
	}

	@Test
	public void shouldCalculateForDate15Days1SecondLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("28/10/2013 20:17:31"));
		
		assertEquals(new BigDecimal("420.00"), tax); // tax 5.4%
	}

	@Test
	public void shouldCalculateForDate20DaysLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("02/11/2013 20:17:30"));
		
		assertEquals(new BigDecimal("420.00"), tax); // tax 5.4%
	}

	@Test
	public void shouldCalculateForDate20Days1SecondLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("02/11/2013 20:17:31"));
		
		assertEquals(new BigDecimal("334.45"), tax); // tax 4.3%
	}

	@Test
	public void shouldCalculateForDate25DaysLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("07/11/2013 20:17:30"));
		
		assertEquals(new BigDecimal("334.45"), tax); // tax 4.3%
	}

	@Test
	public void shouldCalculateForDate25Days1SecondLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("07/11/2013 20:17:31"));
		
		assertEquals(new BigDecimal("163.34"), tax); // tax 2.1%
	}

	@Test
	public void shouldCalculateForDate30DaysLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("12/11/2013 20:17:30"));
		
		assertEquals(new BigDecimal("163.34"), tax); // tax 2.1%
	}

	@Test
	public void shouldCalculateForDate30Days1SecondLater() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("12/11/2013 20:17:31"));
		
		assertEquals(new BigDecimal("93.34"), tax); // tax 1.2%
	}

	@Test
	public void shouldReturnNullForInvalidDateEarlier() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), dateFormat.parse("13/10/2013 20:17:29"));
		
		Assert.assertNull(tax);
	}

	@Test
	public void shouldReturnNullForInvalidDateNull() throws ParseException {
		BigDecimal tax = calculator.calculate(new BigDecimal("7777.77"), null);
		
		Assert.assertNull(tax);
	}

	@Test
	public void shouldReturnNullForInvalidValueNull() throws ParseException {
		BigDecimal tax = calculator.calculate(null, dateFormat.parse("18/10/2013 20:17:30"));
		
		Assert.assertNull(tax);
	}

}
