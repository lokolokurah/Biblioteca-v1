package org.iesalandalus.programacion.biblioteca.mvc.vista;

public enum Opcion {

	INSERTAR_ALUMNO("Insertar Alumno") {
		public void ejecutar() {
			vista.insertarAlumno();
		}
	},
	BUSCAR_ALUMNO("Buscar Alumno") {
		public void ejecutar() {
			vista.buscarAlumno();
		}
	},
	BORRAR_ALUMNO("Borrar Alumno") {
		public void ejecutar() {
			vista.borrarAlumno();
		}
	},
	LISTAR_ALUMNOS("Listar Alumnos") {
		public void ejecutar() {
			vista.listarAlumnos();
		}
	},
	INSERTAR_LIBRO("Insertar Libro") {
		public void ejecutar() {
			vista.insertarLibro();
		}
	},
	BUSCAR_LIBRO("Buscar Libro") {
		public void ejecutar() {
			vista.buscarLibro();
		}
	},
	BORRAR_LIBRO("Borrar Libro") {
		public void ejecutar() {
			vista.borrarLibro();
		}
	},
	LISTAR_LIBROS("Listar Libros") {
		public void ejecutar() {
			vista.listarLibros();
		}
	},
	PRESTAR_LIBRO("Prestar Libro") {
		public void ejecutar() {
			vista.prestarLibro();
		}
	},
	DEVOLVER_LIBRO("Devolver Libro") {
		public void ejecutar() {
			vista.devolverLibro();
		}
	},
	BUSCAR_PRESTAMO("Buscar Prestamo") {
		public void ejecutar() {
			vista.buscarPrestamo();
		}
	},
	BORRAR_PRESTAMO("Borrar Prestamo") {
		public void ejecutar() {
			vista.borrarPrestamo();
		}
	},
	LISTAR_PRESTAMOS("Listar Prestamos") {
		public void ejecutar() {
			vista.listarPrestamos();
		}
	},
	LISTAR_PRESTAMOS_ALUMNO("Listar prestamos de un alumno") {
		public void ejecutar() {
			vista.listarPrestamosAlumno();
		}
	},
	LISTAR_PRESTAMOS_LIBRO("Listar prestamos de un libro") {
		public void ejecutar() {
			vista.listarPrestamosLibro();
		}
	},
	LISTAR_PRESTAMOS_FECHA("Listar prestamos en una fecha determinada") {
		public void ejecutar() {
			vista.listarPrestamosFecha();
		}
	},
	SALIR("Salir") {
		public void ejecutar() {
			vista.terminar();
		}
	};

	private String mensaje;
	private static Vista vista;

	private Opcion(String mensaje) {
		this.mensaje = mensaje;
	}

	public abstract void ejecutar();

	protected static void setVista(Vista vista) {
		Opcion.vista = vista;
	}

	public static Opcion getOpcionSegunOridnal(int ordinal) {
		if (esOrdinalValido(ordinal))
			return values()[ordinal];
		else
			throw new IllegalArgumentException("Ordinal de la opción no válido");
	}

	public static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length - 1);
	}

	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), mensaje);
	}

}
