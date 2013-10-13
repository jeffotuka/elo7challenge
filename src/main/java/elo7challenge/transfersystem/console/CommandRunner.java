package elo7challenge.transfersystem.console;

import java.util.List;
import java.util.Map;

import elo7challenge.transfersystem.db.DBTransferSetup;
import elo7challenge.transfersystem.domain.FinancialTransfer;
import elo7challenge.transfersystem.transfer.TransferManager;

public class CommandRunner {

	private TransferManager manager;
	private TransferDisplay display;
	private TransferReader reader;
	private TransferConverter converter;
	private DBTransferSetup setup;

	public CommandRunner(TransferManager manager, TransferDisplay display, 
			TransferReader reader, TransferConverter converter, DBTransferSetup setup) {
		this.manager = manager;
		this.display = display;
		this.reader = reader;
		this.converter = converter;
		this.setup = setup;
	}
	
	public void run(ArgsCommand command) {
		if (ArgsCommand.LIST.equals(command)) {
			List<FinancialTransfer> transfers = this.manager.getAllTransfers();
			this.display.printAllTransfers(transfers);
			
		} else if (ArgsCommand.SETUP.equals(command)) {
			this.setup.execute();
			
		} else if (ArgsCommand.INPUT.equals(command)) {
			Map<String, String> readTransfer = reader.readTransfer();
			FinancialTransfer transfer = converter.convertToTransfer(readTransfer);
			this.manager.saveTransfer(transfer);
		}
	}

}
