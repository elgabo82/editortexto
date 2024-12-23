package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledDocument;

import sintaxis.Analizador;

/*
 * Editor de texto múltiple
 * 22.12.2024 12.30
 */


public class Interfaz implements ActionListener {
	public JFrame editor;
	public JMenuBar mnuBarra;
	public JMenu mnuListadoOpciones[]; // Menú general	

	public JTextPane areaTexto; // Para poder usar colores en el texto
	public JScrollPane panelTexto; 
	
	// Para poder aplicar estilos al documento
	public StyledDocument documento;
	public Analizador resaltador;
	
	// Opciones de menús
	public JMenuItem mnuArchivo[], mnuEdicion[], mnuOpciones[], mnuAyuda[], mnuLenguajes[];
	
	public static final String titulo = "Editor de Texto Múltiple";
	public static final String version = "0.1";
	
	// Opciones generales de los menús
	public static final String opcionesMenu[] = { "Archivo", "Edición", "Opciones", "Ayuda" };
	public static final String menuArchivo[] = { "Nuevo","Abrir","Guardar","Guardar como","Imprimir","-","Salir" };
	public static final String atajosArchivo[] = { "N", "A", "G", "M", "I", "L" };
	public static final String menuEdicion[] = { "Cortar","Copiar","Pegar","Deshacer","Rehacer" };
	public static final String atajosEdicion[] = { "X", "C", "V", "Z", "U" };
	public static final String menuOpciones[] = { "Lenguajes", "Interfaz", "-", "Opciones" };
	public static final String MNU_OPCIONES_LENGUAJES[] = { "Java", "C/C++", "Python", "JavaScript" };
	public static final String MNU_OPCIONES_INTERFAZ[] = { "Oscuro", "Claro" };
	
	public static final String menuAyuda[] = { "Contenido", "-", "Acerca de" };	


	public static final String ACERCA =
			"Desarrollado por: Gabriel Eduardo Morejón López\ngabrielmorejon@gmail.com\ngabriel.morejon@utm.edu.ec\nGitHub: elgabo82\nhttps://www.grupofmo.com\n2024";
	// Fin Menú Edición	
	
	public static int ALTO = 600;
	public static int ANCHO = 800;
	public String lenguaje;
	public String tema;
		
	public String opcionesSalir[] = { "Sí", "No", "Cancelar" };
	
	public Interfaz() {		
		this.lenguaje = "Java";
		this.tema = "Oscuro";		
		this.crearVentana();
		this.crearBarraMenu();
		this.crearMenuArchivo();
		this.crearMenuEdicion();
		this.crearMenuOpciones();
		this.crearMenuAyuda();
		this.crearAreaTexto();
		
		editor.setVisible(true);
	}
	
	
	public void crearVentana() { // Crea la ventana principal
		editor = new JFrame();
		editor.setSize(ANCHO, ALTO);
		editor.setTitle(titulo);
		//editor.setLayout(null);
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
		int j=0;
		char C;
		for (int i=0; i<menuArchivo.length; i++) {			
			mnuArchivo[i] = new JMenuItem();
			mnuArchivo[i].setText(menuArchivo[i]);
			mnuArchivo[i].addActionListener(this);
			mnuArchivo[i].setActionCommand(menuArchivo[i]);
			C = atajosArchivo[j].charAt(0); // Extrae el caracter de la pos. 0
			
			if (menuArchivo[i].contains("-")) { // Agrega un separador		
				mnuListadoOpciones[0].addSeparator();						
			} else {
				mnuArchivo[i].setAccelerator(KeyStroke.getKeyStroke(C, KeyEvent.CTRL_DOWN_MASK));
				j++;
				mnuListadoOpciones[0].add(mnuArchivo[i]);
			}			
		}
					
	}
	
	public void crearMenuEdicion() { // Menú Edición
		mnuEdicion = new JMenuItem[menuEdicion.length];
		int j=0;
		for (int i=0; i<menuEdicion.length; i++) {
			mnuEdicion[i] = new JMenuItem();
			mnuEdicion[i].setText(menuEdicion[i]);
			mnuEdicion[i].addActionListener(this);
			mnuEdicion[i].setActionCommand(menuEdicion[i]);
			
			if (menuEdicion[i].contains("-")) { // Agrega un separador
				mnuListadoOpciones[1].addSeparator();
			} else {
				mnuEdicion[i].setAccelerator(KeyStroke.getKeyStroke(atajosEdicion[j].charAt(0), KeyEvent.CTRL_DOWN_MASK));
				j++;
				mnuListadoOpciones[1].add(mnuEdicion[i]);
			}
		}		
	}
	
