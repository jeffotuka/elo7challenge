package elo7challenge.transfersystem.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import elo7challenge.transfersystem.dao.DAOException;
import elo7challenge.transfersystem.dao.FinancialTransferDAO;
import elo7challenge.transfersystem.domain.FinancialTransfer;
import elo7challenge.transfersystem.domain.FinancialTransferType;

public class FinancialTransferSQLDAO implements FinancialTransferDAO {

	private Connection connection;
	
	public FinancialTransferSQLDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public List<FinancialTransfer> list() {
		List<FinancialTransfer> list = new ArrayList<FinancialTransfer>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, sender_account, recipient_account, value, tax, scheduled_date, type FROM financial_transfer");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				FinancialTransfer transfer = new FinancialTransfer();
				transfer.setId(resultSet.getLong("id"));
				transfer.setSenderAccount(resultSet.getString("sender_account"));
				transfer.setRecipientAccount(resultSet.getString("recipient_account"));
				transfer.setValue(resultSet.getBigDecimal("value"));
				transfer.setTax(resultSet.getBigDecimal("tax"));
				transfer.setScheduledDate(resultSet.getDate("scheduled_date"));
				transfer.setType(FinancialTransferType.valueOfSafe(resultSet.getString("type")));
				
				list.add(transfer);
			}
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
		return list;
	}

	@Override
	public void save(FinancialTransfer financialTransfer) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO financial_transfer (id, sender_account, recipient_account, value, tax, scheduled_date, type) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, financialTransfer.getSenderAccount());
			preparedStatement.setString(2, financialTransfer.getRecipientAccount());
			preparedStatement.setBigDecimal(3, financialTransfer.getValue());
			preparedStatement.setBigDecimal(4, financialTransfer.getTax());
			preparedStatement.setDate(5, new java.sql.Date(financialTransfer.getScheduledDate().getTime()));
			preparedStatement.setString(6, financialTransfer.getTypeName());
			preparedStatement.execute();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}

