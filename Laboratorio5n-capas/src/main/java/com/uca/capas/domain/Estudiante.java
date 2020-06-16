package com.uca.capas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public",name="estudiante")
public class Estudiante {
	
	@Id
	@Column(name="id_estudiante")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoEstudiante;
	
	@Size(message="El campo no debe contener mas de 30 caracteres", max=30)
	@NotEmpty(message="Este campo no puede estar vacio")
	@Column(name="nombre")
	private String nombre;
	
	@Size(message="El campo no debe contener mas de 30 caracteres", max=30)
	@NotEmpty(message="Este campo no puede estar vacio")
	@Column(name="apellido")
	private String apellido;
	
	
	@Column(name="edad")
	private Integer edad;
	
	
	@Column(name="estado")
	private Boolean estado;
	
	@OneToMany(mappedBy="estudiante",fetch=FetchType.EAGER)
	private List<Computadora> computadoras;
	
	
	
	public List<Computadora> getComputadoras() {
		return computadoras;
	}
	public void setComputadoras(List<Computadora> computadoras) {
		this.computadoras = computadoras;
	}
	public Integer getCodigoEstudiante() {
		return codigoEstudiante;
	}
	public void setCodigoEstudiante(Integer codigoEstudiante) {
		this.codigoEstudiante = codigoEstudiante;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		edad = edad;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		estado = estado;
	}
	
	public String getEstadoDelegate() {
		if(this.estado==null) return"";
		else {
			return estado == true?"Ativo":"Inactivo";
		}
	}
	
	public Estudiante() {
		
	}

}
