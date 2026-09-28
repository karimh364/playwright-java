import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Ejercicio07Test extends BaseTest {

    @Test
    void tomarCapturaDePantalla() {

        page.navigate("https://the-internet.herokuapp.com/login");

        Locator campoUsuario = page.getByLabel("Username");
        Locator campoPassword = page.getByLabel("Password");

        Locator botonLogin = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")
        );

        campoUsuario.fill("tomsmith");
        campoPassword.fill("SuperSecretPassword!");
        botonLogin.click();

        Locator mensajeExito =
                page.getByText("You logged into a secure area!");

        assertThat(mensajeExito).isVisible();

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(
                        "build", "screenshots",
                        "ejercicio07-login-exitoso.png"
                ))
                .setFullPage(true));

        // Espera utilizada solamente para la grabación del video
        page.waitForTimeout(3000);
    }
}