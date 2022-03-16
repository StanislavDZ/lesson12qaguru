package pages.components;

import com.codeborne.selenide.SelenideElement;
import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    //Заводим переменную с типом CalendarComponent
    CalendarComponent calendarComponent = new CalendarComponent();
    CheckForm checkForm = new CheckForm();
    //locators
    private SelenideElement
            headerTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userGenderInput = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateInput = $("#dateOfBirthInput"),
            setSubjectsInput = $("#subjectsInput"),
            hobbiesInput = $(".practice-form-wrapper #hobbiesWrapper"),
            uploadFileInput =  $("#uploadPicture"),
            userAddressInput = $("#currentAddress"),
            userStateInput = $("#state"),
            userCityInput = $("#city"),
            submitPut = $("#submit"),
            checkFormIn = $(".table-responsive");
    //actions test
    public RegistrationPage openPage() {
        open("automation-practice-form");
        headerTitle.shouldHave(text("Student Registration Form"));

        return this;
    }
    //actions
    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }
    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }
    public RegistrationPage userEmailName(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }
    public RegistrationPage userGender(String genterWrapper) {
        userGenderInput.$(byText(genterWrapper)).click();
        return this;
    }
    public RegistrationPage userNumberName(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }
    public RegistrationPage userAddressInput(String userAddress) {
        userAddressInput.setValue(userAddress);
        return this;
    }
    public void setDate(String day, String month, String date) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1987");
        $$(".react-datepicker__day").find(text("13")).click();
        //calendarComponent.dateInput(date); //оставлю как вариант на будущее
    }
    public RegistrationPage setSubjectsInput(String subj1, String subj2) {
        setSubjectsInput.setValue(subj1).pressEnter();
        setSubjectsInput.setValue(subj2).pressEnter();
        return this;
    }
    public RegistrationPage userHobbiesInput(String hobby) {
        hobbiesInput.$(byText(hobby)).click();
        return this;
    }
    public RegistrationPage uploadFile(File foto) {
        uploadFileInput.uploadFile(foto);
        return this;
    }
    public RegistrationPage userStateInput(String userState) {
        submitPut.scrollTo();
        userStateInput.click();
        userStateInput.$(byText(userState)).click();
        return this;
    }
    public RegistrationPage userCityInput(String userCity) {
        userCityInput.click();
        userCityInput.$(byText(userCity)).click();
        return this;
    }
    public RegistrationPage submitPut(String subimtForm) {
        submitPut.click();
        return this;
    }
    public RegistrationPage checkForm(String fieldName, String value) {
        checkFormIn.$(byText(fieldName)).parent().shouldHave(text(value)); //разобраться детальней с созданием форм, пока плавает понимание
        return this;
    }
}
