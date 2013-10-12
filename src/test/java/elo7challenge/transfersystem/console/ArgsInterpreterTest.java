package elo7challenge.transfersystem.console;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import elo7challenge.transfersystem.console.ArgsCommand;
import elo7challenge.transfersystem.console.ArgsInterpreter;

public class ArgsInterpreterTest {

	private ArgsInterpreter interpreter;

	@Before
	public void setUp() {
		this.interpreter = new ArgsInterpreter();
	}
	
	@Test
	public void shouldIdentifyListCommandFromArgs() {
		String[] args = new String[] {"--list"};
		
		ArgsCommand command = this.interpreter.identifyCommand(args);

		assertEquals(ArgsCommand.LIST, command);
	}
	
	@Test
	public void shouldIdentifyNothingWhenNullArgs() {
		ArgsCommand command = this.interpreter.identifyCommand(null);
		
		assertNull(command);
	}

	@Test
	public void shouldIdentifyNothingWhenEmptyArgs() {
		ArgsCommand command = this.interpreter.identifyCommand(new String[] { });
		
		assertNull(command);
	}

	@Test
	public void shouldIdentifyNothingWhenUnknownArgs() {
		String[] unknownArgs = new String[] {"XYZ"};

		ArgsCommand command = this.interpreter.identifyCommand(unknownArgs);
		
		assertNull(command);
	}

	@Test
	public void shouldIdentifySetupCommandFromArgs() {
		String[] args = new String[] {"--setup"};
		
		ArgsCommand command = this.interpreter.identifyCommand(args);

		assertEquals(ArgsCommand.SETUP, command);
	}

}
