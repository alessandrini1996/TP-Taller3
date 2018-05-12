package edu.ort.tp1.ej3;

public class Articulo {

	private static final String ERR_PRECIO_NEGATIVO = "El precio no puede ser negativo";
	private static final String ERR_STOCK_INSUFICIENTE = "Stock insuficiente";
	private static final String ERR_CANTIDAD_INVALIDA = "La cantidad debe ser mayor a 0";
	private static final String ERR_DESCRIPCION_VACIA = "La descripcion no puede estar vacia";
	private static final String ERR_VALORES_INVALIDOS = "Valores invalidos";
	private static final String ERR_EN_DATO_RECIBO = "Error en un dato recibido:";
	private static final String ERR_CANT_VALORES = "La cantidad de valores recibidos no coniciden con los esperados";
	private static final int CANT_ATRIBUTOS = 4;
	private String codigo;
	private String descripcion;
	private int stock;
	private float precio;
	
	public Articulo(String codigo, String descripcion, int stock, float precio){
		setValues(codigo, descripcion, stock, precio);
	}
	public Articulo(String linea){
		String valor [] = linea.split("");
		if(valor.length != CANT_ATRIBUTOS){
			throw new IllegalArgumentException(ERR_CANT_VALORES);
		}
	}
	private void setValues(String codigo,String descripcion,int stock, float precio){
		if(!codigo.equals("") || !descripcion.equals("") || stock < 0 || precio < 0){
			throw new IllegalArgumentException(ERR_VALORES_INVALIDOS);
		}
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.stock = stock;
		this.precio = precio;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		if(!descripcion.equals("")){
			this.descripcion = descripcion;
		}else{
			throw new IllegalArgumentException(ERR_DESCRIPCION_VACIA);
		}
	}
	public int getStock() {
		return stock;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		if(precio >=0){
			this.precio=precio;
		}else{
			throw new IllegalArgumentException(ERR_PRECIO_NEGATIVO);
		}
	}

	public void incStock(int cantidad){
		if(cantidad>0){
			this.stock+=cantidad;
		}else{
			throw new IllegalArgumentException(ERR_CANTIDAD_INVALIDA);
		}
	}
	public void desStock(int cantidad){
		if(cantidad <=0){
			this.stock-=cantidad;
		}else{
			throw new IllegalArgumentException(ERR_STOCK_INSUFICIENTE);
		}
	}
	public String toCSV(){
		return this.codigo +","+ this.descripcion +","+ this.stock +","+ this.precio;
	}
	@Override
	public String toString() {
		return "Articulo [codigo=" + codigo + ", descripcion=" + descripcion + ", stock=" + stock + ", precio=" + precio
				+ "]";
	}
	
}