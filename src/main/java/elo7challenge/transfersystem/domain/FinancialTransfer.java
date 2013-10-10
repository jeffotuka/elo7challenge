package elo7challenge.transfersystem.domain;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialTransfer {

	private Long id;
	private String senderAccount;
	private String recipientAccount;
	private BigDecimal value;
	private BigDecimal tax;
	private Date scheduledDate;
	private FinancialTransferType type;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSenderAccount() {
		return senderAccount;
	}
	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}
	public String getRecipientAccount() {
		return recipientAccount;
	}
	public void setRecipientAccount(String recipientAccount) {
		this.recipientAccount = recipientAccount;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}
	public Date getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public FinancialTransferType getType() {
		return type;
	}
	public void setType(FinancialTransferType type) {
		this.type = type;
	}
}
