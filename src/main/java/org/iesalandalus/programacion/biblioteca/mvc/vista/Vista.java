package org.iesalandalus.programacion.biblioteca.mvc.vista;

import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.Controlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public class Vista {

	private Controlador controlador;

	public Vista() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {
		Consola.mostrarCabecera("Gestión de la Biblioteca del IES Al-Ándalus");
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOridnal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	public void terminar() {
		controlador.terminar();
	}

	public void insertarAlumno() {
		Consola.mostrarCabecera("Insertar Alumno");
		try {
			controlador.insertar(Consola.leerAlumno());
			System.out.println("Alumno insertado correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAlumno() {
		Consola.mostrarCabecera("Buscar Alumno");
		Alumno alumno;
		try {
			alumno = controlador.buscar(Consola.leerAlumnoFicticio());
			String mensaje = (alumno != null) ? alumno.toString() : "No existe dicho alumno.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlumno() {
		Consola.mostrarCabecera("Borrar Alumno");
		try {
			controlador.borrar(Consola.leerAlumnoFicticio());
			System.out.println("Alumno borrado satisfactoriamente.");
		}  catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlumnos() {
		Consola.mostrarCabecera("Listado de Alumnos");
		List<Alumno> alumnos = controlador.getAlumnos();
		if (!alumnos.isEmpty()) {
			for (Alumno alumno : alumnos) {
				if (alumno != null) 
					System.out.println(alumno);
			}
		} else {
			System.out.println("\nNo hay alumnos que mostrar.");
		}
	}

	public void insertarLibro() {
		Consola.mostrarCabecera("Insertar Libro");
		try {
			controlador.insertar(Consola.leerLibro());
			System.out.println("Libro insertada correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarLibro() {
		Consola.mostrarCabecera("Buscar Libro");
		Libro libro;
		try {
			libro = controlador.buscar(Consola.leerLibroFicticio());
			String mensaje = (libro != null) ? libro.toString() : "No existe dicho libro.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarLibro() {
		Consola.mostrarCabecera("Borrar Libro");
		try {
			controlador.borrar(Consola.leerLibroFicticio());
			System.out.println("Libro borrado satisfactoriamente.");
		}  catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarLibros() {
		Consola.mostrarCabecera("Listado de Libros");
		List<Libro> libros = controlador.getLibros();
		if (!libros.isEmpty()) {
			for (Libro libro : libros) {
				if (libro != null) 
					System.out.println(libro);
			}
		} else {
			System.out.println("\nNo hay libros que mostrar.");
		}
	}

	public void prestarLibro() {
		Consola.mostrarCabecera("Prestar Libro");
		try {
			controlador.prestar(Consola.leerPrestamo());
			System.out.println("Libro prestado satisfactoriamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void devolverLibro() {
		Consola.mostrarCabecera("Devolver Libro");
		try {
			controlador.devolver(Consola.leerPrestamoFicticio(), Consola.leerFecha());
			System.out.println("Libro devuelto satisfactoriamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarPrestamo() {
		Consola.mostrarCabecera("Buscar Prestamo");
		Prestamo prestamo;
		try {
			prestamo = controlador.buscar(Consola.leerPrestamoFicticio());
			String mensaje = (prestamo != null) ? prestamo.toString() : "No existe dicho prestamo.";
			System.out.println(mensaje);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarPrestamo() {
		Consola.mostrarCabecera("Borrar Prestamo");
		try {
			controlador.borrar(Consola.leerPrestamoFicticio());
			System.out.println("Prestamo borrado satisfactoriamente.");
		}  catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarPrestamos() {
		Consola.mostrarCabecera("Listado de Prestamos");
		List<Prestamo> prestamos = controlador.getPrestamos();
		if (!prestamos.isEmpty()) {
			for (Prestamo prestamo : prestamos) {
				if (prestamo != null) 
					System.out.println(prestamo);
			}
		} else {
			System.out.println("\nNo hay prestamos que mostrar.");
		}
	}

	public void listarPrestamosAlumno() {
		Consola.mostrarCabecera("Listado de Prestamos por Alumno");
		try {
			List<Prestamo> prestamos = controlador.getPrestamos(Consola.leerAlumnoFicticio());
			if (!prestamos.isEmpty()) {
				for (Prestamo prestamo : prestamos) {
					if (prestamo != null) 
						System.out.println(prestamo);
				}
			} else {
				System.out.println("No hay prestamos a mostrar para dicho alumno.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarPrestamosLibro() {
		Consola.mostrarCabecera("Listado de Prestamos por Libro");
		try {
			List<Prestamo> prestamos = controlador.getPrestamos(Consola.leerLibroFicticio());
			if (!prestamos.isEmpty()) {
				for (Prestamo prestamo : prestamos) {
					if (prestamo != null) 
						System.out.println(prestamo);
				}
			} else {
				System.out.println("No hay prestamos a mostrar para dicho libro.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarPrestamosFecha() {
		Consola.mostrarCabecera("Listado de Prestamos por Fecha");
		try {
			List<Prestamo> prestamos = controlador.getPrestamos(Consola.leerFecha());
			if (!prestamos.isEmpty()) {
				for (Prestamo prestamo : prestamos) {
					if (prestamo != null) 
						System.out.println(prestamo);
				}
			} else {
				System.out.println("No hay prestamos a mostrar para dicho libro.");
			}
		} catch (NullPointerException e) {
			e.getMessage();
		}
	}

	public void mostrarEstadisticaMensualPorCurso()
	{
		Consola.mostrarCabecera("Estadistica mensual por curso");
		try {
			Map<Curso, Integer> estadisticasMensualesPorCurso = controlador.getEstadisticaMensualPorCurso(Consola.leerFecha());
			if (!estadisticasMensualesPorCurso.isEmpty()) {
				System.out.println(estadisticasMensualesPorCurso);
			} else {
				System.out.println("No hay estadisticas mensuales a mostrar para ese mes.");
			}
		} catch (NullPointerException e) {
			e.getMessage();
		}
	}
}
