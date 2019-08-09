package com.pearlpay.training.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.pearlpay.training.dto.InventoryDTO;
import com.pearlpay.training.dto.PurchaseOrderDTO;
import com.pearlpay.training.entity.PurchaseOrder;
import com.pearlpay.training.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;
	
	InventoryServiceImpl(InventoryRepository inventoryRepository){
		this.inventoryRepository = inventoryRepository;
	}

	@Override
	public List<InventoryDTO> findAll() {
		return inventoryRepository.findAll().stream().map(inventory -> {
			PurchaseOrder po = inventory.getPurchaseOrder();
			PurchaseOrderDTO purchase = PurchaseOrderDTO.builder()
														.id(po.getId())
														.dateCreated(po.getDateCreated())
														.receiptNumber(po.getReceiptNumber())
														.build();
			
			InventoryDTO dto = InventoryDTO.builder()
					.id(inventory.getId())
					.dateCreated(inventory.getDateCreated())
					.name(inventory.getName())
					.price(inventory.getPrice())
					.purchaseOrder(purchase)
					.build();
return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<InventoryDTO> findById(Long id) {
		return inventoryRepository.findById(id).map(inventory -> {
			PurchaseOrder po = inventory.getPurchaseOrder();
			PurchaseOrderDTO purchase = PurchaseOrderDTO.builder()
														.id(po.getId())
														.dateCreated(po.getDateCreated())
														.receiptNumber(po.getReceiptNumber())
														.build();
			
			InventoryDTO dto = InventoryDTO.builder()
												.id(inventory.getId())
												.dateCreated(inventory.getDateCreated())
												.name(inventory.getName())
												.price(inventory.getPrice())
												.purchaseOrder(purchase)
												.build();
			return dto;
		});
	}

	@Override
	public List<InventoryDTO> findAllByReceiptNumber(String receiptNumber) {
		return inventoryRepository.findByPurchaseOrder_ReceiptNumber(receiptNumber).stream().map(inventory -> {
			PurchaseOrder po = inventory.getPurchaseOrder();
			PurchaseOrderDTO purchase = PurchaseOrderDTO.builder()
														.id(po.getId())
														.dateCreated(po.getDateCreated())
														.receiptNumber(po.getReceiptNumber())
														.build();
			
			InventoryDTO dto = InventoryDTO.builder()
												.id(inventory.getId())
												.dateCreated(inventory.getDateCreated())
												.name(inventory.getName())
												.price(inventory.getPrice())
												.purchaseOrder(purchase)
												.build();
			return dto;
		}).collect(Collectors.toList());
	}

	
}
