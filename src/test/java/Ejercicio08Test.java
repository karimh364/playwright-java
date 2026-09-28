import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Ejercicio08Test extends BaseTest {

    @Test
    void navegarConLinksYVolverAtras() {
        page.navigate("https://the-internet.herokuapp.com/");

        Locator linkLogin = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName("Form Authentication")
                        .setExact(true)
        );

        linkLogin.click();
        assertThat(page).hasURL("https://the-internet.herokuapp.com/login");
        page.waitForTimeout(1500);

        page.goBack();
        assertThat(page).hasURL("https://the-internet.herokuapp.com/");

        Locator linkCheckboxes = page.getByRole(
                AriaRole.LINK,
                new Page.GetByRoleOptions()
                        .setName("Checkboxes")
                        .setExact(true)
        );

        linkCheckboxes.click();
        assertThat(page).hasURL("https://the-internet.herokuapp.com/checkboxes");
        page.waitForTimeout(1500);

        page.goBack();
        assertThat(page).hasURL("https://the-internet.herokuapp.com/");
        page.waitForTimeout(1500);
    }
}