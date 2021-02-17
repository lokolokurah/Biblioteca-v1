package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;

public class Prestamos {

	private List<Prestamo> coleccionPrestamos;

	public Prestamos() {
		coleccionPrestamos = new ArrayList<>();
	}

	public List<Prestamo> get() {
		List<Prestamo> prestamosOrdenados = copiaProfundaPrestamos();
		Comparator<Alumno> comparadorAlumno = Comparator.comparing(Alumno::getNombre);
		Comparator<Libro> comparadorLibro = Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor);
		prestamosOrdenados.sort(Comparator.comparing(Prestamo::getFechaPrestamo).thenComparing(Prestamo::getAlumno, comparadorAlumno).thenComparing(Prestamo::getLibro, comparadorLibro));
		return prestamosOrdenados;
	}

	private List<Prestamo> copiaProfundaPrestamos() {
		List<Prestamo> copiaPrestamos = new ArrayList<>();
		for (Prestamo prestamo : coleccionPrestamos) {
			copiaPrestamos.add(new Prestamo(prestamo));
		}
		return copiaPrestamos;
	}

	public int getTamano() {
		return coleccionPrestamos.size();
	}

	public List<Prestamo> get(Alumno alumno) {
		if (alumno==null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		List<Prestamo> prestamosAlumno = new ArrayList<>();
		for (Prestamo prestamo : coleccionPrestamos) {
			if (prestamo.getAlumno().equals(alumno)) {
				prestamosAlumno.add(new Prestamo(prestamo));
			}
		}
		Comparator<Alumno> comparadorAlumno = Comparator.comparing(Alumno::getNombre);
		Comparator<Libro> comparadorLibro = Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor);
		prestamosAlumno.sort(Comparator.comparing(Prestamo::getFechaPrestamo).thenComparing(Prestamo::getAlumno, comparadorAlumno).thenComparing(Prestamo::getLibro, comparadorLibro));
		return prestamosAlumno;
	}

	public List<Prestamo> get(Libro libro) {
		if (libro==null) {
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		List<Prestamo> prestamosLibro = new ArrayList<>();
		for (Prestamo prestamo : coleccionPrestamos) {
			if (prestamo.getLibro().equals(libro)) {
				prestamosLibro.add(new Prestamo(prestamo));
			}
		}
		Comparator<Alumno> comparadorAlumno = Comparator.comparing(Alumno::getNombre);
		Comparator<Libro> comparadorLibro = Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor);
		prestamosLibro.sort(Comparator.comparing(Prestamo::getFechaPrestamo).thenComparing(Prestamo::getAlumno, comparadorAlumno).thenComparing(Prestamo::getLibro, comparadorLibro));
		return prestamosLibro;
	}

	public List<Prestamo> get(LocalDate fechaPrestamo) {
		if (fechaPrestamo==null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		List<Prestamo> prestamosFecha = new ArrayList<>();
		for (Prestamo prestamo : coleccionPrestamos) {
			if (mismoMes(prestamo.getFechaPrestamo(), fechaPrestamo)) {
				prestamosFecha.add(new Prestamo(prestamo));
			}
		}
		Comparator<Alumno> comparadorAlumno = Comparator.comparing(Alumno::getNombre);
		Comparator<Libro> comparadorLibro = Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor);
		prestamosFecha.sort(Comparator.comparing(Prestamo::getFechaPrestamo).thenComparing(Comparator.comparing(Prestamo::getAlumno, comparadorAlumno).thenComparing(Prestamo::getLibro, comparadorLibro)));
		return prestamosFecha;
	}

	public Map<Curso, Integer> getEstadisticaMensualPorCurso(LocalDate fecha) {
		Map<Curso, Integer>estadisticasMensualesPorCurso = inicializarEstadisticas();
		List<Prestamo> prestamosMensuales = get(fecha);
		for (Prestamo prestamo : prestamosMensuales) {
			Curso cursoAlumno = prestamo.getAlumno().getCurso();
			estadisticasMensualesPorCurso.put(cursoAlumno, estadisticasMensualesPorCurso.get(cursoAlumno) + prestamo.getPuntos());
		}
		return estadisticasMensualesPorCurso;
	}

	private Map<Curso, Integer> inicializarEstadisticas() {
		Map<Curso, Integer>map = new EnumMap<>(Curso.class);
		for (Curso curso : Curso.values()) {
			map.put(curso, 0);
		}
		return map;
	}

	private boolean mismoMes(LocalDate fechaInicial, LocalDate fechaFinal) {
		boolean fecha = false;
		Month mes = fechaInicial.getMonth();
		int anio = fechaInicial.getYear();
		if (mes.equals(fechaFinal.getMonth()) && anio==fechaFinal.getYear()) {
			fecha = true;
		}
		return fecha;
	}

	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo == null) {
			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}
		int indice = coleccionPrestamos.indexOf(prestamo);
		if (indice == -1) {
			coleccionPrestamos.add(prestamo);
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un préstamo igual.");
		}
	}

	public void devolver(Prestamo prestamo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (prestamo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un préstamo nulo.");
		}
		if (fechaDevolucion==null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		int indice = coleccionPrestamos.indexOf(prestamo);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		} else {
			coleccionPrestamos.get(indice).devolver(fechaDevolucion);
		}	
	}

	public Prestamo buscar(Prestamo prestamo) {
		if (prestamo == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un préstamo nulo.");
		}
		int indice = coleccionPrestamos.indexOf(prestamo);
		if (indice == -1) {
			return null;
		} else {
			return new Prestamo(coleccionPrestamos.get(indice));
		}
	}

	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {
		if (prestamo == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un préstamo nulo.");
		}
		int indice = coleccionPrestamos.indexOf(prestamo);
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");
		} else {
			coleccionPrestamos.remove(prestamo);
		}
	}

}
