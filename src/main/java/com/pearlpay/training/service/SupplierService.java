package com.pearlpay.training.service;

import com.pearlpay.training.dto.SupplierDTO;
import com.pearlpay.training.entity.Supplier;

public interface SupplierService {
	SupplierDTO save(Supplier supplier);
}
