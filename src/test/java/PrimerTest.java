import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimerTest extends BaseTest {

    @Test
    void primerScript() {

        // EJERCICIO 1: NAVEGAR A UNA PÁGINA WEB
        page.navigate("https://the-internet.herokuapp.com/login");

        // Esperamos 5 segundos para mostrarlo en el video
        page.waitForTimeout(5000);

        // EJERCICIO 2: VERIFICAR EL TÍTULO DE LA PÁGINA
        String titulo = page.title();
        assertTrue(titulo.contains("The Internet"));
    }
}