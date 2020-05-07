package services;

public interface TransactionServices {
	double addMoney(Integer userId, Double Amount);
	double showBalance(Integer userId);
	void transactMoney(Integer userId, String beneficiaryLoginName, Double amount);

}
