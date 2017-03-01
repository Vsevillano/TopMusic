package topMusicExcepciones;

import java.util.Calendar;
import java.util.regex.Pattern;

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
public class Cancion {
	private static final Calendar CALENDAR = Calendar.getInstance();
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
	 * Año de grabacion
	 */
	private int anoGrabacion;
	/**
	 * Patron para el titulo y el artista
	 */
	private static final Pattern patronNombre = Pattern.compile("([´,'\\-a-zA-ZáéíóúñÑ0-9]+\\s?){2,}");

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
	 * @param artista
	 * @param anoGrabacion
	 * @throws AutorNoValidoException 
	 * @throws CancionNoValidoException 
	 * @throws FechaNoValidaException 
	 */
	public Cancion(String titulo, String artista, int anoGrabacion) throws AutorNoValidoException, CancionNoValidoException, FechaNoValidaException {
		if (nombreValido(titulo))
		setTitulo(titulo);
		else throw new CancionNoValidoException("El titulo no es valido");
		if (nombreValido(artista))
		setArtista(artista);
		else throw new AutorNoValidoException("El artista no es valido");
		if (anoGrabacion > 1950 && anoGrabacion <= ANNIO)
		setAnoGrabacion(anoGrabacion);
		else throw new FechaNoValidaException("La fecha no es valida");
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
	 */
	private void setTitulo(String titulo) {
			this.titulo = titulo;
	}

	/**
	 * Obtiene el artista
	 * 
	 * @return
	 */
	private String getArtista() {
		return artista;
	}

	/**
	 * Modifica el valor de artista
	 * 
	 * @param artista
	 */
	private void setArtista(String artista) {
			this.artista = artista;
	}

	/**
	 * Obtiene el año de grabacion
	 * 
	 * @return
	 */
	private int getAnoGrabacion() {
		return anoGrabacion;
	}

	/**
	 * Modifica el valor de año de grabacion
	 * 
	 * @param anoGrabacion
	 */
	private void setAnoGrabacion(int anoGrabacion) {
			this.anoGrabacion = anoGrabacion;
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