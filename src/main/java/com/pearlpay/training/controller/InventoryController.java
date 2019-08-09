package com.pearlpay.training.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pearlpay.training.dto.InventoryDTO;
import com.pearlpay.training.service.InventoryService;

import lombok.extern.log4j.Log4j2;

@CrossOrigin(origins="http://127.0.0.1:8887")
@Log4j2
@RestController
@RequestMapping("api/v1/inventory")
public class InventoryController {
	private final InventoryService inventoryService;
	
	InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
	
	@GetMapping
	public ResponseEntity<List<InventoryDTO>> getAllInventories() {
		log.debug("Retrieving all inventories...");
		
		return ResponseEntity.ok(inventoryService.findAll());
	}
	
	@GetMapping("{inventoryId}")
	public ResponseEntity<InventoryDTO> findById(@PathVariable Long inventoryId) {
		log.debug("Retrieving inventory with id: {}", inventoryId);
		
		return inventoryService.findById(inventoryId).map(dto -> {
			return new ResponseEntity<>(dto, HttpStatus.OK);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/receipt/{receiptName}")
	public ResponseEntity<List<InventoryDTO>> findAllByReceiptNumber(@PathVariable String receiptName) {
		log.debug("Retrieving inventories with receipt number: {}", receiptName);
		
		return ResponseEntity.ok(inventoryService.findAllByReceiptNumber(receiptName));
	}
}
