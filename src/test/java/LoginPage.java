import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

    private static final String LOGIN_URL =
            "https://the-internet.herokuapp.com/login";

    private final Page page;
    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator message;

    public LoginPage(Page page) {
        this.page = page;

        usernameInput = page.getByLabel("Username");
        passwordInput = page.getByLabel("Password");

        loginButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Login")
        );

        message = page.locator("#flash");
    }

    public void navigate() {
        page.navigate(LOGIN_URL);
    }

    public void login(String username, String password) {
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
    }

    public Locator getMessage() {
        return message;
    }
}