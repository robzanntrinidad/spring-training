package com.pearlpay.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pearlpay.training.dto.PurchaseOrderDTO;
import com.pearlpay.training.entity.Inventory;
import com.pearlpay.training.entity.PurchaseOrder;
import com.pearlpay.training.service.PurchaseOrderService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin(origins="http://127.0.0.1:8887")
@Log4j2
@RestController
@RequestMapping("api/v1/purchase")
public class PurchaseOrderController {

	private final PurchaseOrderService purchaseOrderService;
	
	PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}
	
	@PostMapping
	public ResponseEntity<Long> savePurchaseOrder(@RequestBody PurchaseOrderDTO input) {
		List<Inventory> inventories = input.getInventories().stream().map(
			inventoryDTO -> {
				Inventory newInventory = Inventory.builder().build();
				BeanUtils.copyProperties(inventoryDTO, newInventory);
				return newInventory;
			}
		).collect(Collectors.toList());
		
		
		PurchaseOrder orderInput = PurchaseOrder.builder()
												.receiptNumber(input.getReceiptNumber())
												.inventories(inventories)
												.build();
		
		log.debug("Saving purchase order: {}", orderInput.getReceiptNumber());
		
		return new ResponseEntity<>(purchaseOrderService.save(orderInput).getId(), HttpStatus.OK);
	}
	
	@GetMapping("{purchaseOrderId}")
	public ResponseEntity<PurchaseOrderDTO> getPurchaseOrder(@PathVariable Long purchaseOrderId) {
		log.debug("Retrieving Purchase Order with id: {}", purchaseOrderId);
		
		return purchaseOrderService.findById(purchaseOrderId).map(dto -> {
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping
	public ResponseEntity<List<PurchaseOrderDTO>> getAllPurchaseOrders() {
		log.debug("Retrieving all purchase orders...");
		
		return ResponseEntity.ok(purchaseOrderService.findAll());
	}
	
//	@GetMapping("/inventory/{inventoryId}")
//	public ResponseEntity<PurchaseOrderDTO> getPurchaseOrderByInventoryId(@PathVariable Long inventoryId) {
//		log.debug("Retrieving Purchase Order containing Inventory Id: {}", inventoryId);
//		
//		return new ResponseEntity<>(purchaseOrderService.findUsingInventoryId(inventoryId), HttpStatus.OK);
//	}
	
}
