package com.sandra.BibliotecaH2.entidades;

// default package
// Generated 15 mar 2023 10:32:13 by Hibernate Tools 6.1.5.Final

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Autores generated by hbm2java
 */
public class Autores implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cod;
	private String nombre;
	private Set<Libros> libroses = new HashSet<Libros>(0);

	public Autores() {
	}

	public Autores(String cod) {
		this.cod = cod;
	}

	public Autores(String cod, String nombre, Set<Libros> libroses) {
		this.cod = cod;
		this.nombre = nombre;
		this.libroses = libroses;
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Libros> getLibroses() {
		return this.libroses;
	}

	public void setLibroses(Set<Libros> libroses) {
		this.libroses = libroses;
	}

	@Override
	public String toString() {
		return nombre+"\n· Código: "+cod+"\n· "+(libroses.size() == 0 ? "Este autor no ha escrito ningún libro" :
			"Lista de sus libros: "+libroses.stream().map(Libros::getTitulo).collect(Collectors.joining(", ")));
	}
	
	
}
