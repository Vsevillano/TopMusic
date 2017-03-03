package topMusicExcepciones;

import java.util.ArrayList;
import java.util.Iterator;

import topMusicExcepciones.excepciones.AutorNoValidoException;
import topMusicExcepciones.excepciones.CancionNoValidoException;
import topMusicExcepciones.excepciones.FechaNoValidaException;
import topMusicExcepciones.excepciones.LimiteTopException;
import topMusicExcepciones.excepciones.PosicionNoValidaException;
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
 * Top Music (Con excepciones). 
 * Mejoremos la versión anterior, pero como conocemos QUÉ ES una excepción, vamos a utilizarla. 
 * Los errores los controlaremos mediante las siguientes excepciones:
 *
 * 1. FechaNoValidaException
 * 2. CancionNoValidaException.
 * 3. AutorNoValidoException
 * 4. PosicionNoValidaExceptio
 * 
 * Y recuerda:
 * 
 * 1. En caso de error, el usuario ha de saber CON EXACTITUD cuál ha sido el problema.
 * 		1. Error al insertar la canción: título no es válido.
 * 		2. Error al insertar la canción: autor no es válido
 * 		3. Error al borrar la canción: posición no válida
 * 		4. Error al bajar la canción: posición no válida.
 * 		5. Error al subir la canción: la canción está la primera.
 * 2. El método Cancion.getInstance() ya no es necesario.
 * 3. Sigue usando expresiones regulares
 * 
 * Utiliza pruebas unitarias para el control de errores.
 * 
 * - El título de la canción: Me too, Don´t let me down, 19 días y 500 noches, Come...
 * - El autor/grupo: The B-52's, Jain...
 * - El año: Nunca posterior al año actual
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class TopMusic {
	/**
	 * ArrayList para el topMusic
	 */
	private ArrayList<Cancion> topMusic;

	/**
	 * Constructor
	 */
	public TopMusic() {
		topMusic = new ArrayList<Cancion>();
	}

	/**
	 * Añade una cancion al topMusic
	 * 
	 * @param index
	 *            Posicion de la cancion
	 * @param cancion
	 *            a añadir al top
	 * @return true si todo es correcto
	 * @throws PosicionNoValidaException 
	 * @throws CancionNoValidoException 
	 * @throws FechaNoValidaException 
	 * @throws AutorNoValidoException 
	 */
	void annadir(int index, String titulo, String artista, int fecha) throws PosicionNoValidaException, AutorNoValidoException, FechaNoValidaException, CancionNoValidoException {		
		try {
			topMusic.add(index - 1, new Cancion(titulo, artista, fecha));
		} catch (IndexOutOfBoundsException e) {
			throw new PosicionNoValidaException("Posicion invalida");
		}
	}

	/**
	 * Saca una cancion del top music
	 * 
	 * @param index
	 *            de la cancion a sacar
	 * @throws PosicionNoValidaException
	 *             En caso de que la posicion no sea valida
	 */
	void sacar(int index) throws PosicionNoValidaException {
		try {
			topMusic.remove(index - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new PosicionNoValidaException("Posicion invalida");
		}
	}

	/**
	 * Sube una posicion una cancion
	 * 
	 * @param index
	 *            de la cancion a subir
	 * @throws PosicionNoValidaException
	 *             En caso de que la posicion no sea valida
	 */
	void subir(int index) throws PosicionNoValidaException {
		try {
			topMusic.add(index - 2, topMusic.remove(index - 1));
		} catch (IndexOutOfBoundsException e) {
			throw new PosicionNoValidaException("No puede subir mas");
		}
	}

	/**
	 * Baja una posicion una cancion
	 * 
	 * @param index
	 *            de la cancion a bajar
	 * @throws PosicionNoValidaException
	 *             En caso de que la posicion no sea valida
	 */
	void bajar(int index) throws PosicionNoValidaException {
		try {
			topMusic.add(index, topMusic.remove(index - 1));
		} catch (IndexOutOfBoundsException e) {
			throw new PosicionNoValidaException("No puede bajar mas");
		}
	}

	/**
	 * Muestra el top Music
	 */
	StringBuilder mostrarTopMusic() {
		StringBuilder cadena = new StringBuilder("");
		int i = 1;
		for (Iterator<Cancion> iterator = topMusic.iterator(); iterator.hasNext();)
			cadena.append("\n" + (i++) + " " + iterator.next());
		return cadena;
	}

	/**
	 * Muestra la cancion del top
	 */
	String masEscuchada() {
		return "*** Top 1: " + topMusic.get(0) + " ***";
	}

	/**
	 * Recoge una posicion valida
	 * 
	 * @param index
	 *            Indice de la posicion
	 * @return El indice si es valido
	 * @throws PosicionNoValidaException
	 */
	int posicionValida(int index) throws PosicionNoValidaException {
		if (index - 1 > topMusic.size() || index - 1 < 0)
			throw new PosicionNoValidaException("Posicion invalida");
		return index;
	}

	/**
	 * indica si la lista esta vacia o no
	 * 
	 * @return true si está vacio
	 */
	boolean isEmpty() {
		return topMusic.isEmpty();
	}

	/**
	 * Devuelve el numero de elementos en el topMusic
	 * @return un entero con el numero de elementos
	 */
	int size() {
		return topMusic.size();
	}
}