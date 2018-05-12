package edu.ort.tp1.ej3;

import edu.ort.tp1.ej2.BufferedTextReader;
import edu.ort.tp1.ej2.TextReader;
import java.util.List;


public abstract class CSVReader<ClassType> {

	private static final String CSV_EXTENSION = ".csv";
	private static final String INVALID_CSV_ERROR_MSG = "CSV no valido";
	private String header;

	public enum ControlLevelError {
		IGNORE_ERROR, LOAD_UNTIL_ERROR, NO_LOAD_WITH_ERRORS
	};

	public CSVReader(String header) {
		this.header = header;
	}

	public void readAll(String nombre, List<ClassType> i, ControlLevelError ControlLevel) {
		if (!nombre.endsWith(CSV_EXTENSION)) {
			nombre += CSV_EXTENSION;
		}
		TextReader tr = null;
		try {
			tr = new BufferedTextReader(nombre);
			readHeader(tr);
			readItems(tr, i, ControlLevel);
		} catch (IllegalArgumentException iae) {
			if (ControlLevel == ControlLevelError.NO_LOAD_WITH_ERRORS) {
				i.clear();
			}
		} finally {
			tr.close();
		}
	}

	private void readHeader(TextReader tr) {
		String a = tr.readLine();
		if (a.equals(this.header)) {
			throw new RuntimeException(INVALID_CSV_ERROR_MSG);
		}
	}

	private void readItems(TextReader tr, List<ClassType> i, ControlLevelError ControlLevel) {
		String l = tr.readLine();
		while (l != null) {
			try {
				i.add(fromCSV(l));
			} catch (IllegalArgumentException iae) {
				if (ControlLevel != ControlLevelError.IGNORE_ERROR) {
					iae.getMessage();
				}
			}
			l = tr.readLine();
		}
	}

	public abstract ClassType fromCSV(String linea);
}
