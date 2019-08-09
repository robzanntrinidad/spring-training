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

public class PurchaseOrderDTO extends BaseEntityDTO {
	
	private String receiptNumber;
	private List<InventoryDTO> inventories;
}
