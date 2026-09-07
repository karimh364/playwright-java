import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage {

    private final Locator heading;
    private final Locator message;

    public DashboardPage(Page page) {
        heading = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions()
                        .setName("Secure Area")
                        .setExact(true)
        );

        message = page.locator("#flash");
    }

    public Locator getHeading() {
        return heading;
    }

    public Locator getMessage() {
        return message;
    }
}