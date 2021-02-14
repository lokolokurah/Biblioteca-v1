package org.iesalandalus.programacion.biblioteca.mvc.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {	
	}

	public static void mostrarMenu() {
		for (Opcion opcion: Opcion.values()) {
			System.out.println("\n"+opcion);
		}
	}

	public static void mostrarCabecera(String mensajeCabecera) {
		System.out.printf("%n%s%n", mensajeCabecera);
		String formatoStr = "%0" + mensajeCabecera.length() + "d%n";
		System.out.print(String.format(formatoStr, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.print("\nElige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}

	public static Alumno leerAlumno() {
		Alumno alumno = null;
		Curso curso = null;
		String nombre;
		String correo;
		int numeroCurso;
		System.out.printf("\nIntroduce el nombre del alumno: ");
		nombre = Entrada.cadena();
		System.out.print("Introduce el correo del alumno: ");
		correo = Entrada.cadena();
		do {
			System.out.print("Introduce el curso del alumno: [1, 2, 3, 4] ");
			numeroCurso = Entrada.entero();
		} while (numeroCurso<1 || numeroCurso>4);
		switch (numeroCurso) {
		case 1: 
			curso = Curso.PRIMERO;
			break;
		case 2:
			curso = Curso.SEGUNDO;
			break;
		case 3:
			curso = Curso.TERCERO;
			break;
		case 4:
			curso = Curso.CUARTO;
			break;	
		}
		alumno = new Alumno(nombre, correo, curso);
		return alumno;
	}

	public static Alumno leerAlumnoFicticio() {
		System.out.print("\nIntroduce el correo del alumno: ");
		return Alumno.getAlumnoFicticio(Entrada.cadena());
	}

	public static Libro leerLibro() {
		System.out.print("\nIntroduce el titulo del libro: ");
		String titulo = Entrada.cadena();
		System.out.print("Introduce el autor del libro: ");
		String autor = Entrada.cadena();
		System.out.print("Introduce el número de páginas del libro: ");
		int numPaginas = Entrada.entero();
		return new Libro(titulo, autor, numPaginas);
	}

	public static Libro leerLibroFicticio() {
		System.out.print("\nIntroduce el titulo del libro: ");
		String titulo = Entrada.cadena();
		System.out.print("Introduce el autor del libro: ");
		String autor = Entrada.cadena();
		return Libro.getLibroFicticio(titulo, autor);
	}

	public static Prestamo leerPrestamo() {
		return new Prestamo(leerAlumno(), leerLibro(), leerFecha());
	}

	public static Prestamo leerPrestamoFicticio() {
		return Prestamo.getPrestamoFicticio(leerAlumnoFicticio(), leerLibroFicticio());
	}

	public static LocalDate leerFecha() {
		LocalDate fecha = null;
		String cadenaFormato = "dd/MM/yyyy";
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(cadenaFormato);
		System.out.printf("\nIntroduce la fecha (%s): ", formatoFecha);
		String diaLeido = Entrada.cadena();
		try {
			fecha = LocalDate.parse(diaLeido, formatoFecha);
		} catch (DateTimeParseException e) {
			System.out.println("ERROR: El formato de la fecha no es correcto.");
		}
		return fecha;
	}

}
