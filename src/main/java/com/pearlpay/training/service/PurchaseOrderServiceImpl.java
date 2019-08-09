package com.pearlpay.training.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pearlpay.training.dto.InventoryDTO;
import com.pearlpay.training.dto.PurchaseOrderDTO;
import com.pearlpay.training.entity.PurchaseOrder;
import com.pearlpay.training.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Override
	public PurchaseOrderDTO save(PurchaseOrder purchaseOrder) {
		purchaseOrder.getInventories().forEach(inventory -> {
			inventory.setPurchaseOrder(purchaseOrder);
		});
		PurchaseOrder order = purchaseOrderRepository.save(purchaseOrder);
		PurchaseOrderDTO dto = PurchaseOrderDTO.builder().build();
		BeanUtils.copyProperties(order, dto);
		return dto;
	}
	
	@Override
	public Optional<PurchaseOrderDTO> findById(Long id) {
		return purchaseOrderRepository.findById(id).map(purchase -> {
			
			List<InventoryDTO> inventories = purchase.getInventories().stream().map(inventory -> {
				InventoryDTO dto = InventoryDTO.builder().build();
				BeanUtils.copyProperties(inventory, dto);
				return dto;
			}).collect(Collectors.toList());
			
			PurchaseOrderDTO po = PurchaseOrderDTO.builder()
													.id(purchase.getId())
													.dateCreated(purchase.getDateCreated())
													.receiptNumber(purchase.getReceiptNumber())
													.inventories(inventories)
													.build();
			return po;
		});
	}

	@Override
	public List<PurchaseOrderDTO> findAll() {
		return purchaseOrderRepository.findAll().stream().map(purchase -> {
			List<InventoryDTO> inventories = purchase.getInventories().stream().map(inventory -> {
				InventoryDTO dto = InventoryDTO.builder().build();
				BeanUtils.copyProperties(inventory, dto);
				return dto;
			}).collect(Collectors.toList());
			
			PurchaseOrderDTO po = PurchaseOrderDTO.builder()
					.id(purchase.getId())
					.dateCreated(purchase.getDateCreated())
					.receiptNumber(purchase.getReceiptNumber())
					.inventories(inventories)
					.build();
			return po;
		}).collect(Collectors.toList());
	}

//	@Override
//	public PurchaseOrderDTO findUsingInventoryId(Long id) {
//		PurchaseOrder purchase = purchaseOrderRepository.findReceiptNumberByInventories_Id(id);
//		List<InventoryDTO> inventories = purchase.getInventories().stream().map(inventory -> {
//			InventoryDTO dto = InventoryDTO.builder().build();
//			BeanUtils.copyProperties(inventory, dto);
//			return dto;
//		}).collect(Collectors.toList());
//		
//		PurchaseOrderDTO newPO = PurchaseOrderDTO.builder()
//				.id(purchase.getId())
//				.dateCreated(purchase.getDateCreated())
//				.receiptNumber(purchase.getReceiptNumber())
//				.inventories(inventories)
//				.build();
//		
//		return newPO;
//	}
}
