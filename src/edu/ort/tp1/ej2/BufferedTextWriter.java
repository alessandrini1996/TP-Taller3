package edu.ort.tp1.ej2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedTextWriter implements TextWriter{

	private BufferedWriter buf;

	public BufferedTextWriter(String ruta) throws IOException {
		File file = new File(ruta);
		buf = null;
			if(!file.exists() || file.isFile()){
				buf = new BufferedWriter(new FileWriter(file));
			}
	}
	@Override
	public boolean isReady() {
		if (buf != null) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void writeLine(String a) {
		if (isReady()) {
			try {
				buf.write(a);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	@Override
	public void close(){
		if(isReady()){
			try{
				buf.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
