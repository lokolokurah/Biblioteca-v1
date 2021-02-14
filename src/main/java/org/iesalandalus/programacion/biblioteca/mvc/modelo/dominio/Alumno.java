package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

import java.util.Objects;

public class Alumno {

	private static final String ER_NOMBRE = "[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+\s{1}[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+";
	private static final String ER_CORREO = "[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)+"; 
	private String nombre;
	private String correo;
	private Curso curso;
	
	public Alumno(String nombre, String correo, Curso curso) {
		setNombre(nombre);
		setCorreo(correo);
		setCurso(curso);
	}
	
	public Alumno(Alumno copiaAlumno)
	{
		if (copiaAlumno==null)
		{
			throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
		}
		setNombre(copiaAlumno.getNombre());
		setCorreo(copiaAlumno.getCorreo());
		setCurso(copiaAlumno.getCurso());
	}
	
	public static Alumno getAlumnoFicticio(String correo)
	{
		return new Alumno("Espartaco el Conquistador", correo, Curso.CUARTO);
	}

	public String getNombre() {
		return formateaNombre(nombre);
	}

	private void setNombre(String nombre) {
		if (nombre==null)
		{
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if (!formateaNombre(nombre).matches(ER_NOMBRE))
		{
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}
		this.nombre = nombre;
	}

	public static String formateaNombre(String nombre)
	{
		String nombreFormateado = "";
		nombre = nombre.replaceAll("( +)"," ").trim();
		if (!nombre.equals(""))
		{
			String[] words = nombre.split(" ");
			for (int i=0; i<words.length; ++i)
			{
				nombreFormateado += words[i].substring(0,1).toUpperCase()+words[i].substring(1).toLowerCase()+" ";
			}
		}
		return nombreFormateado.trim();	
	}
	
	public String getCorreo() {
		return correo;
	}

	private void setCorreo(String correo) {
		if (correo==null)
		{
			throw new NullPointerException("ERROR: El correo no puede ser nulo.");
		}
		if (!correo.matches(ER_CORREO))
		{
			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		}
		this.correo = correo;
	}

	private String getIniciales()
	{
		String iniciales = "";
		nombre = nombre.replaceAll("( +)"," ").trim();
		String[] words = nombre.split(" ");
		for (int i=0; i<words.length; ++i)
		{
			iniciales += words[i].substring(0,1).toUpperCase();
		}
		return iniciales;
	}
	
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		if (curso==null)
		{
			throw new NullPointerException("ERROR: El curso no puede ser nulo.");
		}
		this.curso = curso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Alumno)) {
			return false;
		}
		Alumno other = (Alumno) obj;
		return Objects.equals(correo, other.correo);
	}

	@Override
	public String toString() {
		return String.format("nombre=%s, correo=%s, curso=%s", formateaNombre(nombre)+" ("+getIniciales()+(")"), correo, curso);
	}
	
	
}
