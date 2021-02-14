package org.iesalandalus.programacion.biblioteca.mvc.controlador;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.Modelo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.vista.Vista;

public class Controlador {

	private Vista vista;
	private Modelo modelo;

	public Controlador(Modelo modelo, Vista vista) {
		if (modelo == null) {
			throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
		}
		if (vista == null) {
			throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
		}
		this.modelo = modelo;
		this.vista = vista;
		this.vista.setControlador(this);
	}

	public void comenzar() {
		vista.comenzar();
	}

	public void terminar() {
		System.out.println("Que descanses!!!");
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		modelo.insertar(alumno);
	}

	public void insertar(Libro libro) throws OperationNotSupportedException {
		modelo.insertar(libro);
	}

	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {
		modelo.prestar(prestamo);
	}

	public void devolver(Prestamo prestamo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		modelo.devolver(prestamo, fechaDevolucion);
	}

	public Alumno buscar(Alumno alumno) {
		return modelo.buscar(alumno);
	}

	public Libro buscar(Libro libro) {
		return modelo.buscar(libro);
	}

	public Prestamo buscar(Prestamo prestamo) {
		return modelo.buscar(prestamo);
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		modelo.borrar(alumno);
	}

	public void borrar(Libro libro) throws OperationNotSupportedException {
		modelo.borrar(libro);
	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		modelo.borrar(prestamo);
	}

	public Alumno[] getAlumnos() {
		return modelo.getAlumnos();
	}

	public Libro[] getLibros() {
		return modelo.getLibros();
	}

	public Prestamo[] getPrestamos() {
		return modelo.getPrestamos();
	}

	public Prestamo[] getPrestamos(Alumno alumno) {
		return modelo.getPrestamos(alumno);
	}

	public Prestamo[] getPrestamos(Libro libro) {
		return modelo.getPrestamos(libro);
	}

	public Prestamo[] getPrestamos(LocalDate fechaPrestamo) {
		return modelo.getPrestamos(fechaPrestamo);
	}

}
