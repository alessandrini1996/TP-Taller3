package edu.ort.tp1.ej3;

public class ArticuloReader extends CSVReader<Articulo> {

	public ArticuloReader(){
		super("codigo,descripcion,stock,precio");
	}
	@Override
	public Articulo fromCSV(String linea) {
		Articulo art = new Articulo(linea);
		return art;
	}
}
