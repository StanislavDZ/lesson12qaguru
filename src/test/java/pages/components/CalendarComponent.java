package pages.components;

import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void dateInput(String day, String month, String year) {
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(month);
        $(byText(day)).click();
        //$("#dateOfBirthInput").sendKeys( Keys.CONTROL,"a"); //универсальный вариант заполнения любых полей с заменой текста
        //$("#dateOfBirthInput").sendKeys( "13 apr 1987", Keys.ENTER);
    }
}
