package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Prestamos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.Libros;

public class Modelo {

	private static final int CAPACIDAD = 100;
	private Alumnos alumnos;
	private Prestamos prestamos;
	private Libros libros;

	public Modelo() {
		alumnos = new Alumnos(CAPACIDAD);
		prestamos = new Prestamos(CAPACIDAD);
		libros = new Libros(CAPACIDAD);
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.insertar(alumno);
	}

	public void insertar(Libro libro) throws OperationNotSupportedException {
		libros.insertar(libro);
	}

	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo==null) 
		{
			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}
		Alumno alumno = alumnos.buscar(prestamo.getAlumno());
		if (alumno==null)
		{
			throw new OperationNotSupportedException("ERROR: No existe el alumno del préstamo.");
		}
		Libro libro = libros.buscar(prestamo.getLibro());
		if (libro==null) 
		{
			throw new OperationNotSupportedException("ERROR: No existe el libro del préstamo.");
		}
		prestamos.prestar(new Prestamo(alumno, libro, prestamo.getFechaPrestamo()));
	}

	public void devolver(Prestamo prestamo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (prestamo==null) 
		{
			throw new NullPointerException("ERROR: No se puede devolver un préstamo nulo.");
		}
		if (fechaDevolucion==null)
		{
			throw new NullPointerException("ERROR: La fecha de devolución del prestamo no puede ser nula.");
		}
		prestamo = prestamos.buscar(prestamo);
		if (prestamo==null)
		{
			throw new OperationNotSupportedException("ERROR: No se puede devolver un préstamo no prestado.");
		}
		prestamos.devolver(prestamo, fechaDevolucion);
	}

	public Alumno buscar(Alumno alumno) {
		return alumnos.buscar(alumno);
	}

	public Libro buscar(Libro libro) {
		return libros.buscar(libro);
	}

	public Prestamo buscar(Prestamo prestamo) {
		return prestamos.buscar(prestamo);
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.borrar(alumno);
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {
		libros.borrar(libro);
	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		prestamos.borrar(prestamo);
	}

	public Alumno[] getAlumnos() {
		return alumnos.get();
	}

	public Libro[] getLibros() {
		return libros.get();
	}

	public Prestamo[] getPrestamos() {
		return prestamos.get();
	}

	public Prestamo[] getPrestamos(Alumno alumno) {
		return prestamos.get(alumno);
	}

	public Prestamo[] getPrestamos(Libro libro) {
		return prestamos.get(libro);
	}

	public Prestamo[] getPrestamos(LocalDate fechaPrestamo) {
		return prestamos.get(fechaPrestamo);
	}

}
