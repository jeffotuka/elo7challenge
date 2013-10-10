package elo7challenge.transfersystem.dao;

import java.util.List;

import elo7challenge.transfersystem.domain.FinancialTransfer;

public interface FinancialTransferDAO {

	void save(FinancialTransfer financialTransfer);

	List<FinancialTransfer> list();
	
}
