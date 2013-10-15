package elo7challenge.transfersystem.console;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import elo7challenge.transfersystem.db.DBTransferSetup;
import elo7challenge.transfersystem.domain.FinancialTransfer;
import elo7challenge.transfersystem.transfer.TransferManager;

@RunWith(MockitoJUnitRunner.class)
public class CommandRunnerTest {
	
	@InjectMocks
	private CommandRunner runner;
	
	private @Mock TransferManager manager;
	private @Mock TransferDisplay display;
	private @Mock TransferReader reader;
	private @Mock TransferConverter converter;
	private @Mock DBTransferSetup setup;
	
	@Test
	public void shouldDisplayAllTransfersWhenCommandIsList() {
		List<FinancialTransfer> transfers = new ArrayList<FinancialTransfer>();
		when(manager.getAllTransfers()).thenReturn(transfers);

		runner.run(ArgsCommand.LIST);
		
		verify(display).printAllTransfers(transfers);
	}


	@Test
	public void shouldDoNothingWhenCommandIsNull() {
		runner.run(null);
		
		verifyZeroInteractions(manager);
		verifyZeroInteractions(display);
	}
	
	@Test
	public void shouldSetupDatabaseWhenCommandIsSetup() throws SQLException {
		runner.run(ArgsCommand.SETUP);
		
		verify(setup).execute();
	}
	
	@Test
	public void shouldSaveTransferFromInputWhenCommandIsInput() throws SQLException, IOException {
		Map<String, String> readTransfer = new HashMap<String, String>();
		FinancialTransfer transfer = new FinancialTransfer();
		TransferConverterResult converterResult = new TransferConverterResult();
		converterResult.setConverted(transfer);
		
		when(reader.readTransfer()).thenReturn(readTransfer);
		when(converter.convertToTransfer(readTransfer)).thenReturn(converterResult);
		
		runner.run(ArgsCommand.INPUT);
		
		verify(manager).saveTransfer(transfer);
	}

	@Test
	public void shouldPrintErrorsWhenCommandIsInputAndInputIsInvalid() throws SQLException, IOException {
		Map<String, String> readTransfer = new HashMap<String, String>();
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
		errors.add(new ErrorMessage("value", "invalid value [xpto]"));
		TransferConverterResult converterResult = new TransferConverterResult();
		converterResult.setErrors(errors);;

		when(reader.readTransfer()).thenReturn(readTransfer);
		when(converter.convertToTransfer(readTransfer)).thenReturn(converterResult);

		runner.run(ArgsCommand.INPUT);
		
		verify(display).printErrorMessages(errors);
	}

}