	public void crearMenuOpciones() { // Menú Opciones		
		mnuOpciones = new JMenuItem[menuOpciones.length];
		mnuLenguajes = new JMenuItem[MNU_OPCIONES_LENGUAJES.length];
		
		for (int i=0; i<menuOpciones.length; i++) {			
			if (menuOpciones[i].contains("-")) { // Si hay un separador
				mnuListadoOpciones[2].addSeparator();
			} else if (i==0) { // Agrega los elementos del sub-menú Lenguajes
				mnuOpciones[i] = new JMenu();
				
				for (int j=0; j<MNU_OPCIONES_LENGUAJES.length; j++) {			
					mnuLenguajes[j] = new JMenuItem();
					mnuLenguajes[j].setText(MNU_OPCIONES_LENGUAJES[j]);
					mnuOpciones[i].add(mnuLenguajes[j]);
					mnuOpciones[i].setText(menuOpciones[i]);
					mnuLenguajes[j].addActionListener(this);
					mnuLenguajes[j].setActionCommand(MNU_OPCIONES_LENGUAJES[j]);
					mnuListadoOpciones[2].add(mnuOpciones[i]);					
				}
			} else if (i==1) { // Agrega los elementos del sub-menú Temas
				mnuOpciones[i] = new JMenu();
				
				for (int j=0; j<MNU_OPCIONES_INTERFAZ.length; j++) {			
					mnuLenguajes[j] = new JMenuItem();
					mnuLenguajes[j].setText(MNU_OPCIONES_INTERFAZ[j]);
					mnuOpciones[i].add(mnuLenguajes[j]);
					mnuOpciones[i].setText(menuOpciones[i]);
					mnuLenguajes[j].addActionListener(this);
					mnuLenguajes[j].setActionCommand(MNU_OPCIONES_INTERFAZ[j]);
					mnuListadoOpciones[2].add(mnuOpciones[i]);					
				}
			} else {
				mnuOpciones[i] = new JMenuItem();
				mnuOpciones[i].setText(menuOpciones[i]);
				mnuOpciones[i].addActionListener(this);
				mnuOpciones[i].setActionCommand(menuOpciones[i]);
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
				mnuAyuda[i].addActionListener(this);
				mnuAyuda[i].setActionCommand(menuAyuda[i]);
				mnuListadoOpciones[3].add(mnuAyuda[i]);
			}
		}	
	}

	public void crearAreaTexto() { // Crea el área para introducir texto.
		areaTexto = new JTextPane(); // Inicializa areaTexto		
		
		panelTexto = new JScrollPane(areaTexto,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//panelTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));
		documento = areaTexto.getStyledDocument();
		//if (this.lenguaje.contains("Java") || this.lenguaje.contains("C/C++")) {
			panelTexto.setFont(new Font("Monospaced", Font.BOLD, 24));
		
			resaltador = new Analizador(documento, this.lenguaje, this.tema);
			areaTexto.getDocument().addDocumentListener(resaltador);
		//}
		
		editor.add(panelTexto, BorderLayout.CENTER);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (e.getActionCommand().contains(menuArchivo[0])) { // Nuevo
					
		}
		
		if (e.getActionCommand().contains(menuArchivo[1])) { // Abrir
			
		}
		
		if (e.getActionCommand().contains(menuArchivo[2])) { // Guardar
			
		}
		if (e.getActionCommand().contains(menuArchivo[3])) { // Guardar como
			
		}
		
		if (e.getActionCommand().contains(menuArchivo[4])) { // Imprimir
			
		}
		
		if (e.getActionCommand().contains(menuArchivo[6])) { // Salir
			
		}
		
		if (e.getActionCommand().contains(MNU_OPCIONES_INTERFAZ[0])) {
			this.tema = "Oscuro";
			resaltador.setTema(this.tema);
		}
		
		if (e.getActionCommand().contains(MNU_OPCIONES_INTERFAZ[1])) {
			this.tema = "Claro";
			resaltador.setTema(this.tema);
		}
		
		if (e.getActionCommand().contains(MNU_OPCIONES_LENGUAJES[0])) {
			this.lenguaje = "Java";
			resaltador.setLenguaje(this.lenguaje);
		}
		
		if (e.getActionCommand().contains(MNU_OPCIONES_LENGUAJES[1])) {
			this.lenguaje = "C/C++";
			resaltador.setLenguaje(this.lenguaje);
		}
		
	}

}