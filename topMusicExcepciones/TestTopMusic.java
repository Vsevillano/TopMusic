package topMusicExcepciones;

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

	public static void main(String[] args)
			throws AutorNoValidoException, CancionNoValidoException, PosicionNoValidaException, MaximoTopException {
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
	 * @throws AutorNoValidoException
	 * @throws CancionNoValidoException
	 * @throws PosicionNoValidaException
	 * @throws MaximoTopException
	 */
	private static void gestionarOpciones(int opcion)
			throws AutorNoValidoException, CancionNoValidoException, PosicionNoValidaException, MaximoTopException {
		switch (opcion) {
		case 1:
			// A�adir cancion
			try {
				annadirCancion();
			} catch (AutorNoValidoException e) {
				System.err.println(e.getMessage());
			} catch (CancionNoValidoException e) {
				System.err.println(e.getMessage());
			} catch (FechaNoValidaException e) {
				System.err.println(e.getMessage());
			} catch (PosicionNoValidaException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 2:
			// Eliminar cancion
			try {
				eliminarCancion();
			} catch (PosicionNoValidaException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 3:
			// Subir cancion
			try {
				subirCancion();
			} catch (PosicionNoValidaException e) {
				System.err.println(e.getMessage());
			} catch (MaximoTopException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 4:
			// Bajar cancion
			try {
				bajarCancion();
			} catch (PosicionNoValidaException e) {
				System.err.println(e.getMessage());
			} catch (MaximoTopException e) {
				System.err.println(e.getMessage());
			}
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
	 * 
	 * @throws PosicionNoValidaException
	 * @throws MaximoTopException
	 */
	private static void bajarCancion() throws PosicionNoValidaException, MaximoTopException {
		if (showIfIsEmpty()) {
			if (topMusic.bajar(topMusic.posicionValida(Teclado.leerEntero("Posicion:"))))
				System.out.println("Cancion bajada!");
		}
	}

	/**
	 * Sube una cancion del top
	 * 
	 * @throws PosicionNoValidaException
	 * @throws MaximoTopException
	 */
	private static void subirCancion() throws PosicionNoValidaException, MaximoTopException {
		if (showIfIsEmpty()) {
			if (topMusic.subir(topMusic.posicionValida(Teclado.leerEntero("Posicion:"))))
				System.out.println("Cancion subida!");
		}
	}

	/**
	 * Elimina una cancion del top
	 * 
	 * @throws PosicionNoValidaException
	 * @throws MaximoTopException
	 */
	private static void eliminarCancion() throws PosicionNoValidaException {
		if (showIfIsEmpty()) {
			if (topMusic.sacar(topMusic.posicionValida(Teclado.leerEntero("Posicion:"))))
				System.out.println("Cancion eliminada!");
		}
	}

	/**
	 * A�ade una cancion al top
	 * 
	 * @throws AutorNoValidoException
	 * @throws CancionNoValidoException
	 * @throws FechaNoValidaException
	 * @throws PosicionNoValidaException
	 */
	private static void annadirCancion()
			throws AutorNoValidoException, CancionNoValidoException, FechaNoValidaException, PosicionNoValidaException {
		int posicion;

		posicion = topMusic.posicionValida(Teclado.leerEntero("Posicion:"));

		Cancion cancion = new Cancion(Teclado.leerCadena("Titulo:"), Teclado.leerCadena("Artista:"),
				Teclado.leerEntero("A�o de grabacion:"));
		if (topMusic.annadir(posicion, cancion))
			System.out.println("Cancion a�adida!");
	}

	/**
	 * Comprueba que el top no este vacio
	 * 
	 * @return true o false en funcion de si esta vacio o no
	 */
	private static boolean showIfIsEmpty() {
		if (topMusic.isEmpty()) {
			System.out.println("Top vacio!");
			return false;
		}
		return true;
	}
}
