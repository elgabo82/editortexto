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
	//public JMenu mnuArchivo, mnuEdicion, mnuOpciones, mnuAyuda;
	public JMenu mnuListadoOpciones[]; // Menú general
	// Menú Archivo
	//public JMenuItem mnuNuevo, mnuAbrir, mnuGuardar, mnuGuardarC, mnuImprimir, mnuSalir;
	// Opciones de menú
	public JMenuItem mnuArchivo[], mnuEdicion[], mnuOpciones[], mnuAyuda[], mnuLenguajes[];
	// Fin menú archivo
	// Menú Edición
	//public JMenuItem mnuCortar, mnuCopiar, mnuPegar;	
	// Fin menú edición
	
	// Menú Opciones
	//public JMenu mnuLenguaje;
	//public JMenuItem mnuLenguajes[]; // Arreglo de distintas opciones
	// Fin menú opciones
	
	// Menú Ayuda
	//public JMenuItem mnuContenido, mnuAcerca;	
	// Fin menú ayuda
	
	public static final String titulo = "Editor de Texto Múltiple";
	public static final String version = "0.1";
	
	// Opciones generales del menú
	public static final String opcionesMenu[] = {"Archivo", "Edición", "Opciones", "Ayuda"};
	public static final String menuArchivo[] = {"Nuevo","Abrir","Guardar","Guardar como","Imprimir","-","Salir"};
	public static final String menuEdicion[] = {"Cortar","Copiar","Pegar","Deshacer","Rehacer"};
	public static final String menuOpciones[] = {"Lenguajes","-", "Opciones"};
	public static final String menuAyuda[] = {"Contenido", "-", "Acerca de"};

	public static final String MNU_OPCIONES_LENGUAJES[] = {"Java", "C/C++", "Python", "JavaScript"};

	public static final String ACERCA =
			"Desarrollado por: Gabriel Eduardo Morejón López\ngabrielmorejon@gmail.com\ngabriel.morejon@utm.edu.ec\nGitHub: elgabo82\nhttps://www.grupofmo.com\n2024";
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
		//mnuListadoOpciones - Arreglo con opciones de la barra
		mnuListadoOpciones = new JMenu[opcionesMenu.length];		
		
		//opcionesMenu - Arreglo del listado		
		for (int i=0; i<opcionesMenu.length; i++) {
			mnuListadoOpciones[i] = new JMenu();
			mnuListadoOpciones[i].setText(opcionesMenu[i]);
			mnuBarra.add(mnuListadoOpciones[i]);
		}
		
		editor.setJMenuBar(mnuBarra);
	}
	
	public void crearMenuArchivo() { // Menú Archivo		
		mnuArchivo = new JMenuItem[menuArchivo.length];
		for (int i=0; i<menuArchivo.length; i++) {			
			mnuArchivo[i] = new JMenuItem();
			mnuArchivo[i].setText(menuArchivo[i]);
			
			if (menuArchivo[i].contains("-")) { // Agrega un separador		
				mnuListadoOpciones[0].addSeparator();						
			} else {				
				mnuListadoOpciones[0].add(mnuArchivo[i]);
			}			
		}
	}
	
	public void crearMenuEdicion() { // Menú Edición
		mnuEdicion = new JMenuItem[menuEdicion.length];
		for (int i=0; i<menuEdicion.length; i++) {
			mnuEdicion[i] = new JMenuItem();
			mnuEdicion[i].setText(menuEdicion[i]);
			if (menuEdicion[i].contains("-")) { // Agrega un separador
				mnuListadoOpciones[1].addSeparator();
			} else {				
				mnuListadoOpciones[1].add(mnuEdicion[i]);
			}
		}		
	}
	
	public void crearMenuOpciones() { // Menú Opciones		
		mnuOpciones = new JMenuItem[menuOpciones.length];
		mnuLenguajes = new JMenuItem[MNU_OPCIONES_LENGUAJES.length];
		
		for (int i=0; i<menuOpciones.length; i++) {			
			if (menuOpciones[i].contains("-")) {
				mnuListadoOpciones[2].addSeparator();
			} else if (i==0) { // Agrega los elementos del sub-menú Lenguajes
				mnuOpciones[i] = new JMenu();
				
				for (int j=0; j<MNU_OPCIONES_LENGUAJES.length; j++) {			
					mnuLenguajes[j] = new JMenuItem();
					mnuLenguajes[j].setText(MNU_OPCIONES_LENGUAJES[j]);
					mnuOpciones[i].add(mnuLenguajes[j]);
					mnuOpciones[i].setText(menuOpciones[i]);
					mnuListadoOpciones[2].add(mnuOpciones[i]);
					// Agregar ActionListener
				}			 
				
			} else {
				mnuOpciones[i] = new JMenuItem();
				mnuOpciones[i].setText(menuOpciones[i]);
				mnuListadoOpciones[2].add(mnuOpciones[i]);
			}
			
			
		}				
	}		
	
	public void crearMenuAyuda() { // Menú Ayuda		
		mnuAyuda = new JMenuItem[menuAyuda.length];
		for (int i=0; i<menuAyuda.length; i++) {
			mnuAyuda[i] = new JMenuItem();
			mnuAyuda[i].setText(menuAyuda[i]);
			
			if (menuAyuda[i].contains("-")) { // Agrega un separador		
				mnuListadoOpciones[3].addSeparator();						
			} else {				
				mnuListadoOpciones[3].add(mnuAyuda[i]);
			}			
			
		}	
	}


	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		System.out.println(comando);
		
	}	
	

}
