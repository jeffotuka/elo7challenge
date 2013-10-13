package elo7challenge.transfersystem.console;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;

import elo7challenge.transfersystem.dao.FinancialTransferDAO;
import elo7challenge.transfersystem.dao.sql.FinancialTransferSQLDAO;
import elo7challenge.transfersystem.db.DBTransferSetup;
import elo7challenge.transfersystem.transfer.TransferManager;

public class CommandRunnerFactory {

	public CommandRunner build(Connection connection, InputStream in, OutputStream out) {
		FinancialTransferDAO transferDao = new FinancialTransferSQLDAO(connection);
		TransferManager manager = new TransferManager(transferDao);

		PrintWriter printWriter = new PrintWriter(out);
		TransferDisplay display = new TransferDisplay(printWriter);

		TransferReader reader = new TransferReader(new BufferedReader(new InputStreamReader(in)));
		
		TransferConverter converter = new TransferConverter();
		
		DBTransferSetup setup = new DBTransferSetup(connection);
		
		return new CommandRunner(manager, display, reader, converter, setup);
	}
	
}
