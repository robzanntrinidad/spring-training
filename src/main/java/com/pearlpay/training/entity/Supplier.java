package com.pearlpay.training.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
@Table(name="supplier")
public class Supplier extends BaseEntity {
	
	@Column(name="name")
	private String name;
	
	@Column(name="address", columnDefinition = "TEXT")
	private String address;
	
	@ManyToMany(mappedBy = "suppliers")
	private List<Inventory> inventories;
}
