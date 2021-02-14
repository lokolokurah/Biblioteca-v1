package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;

public class Libros {

	private int capacidad;
	private int tamano;
	private Libro[] coleccionLibros;

	public Libros(int capacidad)
	{
		if (capacidad<=0)
		{
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionLibros = new Libro[capacidad];
		tamano = 0;
	}

	public Libro[] get() {
		return copiaProfundaLibros();
	}

	private Libro[] copiaProfundaLibros()
	{
		Libro[] copiaLibros = new Libro[capacidad];
		for (int i=0; !tamanoSuperado(i); ++i)
		{
			copiaLibros[i] = new Libro(coleccionLibros[i]);
		}
		return copiaLibros;
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void insertar(Libro libro) throws OperationNotSupportedException {
		if (libro == null) 
		{
			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");
		}
		int indice = buscarIndice(libro);
		if (capacidadSuperada(indice)) 
		{
			throw new OperationNotSupportedException("ERROR: No se aceptan más libros.");
		} 
		if (tamanoSuperado(indice)) 
		{
			coleccionLibros[indice] = new Libro(libro);
			tamano++;
		} 
		else 
		{
			throw new OperationNotSupportedException("ERROR: Ya existe un libro con ese título y autor.");
		}		

	}

	private int buscarIndice(Libro libro) {
		int indice = 0;
		boolean libroEncontrado = false;
		while (!tamanoSuperado(indice) && !libroEncontrado)
		{
			if (coleccionLibros[indice].equals(libro)) 
			{
				libroEncontrado = true;
			} 
			else 
			{
				indice++;
			}
		}
		return indice;
	}

	private boolean tamanoSuperado(int indice) {
		return indice >= tamano;
	}

	private boolean capacidadSuperada(int indice) {
		return indice >= capacidad;
	}

	public Libro buscar(Libro libro) {
		if (libro == null) 
		{
			throw new IllegalArgumentException("ERROR: No se puede buscar un libro nulo.");
		}
		int indice = buscarIndice(libro);
		if (tamanoSuperado(indice)) 
		{
			return null;
		}
		else 
		{
			return new Libro(coleccionLibros[indice]);
		}
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {
		if (libro == null) 
		{
			throw new IllegalArgumentException("ERROR: No se puede borrar un libro nulo.");
		}
		int indice = buscarIndice(libro);
		if (tamanoSuperado(indice)) 
		{
			throw new OperationNotSupportedException("ERROR: No existe ningún libro con ese título y autor.");
		} 
		else 
		{
			desplazarUnaPosicionHaciaIzquierda(indice);
		}
	}

	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		int i;
		for (i = indice; !tamanoSuperado(i); ++i) 
		{
			coleccionLibros[i] = coleccionLibros[i+1];
		}
		coleccionLibros[i] = null;
		tamano--;
	}
	
}
