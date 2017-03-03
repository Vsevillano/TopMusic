package topMusicExcepciones;

import topMusicExcepciones.excepciones.AutorNoValidoException;
import topMusicExcepciones.excepciones.CancionNoValidoException;
import topMusicExcepciones.excepciones.FechaNoValidaException;
import topMusicExcepciones.excepciones.PosicionNoValidaException;
import topMusicExcepciones.excepciones.TopVacioException;
import utiles.Menu;
import utiles.Teclado;
/**
 * El usuario podr�:
 * 
 * a. A�adir una canci�n (en una posici�n) al TopMusic.
 * b. Sacar un elemento del TopMusic.
 * c. Subir un puesto en el TopMusic.
 * d. Bajar un puesto en el TopMusic.
 * e. Mostrar la lista TopMusic.
 * f. Mostrar la canci�n m�s escuchada.
 * 
 * Sobre la canci�n se almacenar� el t�tulo, artista o grupo y a�o de grabaci�n.
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

	public static void main(String[] args) throws FechaNoValidaException, AutorNoValidoException, CancionNoValidoException{
		Menu menuGeneral = new Menu("*** TOP MUSIC ***", new String[] { "A�adir cancion", "Eliminar cancion",
				"Subir puesto", "Bajar puesto", "Mostrar TopMusic", "Mostrar el n�1", "Salir" });
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
	 * @throws CancionNoValidoException 
	 * @throws AutorNoValidoException 
	 * @throws FechaNoValidaException 
	 *             Si el autor, el titulo, el a�o, la posicion no son correctos,
	 *             o bien el top esta vacio, o bien una cancion alcanza un
	 *             limite al subir o bajar
	 */
	private static void gestionarOpciones(int opcion) throws FechaNoValidaException, AutorNoValidoException, CancionNoValidoException {
		switch (opcion) {
		case 1:
			// A�adir cancion
			annadirCancion();
			break;
		case 2:
			// Eliminar cancion
			eliminarCancion();
			break;
		case 3:
			// Subir cancion
			subirCancion();
			break;
		case 4:
			// Bajar cancion
			bajarCancion();
			break;
		case 5:
			// Mostrar top
			mostrarTop();
			break;
		case 6:
			// Mostrar n�1
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
	 * Muestra el n� 1 del top
	 * 
	 */
	private static void mostrarCima() {
		try {
			if (showIfIsEmpty())
				System.out.println(topMusic.masEscuchada());
		} catch (TopVacioException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Muestra el top completo
	 * 
	 */
	private static void mostrarTop() {
		try {
			if (showIfIsEmpty())
				System.out.println(topMusic.mostrarTopMusic());
		} catch (TopVacioException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Baja una cancion del top
	 *
	 */
	private static void bajarCancion() {
		try {
			if (showIfIsEmpty()) {
				topMusic.bajar(topMusic.posicionValida(Teclado.leerEntero("Posicion entre 1 y " + topMusic.size() + " :")));
					System.out.println("Cancion bajada!");
			}
		} catch (PosicionNoValidaException | TopVacioException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Sube una cancion del top
	 * 
	 */
	private static void subirCancion() {
		try {
			if (showIfIsEmpty()) {
				topMusic.subir(topMusic.posicionValida(Teclado.leerEntero("Posicion entre 1 y " + topMusic.size() + " :")));
					System.out.println("Cancion subida!");
			}
		} catch (PosicionNoValidaException | TopVacioException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Elimina una cancion del top
	 * 
	 */
	private static void eliminarCancion() {
		try {
			if (showIfIsEmpty()) {
				topMusic.sacar(topMusic.posicionValida(Teclado.leerEntero("Posicion entre 1 y " + topMusic.size() + " :")));
				System.out.println("Cancion eliminada!");
			}
		} catch (PosicionNoValidaException | TopVacioException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * A�ade una cancion al top
	 * 
	 */
	private static void annadirCancion() {
		int posicion;
		String titulo;
		String artista;
		int fecha;
		
		try {
			posicion = topMusic.posicionValida(Teclado.leerEntero("Posicion entre 1 y " + (topMusic.size()+1) + " :"));
			titulo = Teclado.leerCadena("Titulo:");
			artista =  Teclado.leerCadena("Artista:");
			fecha = Teclado.leerEntero("A�o de grabacion:");
			
			topMusic.annadir(posicion, titulo, artista, fecha);
				System.out.println("Cancion a�adida!");
		} catch (PosicionNoValidaException | CancionNoValidoException | AutorNoValidoException
				| FechaNoValidaException e) {
			System.err.println(e.getMessage() + "\nNo se pudo a�adir");
		}
	}

	/**
	 * Comprueba que el top no este vacio
	 * 
	 * @return true o false en funcion de si esta vacio o no
	 * @throws TopVacioException
	 *             Si el top est� vacio
	 */
	private static boolean showIfIsEmpty() throws TopVacioException {
		if (topMusic.isEmpty()) {
			throw new TopVacioException("El top est� vac�o!");
		}
		return true;
	}
}