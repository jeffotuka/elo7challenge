package elo7challenge.transfersystem.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TransferReader {

	private BufferedReader reader;
	
	public TransferReader(BufferedReader reader) {
		this.reader = reader;
	}
	
	public Map<String, String> readTransfer() {
		try {
			Map<String, String> readTransfer = new HashMap<String, String>();
			readTransfer.put("senderAccount", reader.readLine());
			readTransfer.put("recipientAccount", reader.readLine());
			readTransfer.put("value", reader.readLine());
			readTransfer.put("scheduledDate", reader.readLine());
			readTransfer.put("type", reader.readLine());
			
			return readTransfer;

		} catch (IOException e) { 
			throw new RuntimeException(e);
		}
	}
	
}
