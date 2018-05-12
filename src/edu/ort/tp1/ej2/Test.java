package edu.ort.tp1.ej2;

import java.io.IOException;

import edu.ort.tp1.ej2.BufferedTextReader;
import edu.ort.tp1.ej2.BufferedTextWriter;
import edu.ort.tp1.ej2.TextReader;
import edu.ort.tp1.ej2.TextWriter;

public class Test {

	public static void main(String[] args) throws IOException {
		TextReader tr = new BufferedTextReader("src/edu/ort/tp1/ej2/Test.java");
		TextWriter tw = new BufferedTextWriter("archivo.text");
		while (tr.isReady()) {
			String l = tr.readLine();
			System.out.println(l);
			tw.writeLine(l);
		}
	}
}
