package edu.ort.tp1.ej3;

public class ArticuloWriter extends CSVWriter<Articulo>{

	public ArticuloWriter(){
		super("codigo,descripcion,stock,precio");
	}
}
