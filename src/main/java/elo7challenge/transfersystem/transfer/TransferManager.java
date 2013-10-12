package elo7challenge.transfersystem.transfer;

import java.util.List;

import elo7challenge.transfersystem.dao.FinancialTransferDAO;
import elo7challenge.transfersystem.domain.FinancialTransfer;

public class TransferManager {

	private FinancialTransferDAO transferDao;
	
	public TransferManager(FinancialTransferDAO transferDao) {
		this.transferDao = transferDao;
	}
	
	public List<FinancialTransfer> getAllTransfers() {
		return transferDao.list();
	}
	
}
