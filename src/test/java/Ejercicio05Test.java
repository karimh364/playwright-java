import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Ejercicio05Test extends BaseTest {

    @Test
    void loginCompleto() {

        page.navigate("https://the-internet.herokuapp.com/login");

        Locator campoUsuario = page.getByLabel("Username");
        Locator campoPassword = page.getByLabel("Password");

        Locator botonLogin = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("Login")
        );

        campoUsuario.fill("tomsmith");
        campoPassword.fill("SuperSecretPassword!");

        botonLogin.click();

        assertThat(page)
                .hasURL("https://the-internet.herokuapp.com/secure");

        // Espera utilizada solamente para la grabación del video
        page.waitForTimeout(2000);
    }

    @Test
    void loginFallido() {

        page.navigate("https://the-internet.herokuapp.com/login");

        page.getByLabel("Username").fill("usuarioIncorrecto");
        page.getByLabel("Password").fill("claveIncorrecta");

        page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")
        ).click();

        Locator mensajeError =
                page.getByText("Your username is invalid!");

        assertThat(page)
                .hasURL("https://the-internet.herokuapp.com/login");

        assertThat(mensajeError)
                .isVisible();

        // Espera utilizada solamente para la grabación del video
        page.waitForTimeout(2000);
    }
}