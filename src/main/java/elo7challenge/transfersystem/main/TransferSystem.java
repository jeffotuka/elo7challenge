package elo7challenge.transfersystem.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

import elo7challenge.transfersystem.console.ArgsCommand;
import elo7challenge.transfersystem.console.ArgsInterpreter;
import elo7challenge.transfersystem.console.CommandRunner;
import elo7challenge.transfersystem.console.CommandRunnerFactory;

public class TransferSystem {

	public static void main(String[] args) throws SQLException, ParseException {
		Connection connection = null;
		try {
			ArgsInterpreter argsInterpreter = new ArgsInterpreter();
			ArgsCommand command = argsInterpreter.identifyCommand(args);

			connection = DriverManager.getConnection("jdbc:hsqldb:file:db/dbtransfer", "SA", "");
			CommandRunner commandRunner = new CommandRunnerFactory().build(connection, System.in, System.out);
			commandRunner.run(command);
			
		} catch (SQLException e) {
			throw e;
			
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}
}
