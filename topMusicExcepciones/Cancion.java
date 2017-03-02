package topMusicExcepciones;

import java.util.Calendar;
import java.util.regex.Pattern;

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
 * Top Music (Con excepciones). 
 * Mejoremos la versi�n anterior, pero como conocemos QU� ES una excepci�n, vamos a utilizarla. 
 * Los errores los controlaremos mediante las siguientes excepciones:
 *
 * 1. FechaNoValidaException
 * 2. CancionNoValidaException.
 * 3. AutorNoValidoException
 * 4. PosicionNoValidaException
 * 
 * Y recuerda:
 * 
 * 1. En caso de error, el usuario ha de saber CON EXACTITUD cu�l ha sido el problema.
 * 		1. Error al insertar la canci�n: t�tulo no es v�lido.
 * 		2. Error al insertar la canci�n: autor no es v�lido
 * 		3. Error al borrar la canci�n: posici�n no v�lida
 * 		4. Error al bajar la canci�n: posici�n no v�lida.
 * 		5. Error al subir la canci�n: la canci�n est� la primera.
 * 2. El m�todo Cancion.getInstance() ya no es necesario.
 * 3. Sigue usando expresiones regulares
 * 
 * Utiliza pruebas unitarias para el control de errores.
 * 
 * - El t�tulo de la canci�n: Me too, Don�t let me down, 19 d�as y 500 noches, Come...
 * - El autor/grupo: The B-52's, Jain...
 * - El a�o: Nunca posterior al a�o actual
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class Cancion {
	/**
	 * Clase calendario
	 */
	private static final Calendar CALENDAR = Calendar.getInstance();

	/**
	 * Obtenemos el a�o actual de la clase calendario
	 */
	private static final int ANNIO = CALENDAR.get(Calendar.YEAR);

	/**
	 * Titulo de la cancion
	 */
	private String titulo;

	/**
	 * Artista de la cancion
	 */
	private String artista;

	/**
	 * A�o de grabacion
	 */
	private int anoGrabacion;

	/**
	 * Patron para el titulo y el artista
	 */
	private static final Pattern patronNombre = Pattern.compile("([�,'\\-a-zA-Z�������0-9]+\\s?){2,}");

	/**
	 * Determina si el nombre es valido
	 * 
	 * @param nombre
	 * @return
	 */
	private static boolean nombreValido(String nombre) {
		return patronNombre.matcher(nombre).matches();
	}

	/**
	 * Constructor de la cancion
	 * 
	 * @param titulo
	 *            de la cancion
	 * @param artista
	 *            de la cancion
	 * @param anoGrabacion
	 *            A�o de grabacion
	 * @throws AutorNoValidoException
	 *             Si el autor no es valido
	 * @throws CancionNoValidoException
	 *             Si el titulo no es valido
	 * @throws FechaNoValidaException
	 *             Si la fecha no es valida
	 */
	public Cancion(String titulo, String artista, int anoGrabacion) throws Exception {
		setTitulo(titulo);
		setArtista(artista);
		setAnoGrabacion(anoGrabacion);
	}

	/**
	 * Obtiene el titulo de la cancion
	 * 
	 * @return
	 */
	private String getTitulo() {
		return titulo;
	}

	/**
	 * Modifica el titulo de la cancion
	 * 
	 * @param titulo
	 * @throws CancionNoValidoException
	 *             Si el titulo de la cancion no es valido
	 */
	private void setTitulo(String titulo) throws CancionNoValidoException {
		if (nombreValido(titulo))
			this.titulo = titulo;
		else
			throw new CancionNoValidoException("El titulo no es valido");
	}

	/**
	 * Obtiene el artista
	 * 
	 * @return el nombre del artista
	 */
	private String getArtista() {
		return artista;
	}

	/**
	 * Modifica el valor de artista
	 * 
	 * @param artista
	 *            de la cancion
	 * @throws AutorNoValidoException
	 *             Si el formato del autor no es valido
	 */
	private void setArtista(String artista) throws AutorNoValidoException {
		if (nombreValido(artista))
			this.artista = artista;
		else
			throw new AutorNoValidoException("El artista no es valido");
	}

	/**
	 * Obtiene el a�o de grabacion
	 * 
	 * @return a�o de grabacion
	 */
	private int getAnoGrabacion() {
		return anoGrabacion;
	}

	/**
	 * Modifica el valor de a�o de grabacion
	 * 
	 * @param anoGrabacion
	 *            a�o en el cual se grabo la cancion
	 * @throws FechaNoValidaException
	 *             Si la fecha no es valida
	 */
	private void setAnoGrabacion(int anoGrabacion) throws FechaNoValidaException {
		if (anoGrabacion > 1950 && anoGrabacion <= ANNIO)
			this.anoGrabacion = anoGrabacion;
		else
			throw new FechaNoValidaException("La fecha no es valida");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + getTitulo() + ", por " + getArtista() + " (" + getAnoGrabacion() + ")";
	}
}