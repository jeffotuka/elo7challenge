package elo7challenge.transfersystem.transfer.calculation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import elo7challenge.transfersystem.time.Clock;

@RunWith(MockitoJUnitRunner.class)
public class TypeBTaxCalculatorTest {

	@InjectMocks
	private TypeBTaxCalculator calculator;
	
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
	public void shouldCalculateForDate40DaysAndSomeHoursLater() throws ParseException {
		BigDecimal tax = calculator.calculate(null, dateFormat.parse("22/11/2013 23:34:56"));
		
		assertEquals(new BigDecimal("8.00"), tax);
	}

	@Test
	public void shouldCalculateForDate9DaysAndSomeHoursLater() throws ParseException {
		BigDecimal tax = calculator.calculate(null, dateFormat.parse("23/10/2013 06:00:00"));
		
		assertEquals(new BigDecimal("10.00"), tax);
	}

	@Test
	public void shouldCalculateForDate30Days1SecondLater() throws ParseException {
		BigDecimal tax = calculator.calculate(null, dateFormat.parse("12/11/2013 20:17:31"));
		
		assertEquals(new BigDecimal("8.00"), tax);
	}

	@Test
	public void shouldCalculateForDate30DaysLater() throws ParseException {
		BigDecimal tax = calculator.calculate(null, dateFormat.parse("12/11/2013 20:17:30"));
		
		assertEquals(new BigDecimal("10.00"), tax);
	}

	@Test
	public void shouldCalculateForDateExactlyNow() throws ParseException {
		BigDecimal tax = calculator.calculate(null, dateFormat.parse("13/10/2013 20:17:30"));
		
		assertEquals(new BigDecimal("10.00"), tax);
	}

	@Test
	public void shouldReturnNullForInvalidDateEarlier() throws ParseException {
		BigDecimal tax = calculator.calculate(null, dateFormat.parse("13/10/2013 20:17:29"));
		
		assertNull(tax);
	}

	@Test
	public void shouldReturnNullForInvalidDateNull() throws ParseException {
		BigDecimal tax = calculator.calculate(null, null);
		
		assertNull(tax);
	}

}
