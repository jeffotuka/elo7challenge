package elo7challenge.transfersystem.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TransferReader {

	private BufferedReader reader;
	private TransferDisplay display;
	
	public TransferReader(BufferedReader reader, TransferDisplay display) {
		this.reader = reader;
		this.display = display;
	}
	
	public Map<String, String> readTransfer() {
		try {
			Map<String, String> readTransfer = new HashMap<String, String>();
			
			display.print("Sender account: ");
			readTransfer.put("senderAccount", reader.readLine());
			
			display.print("Recipient account: ");
			readTransfer.put("recipientAccount", reader.readLine());
			
			display.print("Value: ");
			readTransfer.put("value", reader.readLine());

			display.print("Scheduled date: ");
			readTransfer.put("scheduledDate", reader.readLine());
			
			display.print("Type: ");
			readTransfer.put("type", reader.readLine());
			
			return readTransfer;

		} catch (IOException e) { 
			throw new RuntimeException(e);
		}
	}
	
}
