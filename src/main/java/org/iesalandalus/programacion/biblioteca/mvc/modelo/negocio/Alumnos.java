package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;

public class Alumnos {

	private int capacidad;
	private int tamano;
	private Alumno[] coleccionAlumnos;

	public Alumnos(int capacidad)
	{
		if (capacidad<=0)
		{
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionAlumnos = new Alumno[capacidad];
		tamano = 0;
	}

	public Alumno[] get() {
		return copiaProfundaAlumnos();
	}

	private Alumno[] copiaProfundaAlumnos()
	{
		Alumno[] copiaAlumnos = new Alumno[capacidad];
		for (int i=0; !tamanoSuperado(i); ++i)
		{
			copiaAlumnos[i] = new Alumno(coleccionAlumnos[i]);
		}
		return copiaAlumnos;
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) 
		{
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}
		int indice = buscarIndice(alumno);
		if (capacidadSuperada(indice)) 
		{
			throw new OperationNotSupportedException("ERROR: No se aceptan más alumnos.");
		} 
		if (tamanoSuperado(indice)) 
		{
			coleccionAlumnos[indice] = new Alumno(alumno);
			tamano++;
		} 
		else 
		{
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese correo.");
		}		

	}

	private int buscarIndice(Alumno alumno) {
		int indice = 0;
		boolean alumnoEncontrado = false;
		while (!tamanoSuperado(indice) && !alumnoEncontrado)
		{
			if (coleccionAlumnos[indice].equals(alumno)) 
			{
				alumnoEncontrado = true;
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

	public Alumno buscar(Alumno alumno) {
		if (alumno == null) 
		{
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}
		int indice = buscarIndice(alumno);
		if (tamanoSuperado(indice)) 
		{
			return null;
		}
		else 
		{
			return new Alumno(coleccionAlumnos[indice]);
		}
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		if (alumno == null) 
		{
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}
		int indice = buscarIndice(alumno);
		if (tamanoSuperado(indice)) 
		{
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese correo.");
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
			coleccionAlumnos[i] = coleccionAlumnos[i+1];
		}
		coleccionAlumnos[i] = null;
		tamano--;
	}

}
