package com.pearlpay.training.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class BaseEntityDTO {

	private Long id;
	private LocalDate dateCreated;
	
}
