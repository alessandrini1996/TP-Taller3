package edu.ort.tp1.ej2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedTextReader implements TextReader {

	private BufferedReader buf;

	public BufferedTextReader(String ruta) {
		File file = new File(ruta);
		try {
			if (file.exists() && file.isFile()) {
				buf = new BufferedReader(new FileReader(file));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isReady() {
		try {
			if (buf != null && buf.ready()) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String readLine() {
		String line = null;
		if (isReady()) {
			try {
				line = buf.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return line;
	}

	@Override
	public void close() {
		if (isReady()) {
			try {
				buf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
