package edu.ort.tp1.ej1.entidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Seccion {

	private String nombre;
	private HashMap<String, String> items;

	public Seccion(String nombre) {
		this.nombre = nombre;
		this.items = new HashMap<>();
	}

	// setNombre: futuro puedo hace que no permita nombres con ñ etc...
	private void setNombre(String nombre) {

		this.nombre = nombre;
	}

	// esSeccion: hace una validacion para ver si lo que le mando es una seccion
	// estatica
	// para poder usarlo sin instanciarlo
	public static boolean esSeccion(String linea) {
		boolean result = false;
		if (linea != null && !linea.isEmpty()) {
			result = (linea.charAt(0) == '[') && (linea.charAt(linea.length() - 1) == ']') && (linea.indexOf(" ") < 0);
		}
		return result;
	}

	public static boolean esItem(String linea) {
		boolean result = false;
		// TODO: el if de abajo tenia indexOf("=")< 0 y para mi tiene que ser >
		if (!esSeccion(linea) && linea.indexOf("=") > 0) {
			result = true;
		}
		return result;
	}

	public static boolean esComentario(String linea) {
		boolean result = false;
		if (linea != null && !linea.isEmpty()) {
			if(linea.charAt(0) == ';')
					result = true;
		}
		return result;
	}

	// setItem: toma el string item y lo divide en 2
	// el item debe ser: clave=valor, y divide clave + valor
	public void setItem(String item) {
		String[] partes = item.split("=");
		setItem(partes[0], partes[1]); // Esta guarda clave y valor
	}

	// setItem: guarda clave y valor pasados como argumento
	public void setItem(String clave, String valor) {
		items.put(clave, valor);
	}

	public void list() {
		System.out.println("[" + this.nombre + "]");
		for (Entry<String, String> item : items.entrySet()) {
			System.out.println(item);
		}
	}

	public void save(BufferedWriter a) {
		try {
			a.write("[" + this.nombre + "]\r\n");
			for (Entry<String, String> item : items.entrySet()) {
				a.write(item.getKey()+"="+ item.getValue()+"\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (a == null) {
					a.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getNombre() {
		return nombre;
	}

	/*
	 * cuando el archivo es muy grande uso el bufferedWrite HashMap<>() es un
	 * diccionario no puede tener palabras duplicadas. bufferReader siempre lee
	 * texto. Solo string sccaner puede leer tipo de datos primitivos. Datos no
	 * primitivos hay que instanciarlos.
	 *
	 */

	// trim() saca espacios del string
	// split("=") divide el string

}