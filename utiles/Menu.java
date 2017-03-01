package utiles;

import utiles.Teclado;

/**
 * <p>Crea la clase Menu. Mételo en el paquete utiles.</p> 
 * <p>Implementa al menos los siguientes métodos y atributos:</p> 
 * <ul>
 * <li>a. titulo</li> 
 * <li>b. opciones</li>
 * <li>c. numOpciones</li> <-- Este no es necesario lo sacamos con el campo length().
 * <li>d. gestionar()</li> 
 * <li>e. mostrar()</li> 
 * <li>f. recogerOpcion()</li>
 * </ul>
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class Menu {
	String titulo;
	String[] opciones;

	public Menu(String titulo, String[] opciones) {
		setTitulo(titulo);
		setOpciones(opciones);
	}

	/**
	 * Recoge una opcion valida entre 0 y el numero de opciones
	 * 
	 * @return
	 */
	int recogerOpcion() {
		int eleccion;
		do {
			eleccion = Teclado.leerEntero("Introduce una opcion válida:");
		} while (eleccion < 0 || eleccion > opciones.length);
		return eleccion;
	}
	
	/**
	 * Muestra el menu
	 */
	void mostrar() {
		System.out.println(titulo);
		for (int i = 0; i < opciones.length; i++) {
			System.out.println((i+1)+ ". " + opciones[i]);
		}
	}

	/**
	 * Gestiona las opciones del menu
	 */
	public int gestionar() {
		mostrar();
		return recogerOpcion();
	}
	
	/**
	 * Obtiene el titulo
	 * 
	 * @return titulo Titulo del menú
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Modifica el valor del titulo
	 * 
	 * @param titulo
	 *            Titulo del menu
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene las opciones
	 * 
	 * @return Un array con las opcciones del menu
	 */
	public String[] getOpciones() {
		return opciones;
	}

	/**
	 * Modifica las opciones del menu
	 * 
	 * @param opciones
	 *            Opciones del menu
	 */
	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}

}
