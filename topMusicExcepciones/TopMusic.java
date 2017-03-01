package topMusicExcepciones;

import java.util.ArrayList;
import java.util.Iterator;
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
	 * Añade una cancion al TopMusic
	 * 
	 * @param cancion
	 *            a añadir al top
	 * @param titulo
	 *            de la cancion
	 * @param artista
	 *            de la cancion
	 * @param anoGrabacion
	 *            de la cancion
	 */
	boolean annadir(int index, Cancion cancion) {
		if (!fueraDeRango(index - 1))
			return false;
		topMusic.add(index - 1, cancion);
		return true;
	}

	/**
	 * Saca una cancion del top music
	 * 
	 * @param index
	 *            de la cancion a sacar
	 * @throws PosicionNoValidaException 
	 * @throws MaximoTopException 
	 */
	boolean sacar(int index) throws PosicionNoValidaException {
		if (!fueraDeRango(index))
			throw new PosicionNoValidaException("Posicion invalida");
		topMusic.remove(index - 1);
		return true;
	}

	/**
	 * Sube una posicion una cancion
	 * 
	 * @param index
	 *            de la cancion a subir
	 * @throws MaximoTopException
	 * @throws PosicionNoValidaException 
	 */
	boolean subir(int index) throws MaximoTopException, PosicionNoValidaException {
		if (!fueraDeRango(index))
			throw new PosicionNoValidaException("Posicion invalida");
		if (index - 2 < 0)
			throw new MaximoTopException("No puede subir mas");
		topMusic.add(index - 2, topMusic.remove(index - 1));
		return true;
	}

	/**
	 * Baja una posicion una cancion
	 * 
	 * @param index
	 *            de la cancion a bajar
	 * @throws MaximoTopException
	 * @throws PosicionNoValidaException 
	 */
	boolean bajar(int index) throws MaximoTopException, PosicionNoValidaException {
		if (!fueraDeRango(index))
			throw new PosicionNoValidaException("Posicion invalida");
		if (index > topMusic.size() - 1)
			throw new MaximoTopException("No puede bajar mas");
		topMusic.add(index, topMusic.remove(index - 1));
		return true;
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
	 * Comprueba que el indice introducido no este fuera de rango
	 * 
	 * @param index
	 * @return
	 */
	private boolean fueraDeRango(int index) {
		if (index < 0 || index > topMusic.size())
			return false;
		return true;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder("");
		if (topMusic.size() < 10) {
			for (int i = 0; i < topMusic.size(); i++) {
				cadena.append("(" + (i + 1) + ")" + topMusic.get(i) + "\n");
			}
		}
		return "Top Music:\n" + cadena;
	}
}