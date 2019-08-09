package com.pearlpay.training.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.pearlpay.training.dto.SupplierDTO;
import com.pearlpay.training.entity.Supplier;
import com.pearlpay.training.repository.SupplierRepository;
@Service
public class SupplierServiceImpl implements SupplierService {

	private final SupplierRepository supplierRepository;
	
	SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}
	
	@Override
	public SupplierDTO save(Supplier supplier) {
		Supplier newSupplier = supplierRepository.save(supplier);
		SupplierDTO dto = SupplierDTO.builder().build();
		BeanUtils.copyProperties(newSupplier, dto);
		return dto;
	}

}
