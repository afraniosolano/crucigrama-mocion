package com.mocion.principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class Principal extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private static int TAMANO = 15;
	
	public static final int DIRECCION_VERTICAL = 1;
	public static final int DIRECCION_HORIZONTAL = 2;	
	public static final int DIRECCION_DIAGONAL_DER_IZQ = 3;
	public static final int DIRECCION_DIAGONAL_IZQ_DER = 4;
	
	public static final int SENTIDO_NORMAL = 1;
	public static final int SENTIDO_INVERSO = 2;

	private JMenuBar barraMenu;
	private JToolBar toolbar;
	private JPanel tablero = null;
	
	public ArrayList<String> palabras = new ArrayList<>();
	
	public String[][] stringTablero = new String[TAMANO][TAMANO];
	
	/**
	 * Este método sirve para construir la nueva ventana
	 * @param tit es el titulo que va a mostrar la ventana
	*/

	public Principal(String tit){
		
		super(tit);
		
		toolbar = new JToolBar();
		
		configurarToolBar();
		
		setJMenuBar(barraMenu);
		
		add(toolbar, BorderLayout.NORTH);
		
		getContentPane().add( getTablero() );
		
		setSize(700, 700);
        setVisible(true);
        
//        llenarPalabras();
	}
	
	
	/**
	 * Este método sirve para cargar algunas palabras de ejemplo
	 * @author Afranio Solano
	 * @return void
	*/
	public void llenarPalabras(){
		
		agregarPalabra("Ciclomotor");
		agregarPalabra("Alrededor");
		agregarPalabra("Bicicleta");
		agregarPalabra("Velodromo");
		agregarPalabra("Quemadura");
		agregarPalabra("Infierno");
		agregarPalabra("Sonreir");
		agregarPalabra("Asiento");
		agregarPalabra("Plantas");
		agregarPalabra("Hambre");
		agregarPalabra("Nacion");
		agregarPalabra("Puntas");
		
	}
	
	/**
	 * Este método sirve para ordenar las palabras de mayor a menor tamaño
	 * @author Afranio Solano
	 * @return void
	*/
	public void ordenarPalabras(){
		Collections.sort(palabras, new Comparator<String>() {
		    @Override
		    public int compare(String s1, String s2) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		    	if( s1.length() > s2.length()){
		    		return -1;
		    	}else if( s1.length() < s2.length()){
		    		return 1;
		    	}
		    	return 0;
		    }
		});
	}

	/**
	 * Este método sirve para agregar nuevas palabras al crucigrama
	 * @author Afranio Solano
	 * @param palabra es la nueva cadena que se debe agregar a al crucigrama
	 * @return void
	*/
	public void agregarPalabra(String palabra){
		
		palabras.add(palabra.toLowerCase());
		ordenarPalabras();
		
	}

	/**
	 * Este método sirve para inicializar el tablero del crucigrama
	 * @author Afranio Solano
	 * @return retorna el nuevo panel
	*/
	public JPanel getTablero(){
		
		if(tablero == null){
			tablero = new JPanel();
			tablero.setLayout(new GridLayout(TAMANO, TAMANO));
			/*for(int i = 0; i < TAMANO; i++){
				for(int j = 0; j < TAMANO; j++){
					JLabel l1 = new JLabel(i + " " + j);
					l1.setForeground(Color.blue);
					l1.setBackground(Color.black);
					l1.setOpaque(true);
					tablero.add(l1);					
				}			
			}*/
		}
		
		return tablero;
		
	}

	/**
	 * Este método se encarga de iniciar la ejecución del programar
	 * Éste es el método principal del proyecto
	 * @param args[] es un arreglo con los parámetros que el reciba por consola
	 * @return void
	*/
	public static void main(String[] args) {
		
		new Principal("Crucigrama");
	
	}

	/**
	 * Este método es para crear una barra de menu
	 * @return void
	*/	
	public void configurarToolBar(){
		
        JButton exitButton = new JButton("EXIT");
        
        JButton addButton = new JButton("ADD");
        
        JButton newButton = new JButton("NEW");
        
        JButton viewButton = new JButton("VIEW");
        
        toolbar.add(newButton);
        toolbar.add(addButton );
        toolbar.add(viewButton);
        
        toolbar.addSeparator();
        toolbar.add(exitButton);
        
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	clean();        
            	colocarPalabras(true);
            }
        });
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	clean();
            	colocarPalabras(false);
            }
        });
        
        addButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent event) {
        		String palabra = JOptionPane.showInputDialog("Ingrese la nueva palabra");
        		if(palabra != null){
        			agregarPalabra(palabra);
        		}
        	}
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

	}
	
	
	/**
	 * Este método sirve para colocar las palabras en la matriz
	 * @author Afranio Solano
	 * @return void
	*/
	public void colocarPalabras(boolean mostrar){
		
		tablero.setLayout(new GridLayout(TAMANO, TAMANO));
		
		for(String palabra1 : palabras){
			
			agregarPalabraATablero(palabra1);
			
			imprimirMatriz();			

		}
		
		for(int i = 0; i < TAMANO; i++){
			for(int j = 0; j < TAMANO; j++){
				if( !stringTablero[i][j].equalsIgnoreCase("")){
					if(mostrar){
						JTextField t1 = new JTextField();
						t1.setHorizontalAlignment(JTextField.CENTER);
						tablero.add(t1);
					}else{
						JLabel l1 = new JLabel("" + stringTablero[i][j]);
						l1.setOpaque(true);
						l1.setHorizontalAlignment(JTextField.CENTER);
						tablero.add(l1);
					}
				}else{
					JLabel l1 = new JLabel("");
					l1.setBackground(Color.black);
					l1.setOpaque(true);
					l1.setHorizontalAlignment(JTextField.CENTER);
					tablero.add(l1);
				}
									
			}			
		}
		
		tablero.revalidate();
		
	}
	
	/**
	 * Este método sirve para agregar una palabra en el tablero
	 * @author Afranio Solano
	 * @param palabra1 es la nueva cadena que se debe agregar a al crucigrama
	 * @return void
	*/
	private void agregarPalabraATablero(String palabra1) {
		boolean vbreak = false;
		vbreak = false;
		for(int i = 0; i < TAMANO; i++){
			
			if(vbreak){
				break;
			}
			
			for(int j = 0; j < TAMANO; j++){
				
				String inversa = invertirCadena(palabra1);
				
				if ( validarInsercionDePalabra(palabra1, i, j, DIRECCION_DIAGONAL_DER_IZQ) ){
					insertarPalabra(palabra1, i, j, DIRECCION_DIAGONAL_DER_IZQ);
					vbreak = true;
					break;
				}
				
				if ( validarInsercionDePalabra(palabra1, i, j, DIRECCION_VERTICAL) ){
					insertarPalabra(palabra1, i, j, DIRECCION_VERTICAL);
					vbreak = true;
					break;
				}
				
				if ( validarInsercionDePalabra(palabra1, i, j, DIRECCION_DIAGONAL_IZQ_DER) ){
					insertarPalabra(palabra1, i, j, DIRECCION_DIAGONAL_IZQ_DER);
					vbreak = true;
					break;
				}
				
				if ( validarInsercionDePalabra(palabra1, i, j, DIRECCION_HORIZONTAL) ){
					insertarPalabra(palabra1, i, j, DIRECCION_HORIZONTAL);
					vbreak = true;
					break;
				}							
				
				
				if ( validarInsercionDePalabra(inversa, i, j, DIRECCION_DIAGONAL_IZQ_DER) ){
					insertarPalabra(inversa, i, j, DIRECCION_DIAGONAL_IZQ_DER);
					vbreak = true;
					break;
				}
				if ( validarInsercionDePalabra(inversa, i, j, DIRECCION_VERTICAL) ){
					insertarPalabra(inversa, i, j, DIRECCION_VERTICAL);
					vbreak = true;
					break;
				}
				if ( validarInsercionDePalabra(inversa, i, j, DIRECCION_DIAGONAL_DER_IZQ) ){
					insertarPalabra(inversa, i, j, DIRECCION_DIAGONAL_DER_IZQ);
					vbreak = true;
					break;
				}
				if ( validarInsercionDePalabra(inversa, i, j, DIRECCION_HORIZONTAL) ){
					insertarPalabra(inversa, i, j, DIRECCION_HORIZONTAL);
					vbreak = true;
					break;
				}
				
				
			}
 
		}
		
	}
	
	/**
	 * Este método sirve para validar si una palabra se puede colocar en el tablero en una orientacion y partiendo desde una posicion
	 * @author Afranio Solano
	 * @param palabra2 es la palabra que debe validar
	 * @param fila es la fila desde la cual se puede validar
	 * @param columna en la columna desde la cual se puede validar
	 * @param direccion es la orientacion que se le va a dar
	 * @return true si se puede insertar y false si no se puede insertar
	*/	
	public boolean validarInsercionDePalabra(String palabra2, int fila, int columna, int direccion){
		boolean respuesta = false;
		int k = 0;
		int i = 0;
		int j = 0;
		switch (direccion) {
		case DIRECCION_VERTICAL:
			if(palabra2.length() + fila > TAMANO){
				return false;
			}			
			
			k = 0;
			for(i = fila; i < fila + palabra2.length(); i++){
				if((!stringTablero[i][columna].equalsIgnoreCase(""))&&
				   (!stringTablero[i][columna].equalsIgnoreCase( "" + palabra2.charAt(k) ))
						){
					return false;
				}
				
				k++;
			}
			
			return true;

		case DIRECCION_HORIZONTAL:
			if(palabra2.length() + columna > TAMANO){
				return false;
			}
			
			k = 0;
			for(i = columna; i < columna + palabra2.length(); i++){
				if((!stringTablero[fila][i].equalsIgnoreCase(""))&&
				   (!stringTablero[fila][i].equalsIgnoreCase( "" + palabra2.charAt(k) ))
						){
					return false;
				}
				
				k++;
			}
			
			return true;
			
		case DIRECCION_DIAGONAL_DER_IZQ:
			
			if((palabra2.length() + columna >= TAMANO)||(palabra2.length() + fila >= TAMANO)){
				return false;
			}
			
			k = 0;
			i = fila;
			j = columna; 
			while ( i < TAMANO && j < TAMANO && k < palabra2.length() ){
			    
		    	if((!stringTablero[i][j].equalsIgnoreCase(""))&&
				   (!stringTablero[i][j].equalsIgnoreCase( "" + palabra2.charAt(k) ))
						){
					return false;
				}
				
				k++;
				i++;
			    j++;
				
			}			
			return true;
		case DIRECCION_DIAGONAL_IZQ_DER:
			for(i = 0; i < TAMANO; i++){
				for (i = 0; i < TAMANO; i++) {
				    for (j = 0; j < TAMANO - i ; j++) {
				    }
				}
			}
			break;
		}	
		
		return respuesta;
	}
	
	/**
	 * Este método sirve para insertar una palabra desde una orientacion y partiendo desde una posicion
	 * @author Afranio Solano
	 * @param palabra2 es la palabra que debe validar
	 * @param fila es la fila desde la cual se puede validar
	 * @param columna en la columna desde la cual se puede validar
	 * @param direccion es la orientacion que se le va a dar
	 * @return void
	*/	
	public void insertarPalabra(String palabra2, int fila, int columna, int direccion){
		System.out.println("Insertada en la columna " + columna + " y la fila " + fila);
		int k = 0;
		int i = 0;
		int j = 0;
		switch (direccion) {
			case DIRECCION_VERTICAL:
				k = 0;
				for(i = fila; i < fila + palabra2.length(); i++){
					stringTablero[i][columna] = "" + palabra2.charAt(k);
					k++;
				}
			case DIRECCION_DIAGONAL_IZQ_DER:
				break;
			case DIRECCION_DIAGONAL_DER_IZQ:
				k = 0;
				i = fila;
				j = columna; 
				while ( i < TAMANO && j < TAMANO && k < palabra2.length()){
				    
					stringTablero[i][j] = "" + palabra2.charAt(k);
					
					k++;
					i++;
				    j++;
					
				}
				break;
			case DIRECCION_HORIZONTAL:
				k = 0;
				for(i = columna; i < columna + palabra2.length(); i++){
					stringTablero[fila][i] = "" + palabra2.charAt(k);
					k++;
				}
		}
	}
	
	/**
	 * Este método sirve invertir una palabra
	 * @author Afranio Solano
	 * @param sCadena es la palabra que se va a convertir
	 * @return retorna la cadena invertida
	*/
	public static String invertirCadena(String sCadena){
		String sCadenaInvertida = "";
		for (int x = sCadena.length()-1; x >= 0; x--){
			sCadenaInvertida = sCadenaInvertida + sCadena.charAt(x);
		}
		return sCadenaInvertida;
	}

	/**
	 * Este método sirve para reiniciar el juego
	 * @author Afranio Solano
	 * @return void
	*/
	public void clean(){
		tablero.removeAll();
    	tablero.repaint();
    	for(int i = 0; i < TAMANO; i++){
    		stringTablero[i] = new String[TAMANO];
			for(int j = 0; j < TAMANO; j++){
				stringTablero[i][j] = "";									
			}			
		}
	}
	
	/**
	 * Este método sirve mostrar la informacion de la matriz en consola
	 * @author Afranio Solano
	 * @return void
	*/
	public void imprimirMatriz(){
		for(int i = 0; i < TAMANO; i++){
    		for(int j = 0; j < TAMANO; j++){
    			System.out.print(stringTablero[i][j] + " ");
			}			
    		System.out.print("\n");
		}
	}
	
	/**
	 * Este método sirve para generar un numero aleatorio entre un rango de numeros
	 * @author Afranio Solano
	 * @param inferior el valor menor del rango
	 * @param superior el valor mayor del rango
	 * @return retorna el numero generado
	*/
	public static int aleatorioRango(int inferior, int superior){
		int numPosibilidades = (superior + 1) - inferior;
		double aleat = Math.random() * numPosibilidades;
		aleat = Math.floor(aleat);
		aleat = (inferior + aleat);
		return (int)aleat;
	}

}
