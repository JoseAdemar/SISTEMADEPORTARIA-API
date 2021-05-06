package com.sistemadeportaria.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Visita {

	 @EqualsAndHashCode.Include
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Id
	 private Long id;
	 
	 //@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	 //@NotNull
	 private LocalDateTime dataDaVisita;
	 
	 @NotNull
	 private String setor;
	 
	 private String observacao;
     
	 
	 @NotNull
	 @ManyToOne
	 @JoinColumn(name = "visitante_id")
     private Visitante visitante;
	 	 
}
