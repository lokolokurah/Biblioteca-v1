package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;

public class Libros {
	
	private List<Libro> coleccionLibros;

	public Libros()
	{
		coleccionLibros = new ArrayList<>();
	}
	
	public List<Libro> get() {
		List<Libro> librosOrdenados = copiaProfundaLibros();
		librosOrdenados.sort(Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor));
		return librosOrdenados;
	}

	private List<Libro> copiaProfundaLibros()
	{
		List<Libro> copiaLibros = new ArrayList<>();
		for (Libro libro : coleccionLibros) {
			copiaLibros.add(new Libro(libro));
		}
		return copiaLibros;
	}

	public int getTamano() {
		return coleccionLibros.size();
	}
	
	public void insertar(Libro libro) throws OperationNotSupportedException {
		if (libro == null) {
			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");
		}
		int indice = coleccionLibros.indexOf(libro);
		if (indice == -1) {
			coleccionLibros.add(libro);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un libro con ese título y autor.");
		}
	}

	public Libro buscar(Libro libro) {
		if (libro == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un libro nulo.");
		}
		int indice = coleccionLibros.indexOf(libro);
		if (indice == -1) {
			return null;
		} else {
			return new Libro(coleccionLibros.get(indice));
		}	
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {
		if (libro == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un libro nulo.");
		}
		int indice = coleccionLibros.indexOf(libro);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún libro con ese título y autor.");
		} else {
			coleccionLibros.remove(libro);
		}
	}
	
}
