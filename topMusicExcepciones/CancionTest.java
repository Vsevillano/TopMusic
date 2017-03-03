package topMusicExcepciones;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import topMusicExcepciones.excepciones.AutorNoValidoException;
import topMusicExcepciones.excepciones.CancionNoValidoException;
import topMusicExcepciones.excepciones.FechaNoValidaException;

public class CancionTest {

	@Test
	public void testCancion() {
		try {
			Cancion cancion = new Cancion("", "", 0);

			Cancion cancion2 = new Cancion("a", "", 0);
			Cancion cancion3 = new Cancion(" ", "a", 0);
		} catch (AutorNoValidoException e) {
			
		} catch (FechaNoValidaException e) {
			
		} catch (CancionNoValidoException e) {
			
		}

	}

}
