package com.pearlpay.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pearlpay.training.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	List<Inventory> findByPurchaseOrder_ReceiptNumber(String receiptNumber);
}
