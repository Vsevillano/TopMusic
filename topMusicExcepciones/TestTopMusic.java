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

	public static void main(String[] args) throws Exception {
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
	 * @throws Exception
	 *             Si el autor, el titulo, el año, la posicion no son correctos,
	 *             o bien el top esta vacio, o bien una cancion alcanza un
	 *             limite al subir o bajar
	 */
	private static void gestionarOpciones(int opcion) throws Exception {
		switch (opcion) {
		case 1:
			// Añadir cancion
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
	 * 
	 * @throws TopVacioException
	 *             Si el top esta vacio
	 */
	private static void mostrarCima() throws TopVacioException {
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
	 * @throws TopVacioException
	 *             Si el top esta vacio
	 */
	private static void mostrarTop() throws TopVacioException {
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
	 * @throws PosicionNoValidaException
	 *             Si la posicion introducida no es valida
	 * @throws LimiteTopException
	 *             Si la cancion alcanza el limite por abajo
	 * @throws TopVacioException
	 *             Si el top esta vacio
	 */
	private static void bajarCancion() throws PosicionNoValidaException, LimiteTopException, TopVacioException {
		try {
			if (showIfIsEmpty()) {
				if (topMusic.bajar(
						topMusic.posicionValida(Teclado.leerEntero("Posicion entre 1 y " + topMusic.size() + " :"))))
					System.out.println("Cancion bajada!");
			}
		} catch (PosicionNoValidaException | LimiteTopException | TopVacioException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Sube una cancion del top
	 * 
	 * @throws PosicionNoValidaException
	 *             Si la posicion introducida no es valida
	 * @throws LimiteTopException
	 *             Si la cancion alcanza el limite por arriba
	 * @throws TopVacioException
	 *             Si el top esta vacio
	 */
	private static void subirCancion() throws PosicionNoValidaException, LimiteTopException, TopVacioException {
		try {
			if (showIfIsEmpty()) {
				if (topMusic.subir(
						topMusic.posicionValida(Teclado.leerEntero("Posicion entre 1 y " + topMusic.size() + " :"))))
					System.out.println("Cancion subida!");
			}
		} catch (PosicionNoValidaException | LimiteTopException | TopVacioException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Elimina una cancion del top
	 * 
	 * @throws PosicionNoValidaException
	 *             Si la posicion introducida no es valida
	 * @throws TopVacioException
	 *             Si el top esta vacio
	 */
	private static void eliminarCancion() throws PosicionNoValidaException, TopVacioException {
		try {
			if (showIfIsEmpty()) {
				if (topMusic.sacar(
						topMusic.posicionValida(Teclado.leerEntero("Posicion entre 1 y " + topMusic.size() + " :"))))
					System.out.println("Cancion eliminada!");
			}
		} catch (PosicionNoValidaException | TopVacioException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Añade una cancion al top
	 * 
	 * @throws Exception
	 *             si el titulo, autor, año o posicion no son correctas o esta
	 *             vacio
	 */
	private static void annadirCancion() throws Exception {
		int posicion;

		try {
			posicion = topMusic.posicionValida(Teclado.leerEntero("Posicion entre 1 y " + (topMusic.size()+1) + " :"));

			Cancion cancion = new Cancion(Teclado.leerCadena("Titulo:"), Teclado.leerCadena("Artista:"),
					Teclado.leerEntero("Año de grabacion:"));
			if (topMusic.annadir(posicion, cancion))
				System.out.println("Cancion añadida!");
		} catch (PosicionNoValidaException | CancionNoValidoException | AutorNoValidoException
				| FechaNoValidaException e) {
			System.err.println(e.getMessage() + "\nNo se pudo añadir");
		}
	}

	/**
	 * Comprueba que el top no este vacio
	 * 
	 * @return true o false en funcion de si esta vacio o no
	 * @throws TopVacioException
	 *             Si el top está vacio
	 */
	private static boolean showIfIsEmpty() throws TopVacioException {
		if (topMusic.isEmpty()) {
			throw new TopVacioException("El top está vacío!");
		}
		return true;
	}
}