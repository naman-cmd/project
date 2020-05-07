package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class WalletAccount {
	@Id
	@GeneratedValue
	int accountId;
	double accountBalance;
	@OneToMany
	List <WalletTransactions> transactions ;
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public List<WalletTransactions> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<WalletTransactions> transactions) {
		this.transactions = transactions;
	}
	
	

}
