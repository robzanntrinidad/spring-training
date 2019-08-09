package com.pearlpay.training.service;

import java.util.List;
import java.util.Optional;

import com.pearlpay.training.dto.InventoryDTO;

public interface InventoryService {
	List<InventoryDTO> findAll();
	Optional<InventoryDTO> findById(Long id);
	List<InventoryDTO> findAllByReceiptNumber(String receiptNumber);
}
