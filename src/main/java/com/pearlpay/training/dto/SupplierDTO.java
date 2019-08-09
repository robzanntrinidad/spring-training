package com.pearlpay.training.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SupplierDTO extends BaseEntityDTO {
	
	private String name;
	private String address;
	private List<InventoryDTO> inventories;
}
