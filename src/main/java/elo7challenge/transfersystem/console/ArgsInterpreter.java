package elo7challenge.transfersystem.console;

public class ArgsInterpreter {

	public ArgsCommand identifyCommand(String[] args) {
		if (args == null || args.length == 0) {
			return null;
		}

		ArgsCommand command = null;
		if ("--list".equals(args[0])) {
			command = ArgsCommand.LIST;
		}
		
		return command;
	}

}
