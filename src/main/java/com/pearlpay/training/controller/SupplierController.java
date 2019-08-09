package com.pearlpay.training.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pearlpay.training.dto.SupplierDTO;
import com.pearlpay.training.entity.Supplier;
import com.pearlpay.training.service.SupplierService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/v1/supplier")
public class SupplierController {

	private final SupplierService supplierService;
	
	SupplierController(SupplierService supplierService) {
		this.supplierService = supplierService;
	}
	
	@PostMapping
	public ResponseEntity<Long> saveSupplier(@RequestBody SupplierDTO input) {
		Supplier supplierInput = Supplier.builder()
											.name(input.getName())
											.address(input.getAddress())
											.build();
		log.debug("Saving supplier: {}", supplierInput.getId());
		return new ResponseEntity<>(supplierService.save(supplierInput).getId(), HttpStatus.OK);
	}
}
