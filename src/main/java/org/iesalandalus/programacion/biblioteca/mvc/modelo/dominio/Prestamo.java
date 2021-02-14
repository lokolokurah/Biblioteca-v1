package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Prestamo {

	private static final int MAX_DIAS_PRESTAMO = 20;
	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
	private Alumno alumno;
	private Libro libro;

	public Prestamo(Alumno alumno, Libro libro, LocalDate fechaPrestamo)
	{
		if (alumno==null)
		{
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		if (libro==null)
		{
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		setAlumno(new Alumno(alumno));
		setLibro(new Libro(libro));
		setFechaPrestamo(fechaPrestamo);
	}

	public Prestamo(Prestamo copiaPrestamo)
	{
		if (copiaPrestamo==null)
		{
			throw new NullPointerException("ERROR: No es posible copiar un préstamo nulo.");
		}
		setAlumno(new Alumno(copiaPrestamo.getAlumno()));
		setLibro(new Libro(copiaPrestamo.getLibro()));
		setFechaPrestamo(copiaPrestamo.getFechaPrestamo());
		if (copiaPrestamo.getFechaDevolucion()!=null)
		{
			setFechaDevolucion(copiaPrestamo.getFechaDevolucion());
		}
	}

	public static Prestamo getPrestamoFicticio(Alumno alumno, Libro libro)
	{
		if (alumno==null)
		{
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		if (libro==null)
		{
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		alumno = Alumno.getAlumnoFicticio(alumno.getCorreo());
		libro = Libro.getLibroFicticio(libro.getTitulo(), libro.getAutor());
		return new Prestamo(alumno, libro, 	LocalDate.now());
	}

	public void devolver(LocalDate fechaDevolucion)
	{
		if (getFechaDevolucion()!=null)
		{
			throw new IllegalArgumentException("ERROR: La devolución ya estaba registrada.");
		}
		if (fechaDevolucion==null)
		{
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}
		if (fechaDevolucion.isAfter(LocalDate.now()))
		{
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}
		if (fechaDevolucion.isBefore(getFechaPrestamo())||fechaDevolucion.isEqual(getFechaPrestamo()))
		{
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de préstamo.");
		}
		setFechaDevolucion(fechaDevolucion);
	}

	public int getPuntos()
	{
		if (getFechaDevolucion()!=null)
		{
			long daysBetween = ChronoUnit.DAYS.between(getFechaPrestamo(), getFechaDevolucion());
			if (daysBetween>MAX_DIAS_PRESTAMO)
			{
				return 0;
			}
			else
			{
				return Math.round(libro.getPuntos()/daysBetween);
			}
		}
		else
		{
			return 0;
		}
	}

	public Alumno getAlumno() {
		return alumno;
	}

	private void setAlumno(Alumno alumno) {
		if (alumno==null)
		{
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		this.alumno = alumno;
	}

	public Libro getLibro() {
		return libro;
	}

	private void setLibro(Libro libro) {
		if (libro==null)
		{
			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		this.libro = libro;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	private void setFechaPrestamo(LocalDate fechaPrestamo) {
		if (fechaPrestamo==null)
		{
			throw new NullPointerException("ERROR: La fecha de préstamo no puede ser nula.");
		}
		if (fechaPrestamo.isAfter(LocalDate.now()))
		{
			throw new IllegalArgumentException("ERROR: La fecha de préstamo no puede ser futura.");
		}
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	private void setFechaDevolucion(LocalDate fechaDevolucion) {
		if (fechaDevolucion==null)
		{
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}
		if (fechaDevolucion.isAfter(LocalDate.now()))
		{
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}
		this.fechaDevolucion = fechaDevolucion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alumno, libro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Prestamo)) {
			return false;
		}
		Prestamo other = (Prestamo) obj;
		return Objects.equals(alumno, other.alumno) && Objects.equals(libro, other.libro);
	}

	@Override
	public String toString() {
		if (fechaDevolucion==null)
		{
			return String.format("alumno=(%s), libro=(%s), fecha de préstamo=%s, puntos=%d", alumno, libro, fechaPrestamo.format(Prestamo.FORMATO_FECHA), getPuntos());
		}
		else
		{
			return String.format("alumno=(%s), libro=(%s), fecha de préstamo=%s, fecha de devolución=%s, puntos=%d", alumno, libro, fechaPrestamo.format(Prestamo.FORMATO_FECHA), fechaDevolucion.format(Prestamo.FORMATO_FECHA), getPuntos());
		}
	}

}
