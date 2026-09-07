import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {

    @Test
    void test_loginExitoso() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigate();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        DashboardPage dashboardPage = new DashboardPage(page);

        assertThat(page)
                .hasURL("https://the-internet.herokuapp.com/secure");

        assertThat(dashboardPage.getHeading())
                .isVisible();

        assertThat(dashboardPage.getMessage())
                .containsText("You logged into a secure area!");
                //.containsText("MENSAJE INCORRECTO PARA PROBAR SCREENSHOT");
    }

    @Test
    void test_loginFallido() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigate();
        loginPage.login("usuarioIncorrecto", "claveIncorrecta");

        assertThat(page)
                .hasURL("https://the-internet.herokuapp.com/login");

        assertThat(loginPage.getMessage())
                .isVisible();

        assertThat(loginPage.getMessage())
                .containsText("Your username is invalid!");
    }
}