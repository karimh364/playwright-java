import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Ejercicio03Test extends BaseTest {

    @Test
    void hacerClicEnLink() {

        page.navigate("https://the-internet.herokuapp.com/");

        Locator formAuthenticationLink = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName("Form Authentication")
                        .setExact(true)
        );

        formAuthenticationLink.click();

        assertThat(page)
                .hasURL("https://the-internet.herokuapp.com/login");

        // Espera utilizada para la grabación del video
        page.waitForTimeout(3000);
    }
}