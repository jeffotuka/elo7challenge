package elo7challenge.transfersystem.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import elo7challenge.transfersystem.dao.FinancialTransferDAO;
import elo7challenge.transfersystem.domain.FinancialTransfer;

public class FinancialTransferSQLDAO implements FinancialTransferDAO {

	private Connection connection;
	
	public FinancialTransferSQLDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public List<FinancialTransfer> list() {
		List<FinancialTransfer> list = new ArrayList<FinancialTransfer>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM financial_transfer");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			resultSet.next();

			FinancialTransfer financialTransfer = new FinancialTransfer();
			financialTransfer.setId(resultSet.getLong("id"));
			
			list.add(financialTransfer);
			
		} catch (SQLException e) {
			// TODO test this case
		}
		
		return list;
	}

	@Override
	public void save(FinancialTransfer financialTransfer) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO financial_transfer (id) VALUES (DEFAULT)");
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO test this case
		}
	}

}

