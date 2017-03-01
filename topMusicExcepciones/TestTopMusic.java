package topMusicExcepciones;

import utiles.Menu;
import utiles.Teclado;
/**
 * El usuario podrá:
 * 
 * a. Añadir una canción (en una posición) al TopMusic.
 * b. Sacar un elemento del TopMusic.
 * c. Subir un puesto en el TopMusic.
 * d. Bajar un puesto en el TopMusic.
 * e. Mostrar la lista TopMusic.
 * f. Mostrar la canción más escuchada.
 * 
 * Sobre la canción se almacenará el título, artista o grupo y año de grabación.
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class TestTopMusic {
	/**
	 * Campo que recogera nuestras canciones
	 */
	private static TopMusic topMusic = new TopMusic();

	public static void main(String[] args) throws AutorNoValidoException, CancionNoValidoException, PosicionNoValidaException {
		Menu menuGeneral = new Menu("*** TOP MUSIC ***", new String[] { "Añadir cancion", "Eliminar cancion",
				"Subir puesto", "Bajar puesto", "Mostrar TopMusic", "Mostrar el nº1", "Salir" });
		int opcion;

		do {
			opcion = menuGeneral.gestionar();
			gestionarOpciones(opcion);
		} while (opcion != 7);
	}

	/**
	 * Gestiona las opciones del menu
	 * 
	 * @param opcion
	 *            escogida
	 * @throws AutorNoValidoException 
	 * @throws CancionNoValidoException 
	 * @throws PosicionNoValidaException 
	 */
	private static void gestionarOpciones(int opcion) throws AutorNoValidoException, CancionNoValidoException, PosicionNoValidaException {
		switch (opcion) {
		case 1:
			// Añadir cancion
			try {
				annadirCancion();
			} catch (AutorNoValidoException e) {
				System.err.println(e.getMessage());
			}
			catch (CancionNoValidoException e) {
				System.err.println(e.getMessage());
			}
			catch (FechaNoValidaException e) {
				System.err.println(e.getMessage());
			}
			catch (PosicionNoValidaException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 2:
			// Eliminar cancion
			eliminarCancion();
			break;
		case 3:
			// Subir cancion
			try {
				subirCancion();
			} catch (PosicionNoValidaException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 4:
			// Bajar cancion
			try {
				bajarCancion();
			} catch (PosicionNoValidaException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 5:
			// Mostrar top
			mostrarTop();
			break;
		case 6:
			// Mostrar nº1
			mostrarCima();
			break;
		case 7:
			// Salir
			System.out.println("Adios!");
			System.exit(0);
			break;
		}
	}

	/**
	 * Muestra el nº 1 del top
	 */
	private static void mostrarCima() {
		if (showIfIsEmpty()) {
			System.out.println(topMusic.masEscuchada());
		}
	}

	/**
	 * Muestra el top completo
	 */
	private static void mostrarTop() {
		if (showIfIsEmpty())
			System.out.println(topMusic.mostrarTopMusic());
	}

	/**
	 * Baja una cancion del top
	 * @throws PosicionNoValidaException 
	 */
	private static void bajarCancion() throws PosicionNoValidaException {
		if (showIfIsEmpty()) {
			if (!topMusic.bajar(topMusic.posicionValida(Teclado.leerEntero("Posicion:"))))
				System.out.println("No se pudo bajar la cancion");
			else
				System.out.println("Cancion bajada!");
		}
	}

	/**
	 * Sube una cancion del top
	 * @throws PosicionNoValidaException 
	 */
	private static void subirCancion() throws PosicionNoValidaException {
		if (showIfIsEmpty()) {
			if (!topMusic.subir(topMusic.posicionValida(Teclado.leerEntero("Posicion:"))))
				System.out.println("No se pudo subir la cancion");
			else
				System.out.println("Cancion subida!");
		}
	}

	/**
	 * Elimina una cancion del top
	 */
	private static void eliminarCancion() {
		if (showIfIsEmpty()) {
			if (!topMusic.sacar(Teclado.leerEntero("Posicion:")))
				System.out.println("No se pudo eliminar");
			else
				System.out.println("Cancion eliminada!");

		}
	}

	/**
	 * Añade una cancion al top
	 * @throws AutorNoValidoException 
	 * @throws CancionNoValidoException 
	 * @throws FechaNoValidaException 
	 * @throws PosicionNoValidaException 
	 */
	private static void annadirCancion() throws AutorNoValidoException, CancionNoValidoException, FechaNoValidaException, PosicionNoValidaException {	
		int posicion;
		
		posicion = topMusic.posicionValida(Teclado.leerEntero("Posicion:"));
		
		Cancion cancion = new Cancion(Teclado.leerCadena("Titulo:"), Teclado.leerCadena("Artista:"), Teclado.leerEntero("Año de grabacion:"));
		if (!topMusic.annadir(posicion, cancion)) {
			System.out.println("No se pudo añadir");
		} else
			System.out.println("Cancion añadida!");

	}

	/**
	 * Comprueba que el top no este vacio
	 * 
	 * @return
	 */
	private static boolean showIfIsEmpty() {
		if (topMusic.isEmpty()) {
			System.out.println("Top vacio!");
			return false;
		}
		return true;
	}
}
