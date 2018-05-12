package edu.ort.tp1.ej3;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList<Articulo> i = new ArrayList<>();
		try{
			i.add(new Articulo("A211","bolsa de basura", 700, 90));
			i.add(new Articulo("A331", "fideos", 300, 50));
			i.add(new Articulo("A455", "jabon liquido", 322, 70));
			i.add(new Articulo("A566", "guate de latex", 200, 19));
			
			CSVWriter<Articulo> write = new ArticuloWriter();
			write.writeAll("salida", i);
			
			i.clear();
			System.out.println("La lista contiene: " + i.size() + "de articulos");
			
			CSVReader<Articulo> reader = new ArticuloReader();
			reader.readAll("saldida", i, CSVReader.ControlLevelError.IGNORE_ERROR);
			for (Articulo articulo : i) {
				System.out.println(articulo);
			}
		}catch(RuntimeException e){
			System.out.println(e.getMessage());
		}
	}

}
