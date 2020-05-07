package services;

import com.cg.onlinewallet.entities.WalletAccount;
import com.cg.onlinewallet.entities.WalletAccount.status;
import com.cg.onlinewallet.exceptions.InvalidException;
import com.cg.onlinewallet.exceptions.WrongValueException;

import entity.WalletUser;

public class TransactionServiceImplementaion implements TransactionServices {

	@Override
	public Double addMoney(Integer userId, Double Amount) {
		// TODO Auto-generated method stub
		WalletUser user = WalletUserDao.getUser(userId);
		Integer accountId = user.getAccount().getAccountID();
		WalletAccount account = onlineWalletDao.getAccount(accountId);
		Double balance = account.getAccountBalance();
		balance += Amount;
		account.setAccountBalance(balance);
		return account.getAccountBalance();
	}

	@Override
	public Double showBalance(Integer userId) {
		WalletUser user = WalletUserDao.getUser(userId);
		WalletAccount account = user.getAccountDetail();
		return account.getAccountBalance();
	}

	@Override
	public void transactMoney(Integer userId, String beneficiaryLoginName, Double amount) {
		if (onlineWalletDao.checkUserByEmail(beneficiaryEmail) == false)
			throw new InvalidException("The Beneficary doesn't exist");
		WalletUser beneficiary = onlineWalletDao.getUserByEmail(beneficiaryEmail);
		if (beneficiary.getAccountDetail().getUserStatus() == status.non_active)
			throw new InvalidException("The Beneficiary must be an active user");
		WalletUser user = onlineWalletDao.getUser(userId);
		if (user.getAccountDetail().getAccountBalance() < amount)
			throw new WrongValueException("The Amount cannot be greater then available Balance");
		Integer beneficiaryId = beneficiary.getUserID();
		Double beneficiaryBalance = beneficiary.getAccountDetail().getAccountBalance();
		beneficiary.getAccountDetail().setAccountBalance(beneficiaryBalance + amount);
		Double userBalance = user.getAccountDetail().getAccountBalance();
		user.getAccountDetail().setAccountBalance(userBalance - amount);
		createTransaction(userId, "Amount has been tranfered. Balance has been updated", amount);
		createTransaction(beneficiaryId, "Amount credited to your account. Balance has been updated", amount);
		
	}

}
