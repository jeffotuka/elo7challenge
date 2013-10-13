package elo7challenge.transfersystem.console;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

public class TransferReaderTest {

	@Test
	public void shouldReadTransferStringsFromInput() throws IOException {
		String inputStr = "01212-3"
				+ "\n45454-6"
				+ "\n120.99"
				+ "\n21/07/2020"
				+ "\nB";

		BufferedReader bufferedReader = new BufferedReader(new StringReader(inputStr));
		TransferDisplay display = Mockito.mock(TransferDisplay.class);
		TransferReader transferReader = new TransferReader(bufferedReader, display);
		Map<String, String> readTransfer = transferReader.readTransfer();
		
		assertEquals("01212-3", readTransfer.get("senderAccount"));
		assertEquals("45454-6", readTransfer.get("recipientAccount"));
		assertEquals("120.99", readTransfer.get("value"));
		assertEquals("21/07/2020", readTransfer.get("scheduledDate"));
		assertEquals("B", readTransfer.get("type"));
		
		verify(display).print("Sender account: ");
		verify(display).print("Recipient account: ");
		verify(display).print("Value: ");
		verify(display).print("Scheduled date: ");
		verify(display).print("Type: ");
	}

	@Test(expected=RuntimeException.class)
	public void shouldThrowExceptionWhenFailingToRead() throws IOException {
		BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
		TransferDisplay display = Mockito.mock(TransferDisplay.class);
		TransferReader transferReader = new TransferReader(bufferedReader, display);
		
		when(bufferedReader.readLine()).thenThrow(new IOException());
		
		transferReader.readTransfer();
	}

}
