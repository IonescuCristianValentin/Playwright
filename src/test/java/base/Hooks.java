package base;

import com.microsoft.playwright.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import utils.Config;

public class Hooks {

    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext context;
    public static Page page;

    @Before
    public void setUp() {
        if (playwright == null) {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            context = browser.newContext();
            page = context.newPage();
        }
        page.navigate(Config.BASE_URL);
    }

    @After
    public void tearDown() {
        if (page != null) page.close();
        if (context != null) context.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();

        playwright = null;
        browser = null;
        context = null;
        page = null;
    }
}