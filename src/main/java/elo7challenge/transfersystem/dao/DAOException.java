package elo7challenge.transfersystem.dao;

public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 3340323483681952222L;

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
