package edu.ort.tp1.ej1.entidades;

public class Test {

	public static void main(String[] args) {
		IniManager iniManager = new IniManager("filename.ini");
//		Seccion s = new Seccion("Seccion 1");
//		Seccion s2 = new Seccion("Seccion 2");
		iniManager.setItem("CLAVES", "usuario_1", "4321");
		iniManager.setItem("CLAVES", "usuario_2", "1234");
		iniManager.setItem("CLAVES", "administrador", "1111");
		iniManager.setItem("IMPUESTOS", "iva_1", "21");
		iniManager.setItem("IMPUESTOS", "iva_2", "10.5");
		iniManager.setItem("IMPUESTOS", "iva_3", "0.0");
		iniManager.setItem("IMPUESTOS", "iibb", "3");
		iniManager.list();
		iniManager.load();
		iniManager.save();
	}
}

