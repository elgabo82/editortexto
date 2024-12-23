package es;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JOptionPane;

import interfaz.Interfaz;

/*
 * 22.12.2024
 * 16.40
 * Clase: Archivos
 * Operaciones básicas E/S
 */
public class Archivos {
	Interfaz gui;
	String nombreArchivo, rutaArchivo;
	// 10
	
	public Archivos(Interfaz gui) {
		this.gui = gui;
	}
	
	// 9
	public void nuevoArchivo() {
		gui.areaTexto.setText("");
		gui.editor.setTitle(gui.titulo + " | " + gui.menuArchivo[0]);
		// 16
		nombreArchivo = null;
		rutaArchivo = null;
	}
	
	public void abrirArchivo() {
		FileDialog dialogo = new FileDialog(gui.editor, gui.menuArchivo[1], FileDialog.LOAD);
		dialogo.setVisible(true);
		
		
		if (dialogo.getFile() != null) {
			nombreArchivo = dialogo.getFile();
			rutaArchivo = dialogo.getDirectory();
			
			gui.editor.setTitle(gui.titulo + " | " + nombreArchivo);
		} else {
			JOptionPane.showMessageDialog(null, gui.SELECCIONAR_ARCHIVO, gui.titulo, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		// 10
		
		//System.out.println("Ruta: " + rutaArchivo + nombreArchivo);
		try {
			BufferedReader buffer = 
					new BufferedReader(
							new FileReader(rutaArchivo + nombreArchivo));
			gui.areaTexto.setText("");
			
			String linea = null;
			StringBuilder contenido = new StringBuilder();
						
			while ((linea = buffer.readLine())!=null) {
				contenido.append(linea).append("\n");
				// Para usar con JTextArea
				//gui.areaTexto.append(linea + "\n");			
			}
			gui.areaTexto.setText(contenido.toString());
			
			buffer.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,					
					gui.ERROR_ARCHIVO,
					gui.titulo,
					JOptionPane.ERROR_MESSAGE);
			
			//System.out.println(gui.ERROR_ARCHIVO);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	// 14
	public void guardarArchivo() {
		if (nombreArchivo == null) {
			this.guardarComo();
		} else {
			try {
				FileWriter archivo = new FileWriter(rutaArchivo + nombreArchivo);
				archivo.write(gui.areaTexto.getText());
				gui.editor.setTitle(gui.titulo + " | " + nombreArchivo);
				archivo.close();
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,					
						gui.ERROR_ARCHIVO,
						gui.titulo,
						JOptionPane.ERROR_MESSAGE);
				
				//System.out.println(gui.ERROR_GUARDAR);
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	// 13
	public void guardarComo() {
		FileDialog dialogo = new FileDialog(gui.editor, gui.menuArchivo[2], FileDialog.SAVE);
		dialogo.setVisible(true);
		
		if (dialogo.getFile()!=null) {
			nombreArchivo = dialogo.getFile();
			rutaArchivo = dialogo.getDirectory();
			gui.editor.setTitle(gui.titulo + " | " + nombreArchivo);
		} else {
			JOptionPane.showMessageDialog(null, gui.NOMBRE_ARCHIVO, gui.titulo, JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		try {
			FileWriter guardar = new FileWriter(rutaArchivo + nombreArchivo);
			guardar.write(gui.areaTexto.getText());
			
			guardar.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,					
					gui.ERROR_ARCHIVO,
					gui.titulo,
					JOptionPane.ERROR_MESSAGE);
			
			//System.out.println(gui.ERROR_GUARDAR);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
	}
	
	// 16
	public void salir() {		
		
		int respuesta = JOptionPane.showOptionDialog(null,
				gui.CONFIRMAR_SALIR,
				gui.titulo,
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				gui.opcionesSalir,
				gui.opcionesSalir[0]);
		
		// 0 Sí
		// 1 No
		// 2 Cancelar
		
		//System.out.println(respuesta);
		if (respuesta == 0) {
			System.exit(0);
		}
	}


}
