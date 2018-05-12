package edu.ort.tp1.ej3;

import java.io.IOException;
import java.util.List;

import edu.ort.tp1.ej2.BufferedTextWriter;
import edu.ort.tp1.ej2.TextWriter;

public class CSVWriter<ClassType> {

	private static final String CSV_EXTENSION = ".csv";
	private String header;
	
	public CSVWriter(String header){
		this.header = header;
	}
	public void writeAll(String nombre, List<ClassType> i ){
		if(!nombre.endsWith(CSV_EXTENSION)){
			nombre+= CSV_EXTENSION;
		}
		TextWriter tw = null;
		try {
			tw = new BufferedTextWriter(nombre);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(i.size() > 0){
			writeHeader(tw);
			writeItems(tw, i);
		}
		tw.close();
	}
	private void writeHeader(TextWriter tw){
		tw.writeLine(header);
	}
	private void writeItems(TextWriter tw, List<ClassType> i){
		String linea;
		for (ClassType classType : i) {
			linea = ((CSVCompatible<?>) i).toCSV();
			tw.writeLine(linea);
			System.out.println(linea);
		}
	}
}
