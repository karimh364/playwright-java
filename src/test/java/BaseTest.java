import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected boolean testFailed;

    @RegisterExtension
    final TestExecutionExceptionHandler failureDetector =
            (extensionContext, throwable) -> {
                testFailed = true;
                throw throwable;
            };

    @BeforeEach
    void setUp() {
        testFailed = false;

        boolean headless =
                "true".equalsIgnoreCase(System.getenv("CI"));

        playwright = Playwright.create();

        browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions()
                        .setHeadless(headless));

        context = browser.newContext();

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();
    }

    @AfterEach
    void tearDown(TestInfo testInfo) throws IOException {
        String testName = testInfo.getTestMethod()
                .map(method -> method.getName())
                .orElse("test");

        if (testFailed) {
            Path screenshotDirectory =
                    Paths.get("build", "screenshots");

            Files.createDirectories(screenshotDirectory);

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(screenshotDirectory.resolve(
                            testName + "-failure.png"
                    )));
        }

        Path traceDirectory = Paths.get("build", "traces");
        Files.createDirectories(traceDirectory);

        context.tracing().stop(new Tracing.StopOptions()
                .setPath(traceDirectory.resolve(
                        testName + "-trace.zip"
                )));

        context.close();
        browser.close();
        playwright.close();
    }
}