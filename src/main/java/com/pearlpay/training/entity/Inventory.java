package com.pearlpay.training.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Entity
@Table(name="inventory")
public class Inventory extends BaseEntity {
	

	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private Double price;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "purchase_order_id")
	private PurchaseOrder purchaseOrder;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="inventories_suppliers", joinColumns = @JoinColumn(name="inventory_id"),
											 inverseJoinColumns = @JoinColumn(name="supplier_id"))
	private List<Supplier> suppliers;
}
