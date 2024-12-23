package sintaxis;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/*
 * 22.12.2024
 * 16.42
 * Clase: Analizador
 * 
 * Analiza la sintaxis dentro del editor
 * Clase tomada del proyecto anterior
 */

public class Analizador implements DocumentListener {
	private final StyledDocument documento;
	private String lenguaje;
	private String tema;	
	
	private Map<String, Style> estilos;
	// Mapa de los patrones de palabras reservadas de los lenguajes
	private static final Map<String, Map<String, Pattern>> PATRONES_LENGUAJE = new HashMap<>();
	// Mapa de los colores de los temas.
	private static final Map<String, Map<String, Color>> Temas = new HashMap<>();
	
	static { // Patrones de lenguajes
		// Java
		Map<String, Pattern> java = new HashMap<>();
		java.put("Palabras", Pattern.compile("\\b(abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|native|new|package|private|protected|public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while)\\b"));
		java.put("Cadenas", Pattern.compile("\".*?\"|'.*?'"));
        java.put("Comentarios", Pattern.compile("//.*|/\\*(.|\\R)*?\\*/"));
        java.put("Números", Pattern.compile("\\b\\d+\\.?\\d*\\b"));
        
        PATRONES_LENGUAJE.put("Java", java);
        
        // Temas
        Map<String, Color> temaOscuro = new HashMap<>();
        temaOscuro.put("Palabras", Color.BLUE);
        temaOscuro.put("Cadenas", new Color(34, 139, 34)); // Verde claro
        temaOscuro.put("Comentarios", Color.GREEN);
        temaOscuro.put("Números", new Color(139, 0, 139)); // Magenta
        temaOscuro.put("Defecto", Color.BLACK);
        Temas.put("Oscuro", temaOscuro);

        Map<String, Color> temaClaro = new HashMap<>();
        temaClaro.put("Palabras", Color.BLUE);
        temaClaro.put("Cadenas", new Color(0, 100, 0)); // Verde Oscuro
        temaClaro.put("Comentarios", Color.GRAY);
        temaClaro.put("Números", Color.MAGENTA);
        temaClaro.put("Defecto", Color.BLACK);
        Temas.put("Claro", temaClaro);
	}
	
	
	public Analizador(StyledDocument doc, String lenguaje, String tema) {
		this.documento = doc;
		setLenguaje(lenguaje);
		setTema(tema);
	}
	
	
	public String[] getLenguajesSoportados() {
		return PATRONES_LENGUAJE.keySet().toArray(new String[0]);
	}


	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
		actualizarResaltadoSintaxis();
	}


	public static String[] temasDisponibles() {
		return Temas.keySet().toArray(new String[0]);
	}


	public void setTema(String tema) {
		this.tema = tema;
		estilos = new HashMap<>();
		Map<String, Color> colores = Temas.get(tema);
		for (String elemento: colores.keySet()) {
			Style estilo = documento.addStyle(elemento, null);
			StyleConstants.setForeground(estilo, colores.get(elemento));
			estilos.put(elemento, estilo);
		}
		actualizarResaltadoSintaxis();
	}


	@Override
	public void insertUpdate(DocumentEvent e) {
		actualizarResaltadoSintaxis();
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		actualizarResaltadoSintaxis();
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		//actualizarResaltadoSintaxis();
		
	}
	
	 private void actualizarResaltadoSintaxis() {
	        SwingUtilities.invokeLater(() -> {
	            try {
	                String texto = documento.getText(0, documento.getLength());
	                documento.setCharacterAttributes(0, texto.length(), estilos.get("Defecto"), true);	                
	                
	                Map<String, Pattern> patrones = PATRONES_LENGUAJE.get(lenguaje);
	                if (patrones!= null) {
	                    for (String elemento : patrones.keySet()) {
	                        Matcher matcher = patrones.get(elemento).matcher(texto);
	                        while (matcher.find()) {
	                            int inicio = matcher.start();
	                            int fin = matcher.end();
	                            documento.setCharacterAttributes(inicio, fin - inicio, estilos.get(elemento), true);
	                        }
	                    }
	                }
	            } catch (BadLocationException ex) {
	                ex.printStackTrace();
	            }
	        });
	    }	
}
