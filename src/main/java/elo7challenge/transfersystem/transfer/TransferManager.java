package elo7challenge.transfersystem.transfer;

import java.math.BigDecimal;
import java.util.List;

import elo7challenge.transfersystem.dao.FinancialTransferDAO;
import elo7challenge.transfersystem.domain.FinancialTransfer;

public class TransferManager {

	private FinancialTransferDAO transferDao;
	private TaxCalculatorFactory calculatorFactory;
	
	public TransferManager(FinancialTransferDAO transferDao, TaxCalculatorFactory calculatorFactory) {
		this.transferDao = transferDao;
		this.calculatorFactory = calculatorFactory;
	}
	
	public List<FinancialTransfer> getAllTransfers() {
		return transferDao.list();
	}
	
	public void saveTransfer(FinancialTransfer transfer) {
		TaxCalculator calculator = this.calculatorFactory.build(transfer.getType(), transfer.getValue());
		BigDecimal tax = calculator.calculate(transfer.getValue(), transfer.getScheduledDate());
		transfer.setTax(tax);
		
		transferDao.save(transfer);
	}
	
}
