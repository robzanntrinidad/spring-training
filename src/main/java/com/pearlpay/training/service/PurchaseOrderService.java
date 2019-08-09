package com.pearlpay.training.service;

import java.util.List;
import java.util.Optional;

import com.pearlpay.training.dto.PurchaseOrderDTO;
import com.pearlpay.training.entity.PurchaseOrder;

public interface PurchaseOrderService {
	PurchaseOrderDTO save(PurchaseOrder purchaseOrder);
	Optional<PurchaseOrderDTO> findById(Long id);
	List<PurchaseOrderDTO> findAll();
//	PurchaseOrderDTO findUsingInventoryId(Long id);
}
