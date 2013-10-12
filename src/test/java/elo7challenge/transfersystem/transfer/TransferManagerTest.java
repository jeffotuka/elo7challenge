package elo7challenge.transfersystem.transfer;

import static org.junit.Assert.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import elo7challenge.transfersystem.dao.FinancialTransferDAO;
import elo7challenge.transfersystem.domain.FinancialTransfer;

@RunWith(MockitoJUnitRunner.class)
public class TransferManagerTest {

	@InjectMocks
	private TransferManager manager;
	
	private @Mock FinancialTransferDAO transferDao;
	
	@Test
	public void shouldGetListOfTransfers() {
		List<FinancialTransfer> listExpected = new ArrayList<FinancialTransfer>();
		
		Mockito.when(transferDao.list()).thenReturn(listExpected);
		
		List<FinancialTransfer> list = manager.getAllTransfers();
		
		assertSame(listExpected, list);
	}

}
