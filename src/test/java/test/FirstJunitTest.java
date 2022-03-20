package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class FirstJunitTest {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        String name = System.getProperty("name");
        String passw = System.getProperty("passw");
        String path = System.getProperty("path");
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "700x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC",true);
        capabilities.setCapability("enableVideo",true);
        Configuration.browserCapabilities = capabilities;
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.remote = "https://" + name + ":" + passw + path;
    }

    @AfterAll
    static void addAttachments () {
        Attach.screenshotAs("Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    void FormRun() {
        step("Открываем главную страницу", () -> {
            open("/automation-practice-form");
        });
        step("Заполняем форму регистрационными данными:", () -> {
            $("#firstName").setValue("Stanislav");
            $("#lastName").setValue("Zaychenko");
            $("#userEmail").setValue("Ashestodust@yandex.ru");
            $(byText("Male")).click();
            $("#userNumber").setValue("9147851961");
            $("#dateOfBirthInput").sendKeys(Keys.CONTROL, "a");
            $("#dateOfBirthInput").sendKeys("13 apr 1987", Keys.ENTER);
            $("#subjectsInput").setValue("Comp").pressEnter();
            $("#subjectsInput").setValue("Ar").pressEnter();
            $(byText("Sports")).scrollTo().click();
            $(byText("Reading")).click();
            $(byText("Music")).click();
            //$("#uploadPicture").uploadFile(new File("src/test/resources/pic.png"));
            $("#currentAddress").setValue("Taxes, BestCh.St 17");
            $("#submit").scrollTo();
            $("#state").click();
            $(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Delhi")).click();
        });
        step("Подтверждаем заполнение формы", () -> {
            $(byText("Submit")).click();
        });
        step("Проверяем заполнение формы", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Stanislav Zaychenko"));
            $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("Ashestodust@yandex.ru"));
            $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
            $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9147851961"));
            $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("13 April,1987"));
            $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Computer Science, Arts"));
            $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Sports, Reading, Music"));
              //$(".table-responsive").$(byText("Picture")).parent().shouldHave(text("pic.png"));
            $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Taxes, BestCh.St 17"));
            $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));
        });

        step("Закрываем форму с данными", () -> {
            $(byText("Close")).scrollTo().click();
        });

    }
}

