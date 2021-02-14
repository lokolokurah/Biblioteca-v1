package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.time.LocalDate;
import java.time.Month;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public class Prestamos {

	private int capacidad;
	private int tamano;
	private Prestamo[] coleccionPrestamos;

	public Prestamos(int capacidad)
	{
		if (capacidad<=0)
		{
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		this.capacidad = capacidad;
		coleccionPrestamos = new Prestamo[capacidad];
		tamano = 0;
	}

	public Prestamo[] get() {
		return copiaProfundaPrestamos();
	}

	private Prestamo[] copiaProfundaPrestamos()
	{
		Prestamo[] copiaPrestamos = new Prestamo[capacidad];
		for (int i=0; !tamanoSuperado(i); ++i)
		{
			copiaPrestamos[i] = new Prestamo(coleccionPrestamos[i]);
		}
		return copiaPrestamos;
	}

	public Prestamo[] get(Alumno alumno)
	{
		if (alumno==null)
		{
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		Prestamo[] prestamosAlumno = new Prestamo[capacidad];
		int j = 0;
		for (int i=0; !tamanoSuperado(i); ++i)
		{
			if (coleccionPrestamos[i].getAlumno().equals(alumno))
			{
				prestamosAlumno[j++] = new Prestamo(coleccionPrestamos[i]);
			}
		}
		return prestamosAlumno;
	}

	public Prestamo[] get(Libro libro)
	{
		if (libro==null)
		{
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		Prestamo[] prestamosLibro = new Prestamo[capacidad];
		int j = 0;
		for (int i=0; !tamanoSuperado(i); ++i)
		{
			if (coleccionPrestamos[i].getLibro().equals(libro))
			{
				prestamosLibro[j++] = new Prestamo(coleccionPrestamos[i]);
			}
		}
		return prestamosLibro;
	}

	public Prestamo[] get(LocalDate fechaPrestamo)
	{
		if (fechaPrestamo==null)
		{
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		Prestamo[] prestamosFecha = new Prestamo[capacidad];
		int j = 0;
		for (int i=0; !tamanoSuperado(i); ++i)
		{
			if (mismoMes(coleccionPrestamos[i].getFechaPrestamo(), fechaPrestamo))
			{
				prestamosFecha[j++] = new Prestamo(coleccionPrestamos[i]);
			}
		}
		return prestamosFecha;
	}

	private boolean mismoMes(LocalDate fechaInicial, LocalDate fechaFinal)
	{
		boolean fecha = false;
		Month mes = fechaInicial.getMonth();
		int anio = fechaInicial.getYear();
		if (mes.equals(fechaFinal.getMonth()) && anio==fechaFinal.getYear())
		{
			fecha = true;
		}
		return fecha;
	}

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void prestar(Prestamo prestamo) throws OperationNotSupportedException
	{
		if (prestamo == null) 
		{
			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}
		int indice = buscarIndice(prestamo);
		if (capacidadSuperada(indice)) 
		{
			throw new OperationNotSupportedException("ERROR: No se aceptan más préstamos.");
		} 
		if (tamanoSuperado(indice)) 
		{
			coleccionPrestamos[indice] = new Prestamo(prestamo);
			tamano++;
		} 
		else 
		{
			throw new OperationNotSupportedException("ERROR: Ya existe un préstamo igual.");
		}	
	}

	public void devolver(Prestamo prestamo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (prestamo == null) 
		{
			throw new NullPointerException("ERROR: No se puede devolver un préstamo nulo.");
		}
		if (fechaDevolucion==null)
		{
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		int indice = buscarIndice(prestamo);
		if (tamanoSuperado(indice)) 
		{
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		} 
		else 
		{
			coleccionPrestamos[indice].devolver(fechaDevolucion);
		}		
	}

	private int buscarIndice(Prestamo prestamo) {
		int indice = 0;
		boolean prestamoEncontrado = false;
		while (!tamanoSuperado(indice) && !prestamoEncontrado)
		{
			if (coleccionPrestamos[indice].equals(prestamo)) 
			{
				prestamoEncontrado = true;
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

	public Prestamo buscar(Prestamo prestamo) {
		if (prestamo == null) 
		{
			throw new IllegalArgumentException("ERROR: No se puede buscar un préstamo nulo.");
		}
		int indice = buscarIndice(prestamo);
		if (tamanoSuperado(indice)) 
		{
			return null;
		}
		else 
		{
			return new Prestamo(coleccionPrestamos[indice]);
		}
	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo == null) 
		{
			throw new IllegalArgumentException("ERROR: No se puede borrar un préstamo nulo.");
		}
		int indice = buscarIndice(prestamo);
		if (tamanoSuperado(indice)) 
		{
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
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
			coleccionPrestamos[i] = coleccionPrestamos[i+1];
		}
		coleccionPrestamos[i] = null;
		tamano--;
	}

}
