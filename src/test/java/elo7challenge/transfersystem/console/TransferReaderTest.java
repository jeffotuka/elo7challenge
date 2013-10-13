package elo7challenge.transfersystem.console;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.junit.Test;

public class TransferReaderTest {

	@Test
	public void shouldReadTransferStringsFromInput() throws IOException {
		String inputStr = "01212-3"
				+ "\n45454-6"
				+ "\n120.99"
				+ "\n21/07/2020"
				+ "\nB";
		BufferedReader bufferedReader = new BufferedReader(new StringReader(inputStr));
		
		TransferReader transferReader = new TransferReader();
		Map<String, String> readTransfer = transferReader.readTransfer(bufferedReader);
		
		assertEquals("01212-3", readTransfer.get("senderAccount"));
		assertEquals("45454-6", readTransfer.get("recipientAccount"));
		assertEquals("120.99", readTransfer.get("value"));
		assertEquals("21/07/2020", readTransfer.get("scheduledDate"));
		assertEquals("B", readTransfer.get("type"));
	}

}
