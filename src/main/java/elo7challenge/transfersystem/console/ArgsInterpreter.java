package elo7challenge.transfersystem.console;

public class ArgsInterpreter {

	public ArgsCommand identifyCommand(String[] args) {
		if (args == null || args.length == 0) {
			return null;
		}

		ArgsCommand command = ArgsCommand.HELP;
		if ("--list".equals(args[0])) {
			command = ArgsCommand.LIST;
		} else if ("--setup".equals(args[0])) {
			command = ArgsCommand.SETUP;
		} else if ("--input".equals(args[0])) {
			command = ArgsCommand.INPUT;
		}
		
		return command;
	}

}
