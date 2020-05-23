package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="public", name="computadora")
public class Computadora {
	
	@Id
	@Column(name="id_computadora")
	private Integer codigoComputadora;
	
	@Column(name="marca")
	private String marca;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_estudiante")
	private Estudiante estudiante;

	public Integer getCodigoComputadora() {
		return codigoComputadora;
	}

	public void setCodigoComputadora(Integer codigoComputadora) {
		this.codigoComputadora = codigoComputadora;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	

}
