package elo7challenge.transfersystem.console;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TransferConverterResultTest {

	@Test
	public void shouldIndicateSuccessWhenNoErrors() {
		TransferConverterResult result = new TransferConverterResult();
		result.setErrors(new ArrayList<ErrorMessage>());
		
		assertTrue(result.isSuccess());
	}

	@Test
	public void shouldIndicateSuccessWhenErrorsIsNull() {
		TransferConverterResult result = new TransferConverterResult();
		result.setErrors(null);
		
		assertTrue(result.isSuccess());
	}

	@Test
	public void cannotIndicateSuccessWhenHasErrors() {
		TransferConverterResult result = new TransferConverterResult();
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
		errors.add(new ErrorMessage());
		result.setErrors(errors);
		
		assertFalse(result.isSuccess());
	}

}
