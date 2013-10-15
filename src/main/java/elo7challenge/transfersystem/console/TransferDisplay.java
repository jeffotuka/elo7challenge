package elo7challenge.transfersystem.console;

import java.io.PrintWriter;
import java.util.List;

import elo7challenge.transfersystem.domain.FinancialTransfer;

public class TransferDisplay {

	private PrintWriter printWriter;
	
	public TransferDisplay(PrintWriter printWriter) {
		this.printWriter = printWriter;
	}
	
	public void printAllTransfers(List<FinancialTransfer> transfers) {
		for (FinancialTransfer transfer : transfers) {
			printWriter.printf("\nid %d, sender %s, recipient %s, value %s, tax %s, date %td/%tm/%tY, type %s",
					transfer.getId(),
					transfer.getSenderAccount(),
					transfer.getRecipientAccount(),
					transfer.getValue(),
					transfer.getTax(),
					transfer.getScheduledDate(),
					transfer.getScheduledDate(),
					transfer.getScheduledDate(),
					transfer.getTypeName());
		}
		printWriter.println();
		printWriter.flush();
	}

	public void printErrorMessages(List<ErrorMessage> errors) {
		for (ErrorMessage error : errors) {
			printWriter.printf("\n%s: %s", error.getField(), error.getMessage());
		}
		printWriter.println();
		printWriter.flush();
	}
	
	public void print(String string) {
		printWriter.print(string);
		printWriter.flush();
	}
	
}
