package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.WalletTransactions;

public interface TransactionDao extends JpaRepository<WalletTransactions,Long> {

}
