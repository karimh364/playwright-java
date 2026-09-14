import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Ejercicio04Test extends BaseTest {

    @Test
    void escribirTextoEnCampo() {

        page.navigate("https://the-internet.herokuapp.com/login");

        Locator campoUsuario = page.getByLabel("Username");

        campoUsuario.fill("tomsmith");

        assertThat(campoUsuario)
                .hasValue("tomsmith");

        // Espera utilizada solamente para la grabación del video
        page.waitForTimeout(3000);
    }
}