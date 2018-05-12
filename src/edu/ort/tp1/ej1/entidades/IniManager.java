package edu.ort.tp1.ej1.entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IniManager {

	private String ruta;
	private ArrayList<Seccion> secciones;

	public IniManager() {
		this(null);
	}

	public IniManager(String ruta) {
		this.ruta = ruta;
		this.secciones = new ArrayList<Seccion>();
	}

	public String getRuta() {
		return ruta;
	}

	public boolean load(String ruta) {
		this.ruta = ruta;
		return load();
	}

	// completar con corte de control. en cada seccion nueva guardar el par
	// key=value
	public boolean load() {
		boolean isFile = false;
		BufferedReader buf;
		String linea;
		boolean siSeccion = false;
		Seccion aux = null;
		File a = new File(this.getRuta());
		if (a.exists() && a.isFile()) {
			try {
				buf = new BufferedReader(new FileReader(ruta));
				while (buf.ready()) {
					linea = buf.readLine();
					if (Seccion.esSeccion(linea)) {
						if (siSeccion) {
							secciones.add(aux);
						}
						aux = new Seccion(linea.substring(0, secciones.size()-1));
						siSeccion = true;
					} else if (Seccion.esComentario(linea)) {
						
					} else if (Seccion.esItem(linea) && aux != null) {
						aux.setItem(linea);
					}

				}
				buf.close();

			} catch (FileNotFoundException e) {
				isFile = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isFile;
	}

	private void leer(Scanner archivo) { // leo la primera linea
		String linea = archivo.nextLine(); // me da la primera linea del archivo
	}

	/*
	 * pregunto si la siguiente linea es distinto de nulo si es distinto de nulo
	 * sigo con la siguiente linea sino devuelve nulo.
	 */
	private String getLinea(Scanner a) {
		return a.nextLine() != null ? a.nextLine() : null;
	}

	// llamo a buscarSeccion y le paso el nombre del parametro
	public Seccion getSeccion(String nombre) {
		return buscarSeccion(nombre);
	}

	/*
	 * buscarSeccion: recorre con un foreach el array de secciones y pregunta si
	 * el nombre de la seccion contiene el nombre pasado por parametro devuelve:
	 * objeto seccion si el nombre existe, null si no existe
	 */
	private Seccion buscarSeccion(String nombre) {
		for (Seccion seccion : secciones) {
			if (seccion.getNombre().equals(nombre)) {
				return seccion;
			}
		}
		return null;
	}

	private String getNombreSeccion(String nombre) {
		Seccion aux = buscarSeccion(nombre);
		if (aux.getNombre().equals(buscarSeccion(nombre))) {
			return aux.getNombre();
		}
		return null;
	}

	public void list() {
		// listo las secciones
		String s1 = null;
		String s2 = null;
		for (Seccion seccion : secciones) {
			s1 = seccion.getNombre();
			if (s1 != s2) {
				seccion.list();
				s2 = s1;
			}
		}
	}

	public void setItem(String nombreSeccion, String key, String value) {
		Seccion a = null;
		if (getSeccion(nombreSeccion) == null) {
			a = new Seccion(nombreSeccion);
		} else {
			a = getSeccion(nombreSeccion);
		}
		a.setItem(key, value);
		secciones.add(a);
	}

	/*
	 * 
	 */
	public void save() {
		String s1 = null;
		String s2 = null;

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(ruta);
			bw = new BufferedWriter(fw);
			for (Seccion seccion : secciones) {
				s1 = seccion.getNombre();
				if (s1 != s2) {
					// bw = new BufferedWriter(fw);
					seccion.save(bw);
					bw.newLine();
					s2 = s1;
				}
			}
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
