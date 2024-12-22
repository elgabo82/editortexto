package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * Editor de texto múltiple
 * 22.12.2024 12.30
 */


public class Interfaz implements ActionListener {
	public JFrame editor;
	public JMenuBar mnuBarra;
	public JMenu mnuArchivo, mnuEdicion, mnuOpciones, mnuAyuda;
	// Menú Archivo
	public JMenuItem mnuNuevo, mnuAbrir, mnuGuardar, mnuGuardarC, mnuImprimir, mnuSalir;	
	// Fin menú archivo
	// Menú Edición
	public JMenuItem mnuCortar, mnuCopiar, mnuPegar;	
	// Fin menú edición
	
	// Menú Opciones
	public JMenu mnuLenguaje;
	public JMenuItem mnuLenguajes[]; // Arreglo de distintas opciones
	// Fin menú opciones
	
	// Menú Ayuda
	public JMenuItem mnuContenido, mnuAcerca;	
	// Fin menú ayuda
	
	public static final String titulo = "Editor de Texto Múltiple";
	public static final String version = "0.1";
	
	// Textos para los menús
	// Menú Archivo
	public static final String MNU_ARCHIVO = "Archivo";
	public static final String MNU_ARCHIVO_NUEVO = "Nuevo";
	public static final String MNU_ARCHIVO_ABRIR = "Abrir";
	public static final String MNU_ARCHIVO_GUARDAR = "Guardar";
	public static final String MNU_ARCHIVO_GUARDARC = "Guardar como";
	public static final String MNU_ARCHIVO_IMPRIMIR = "Imprimir";
	public static final String MNU_ARCHIVO_SALIR = "Salir";	

	// Fin Menú Archivo
	
	// Menú Edición	
	public static final String MNU_EDICION = "Edición";
	public static final String MNU_EDICION_CORTAR = "Cortar";
	public static final String MNU_EDICION_COPIAR = "Copiar";
	public static final String MNU_EDICION_PEGAR = "Pegar";
	public static final String MNU_EDICION_DESHACER = "Deshacer";
	public static final String MNU_EDICION_REHACER = "Rehacer";
	// Fin Menú Edición
	
	
	// Menú Opciones	
	public static final String MNU_OPCIONES = "Opciones";
	public static final String MNU_OPCIONES_LENGUAJE = "Lenguaje";
	public static final String MNU_OPCIONES_LENGUAJES[] = {"Java", "C/C++", "Python", "JavaScript"};
	// Fin Menú Edición
	
	// Menú Opciones	
	public static final String MNU_AYUDA = "Ayuda";
	public static final String MNU_AYUDA_CONTENIDO = "Contenido";
	public static final String MNU_AYUDA_ACERCA = "Acerca de...";
	public static final String ACERCA =
			"Desarrollado por: Gabriel Eduardo Morejón López\ngabrielmorejon@gmail.com\nGitHub: elgabo82\nhttps://www.grupofmo.com\n2024";
	// Fin Menú Edición	
	
	public static int ALTO = 600;
	public static int ANCHO = 800;
	
	public String opcionesSalir[] = {"Sí", "No", "Cancelar"};
	
	public Interfaz() {		
				
		this.crearVentana();
		this.crearBarraMenu();
		this.crearMenuArchivo();
		this.crearMenuEdicion();
		this.crearMenuOpciones();
		this.crearMenuAyuda();
		editor.setVisible(true);
	}
	
	
	public void crearVentana() { // Crea la ventana principal
		editor = new JFrame();
		editor.setSize(ANCHO, ALTO);
		editor.setTitle(titulo);
		editor.setLayout(null);
		editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editor.setResizable(true);
		editor.setLocationRelativeTo(null);		
	}
	
	public void crearBarraMenu() { // Crea la barra de menú
		mnuBarra = new JMenuBar();
		mnuArchivo = new JMenu();
		mnuEdicion = new JMenu();
		mnuOpciones = new JMenu();
		mnuAyuda = new JMenu();
		
		mnuBarra.add(mnuArchivo);
		mnuBarra.add(mnuEdicion);
		mnuBarra.add(mnuOpciones);
		mnuBarra.add(mnuAyuda);		
		editor.setJMenuBar(mnuBarra);
	}
	
	public void crearMenuArchivo() { // Menú Archivo		
		mnuNuevo = new JMenuItem();	
		mnuAbrir = new JMenuItem();
		mnuGuardar = new JMenuItem();
		mnuGuardarC = new JMenuItem();
		mnuImprimir = new JMenuItem();
		mnuSalir = new JMenuItem();
		
		mnuArchivo.setText(MNU_ARCHIVO);
		mnuNuevo.setText(MNU_ARCHIVO_NUEVO);		
		mnuAbrir.setText(MNU_ARCHIVO_ABRIR);
		mnuGuardar.setText(MNU_ARCHIVO_GUARDAR);
		mnuGuardarC.setText(MNU_ARCHIVO_GUARDARC);
		mnuImprimir.setText(MNU_ARCHIVO_IMPRIMIR);
		mnuSalir.setText(MNU_ARCHIVO_SALIR);
		
		mnuArchivo.add(mnuNuevo);
		mnuArchivo.add(mnuAbrir);
		mnuArchivo.add(mnuGuardar);
		mnuArchivo.add(mnuGuardarC);
		mnuArchivo.add(mnuImprimir);
		mnuArchivo.addSeparator();
		mnuArchivo.add(mnuSalir);		
	}
	
	public void crearMenuEdicion() { // Menú Edición		
		mnuCortar = new JMenuItem();	
		mnuCopiar = new JMenuItem();
		mnuPegar = new JMenuItem();		
		
		mnuEdicion.setText(MNU_EDICION);
		mnuCortar.setText(MNU_EDICION_CORTAR);	
		mnuCopiar.setText(MNU_EDICION_COPIAR);
		mnuPegar.setText(MNU_EDICION_PEGAR);		
		
		mnuEdicion.add(mnuCortar);
		mnuEdicion.add(mnuCopiar);
		mnuEdicion.add(mnuPegar);
				
	}
	
	public void crearMenuOpciones() { // Menú Opciones		
		mnuLenguaje = new JMenu();
		
		mnuOpciones.setText(MNU_OPCIONES);
		mnuLenguaje.setText(MNU_OPCIONES_LENGUAJE);
		mnuLenguajes = new JMenuItem[MNU_OPCIONES_LENGUAJES.length];
		
		for (int i=0; i<MNU_OPCIONES_LENGUAJES.length; i++) {			
			mnuLenguajes[i] = new JMenuItem();
			mnuLenguajes[i].setText(MNU_OPCIONES_LENGUAJES[i]);
			mnuLenguaje.add(mnuLenguajes[i]);
			// Agregar ActionListener
		}		
		
		mnuOpciones.add(mnuLenguaje);				
	}		
	
	public void crearMenuAyuda() { // Menú Ayuda		
		mnuContenido = new JMenuItem();
		mnuAcerca = new JMenuItem();			
		
		mnuAyuda.setText(MNU_AYUDA);
		mnuContenido.setText(MNU_AYUDA_CONTENIDO);		
		mnuAcerca.setText(MNU_AYUDA_ACERCA);		
		
		
		mnuAyuda.add(mnuContenido);
		mnuAyuda.add(mnuAcerca);	
	}


	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		System.out.println(comando);
		
	}	
	

}
