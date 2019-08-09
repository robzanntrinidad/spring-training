package com.pearlpay.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pearlpay.training.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
