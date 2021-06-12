package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm extends TestBase {

    String studentFirstName = "Jonh",
            studentLastName = "Doe",
            studentEmail = "jonh.doe@mail.com",
            gender = "Male",
            studentMobileNumber = "0123456789",
            dayOfBirth = "1",
            dayOfWeekOfBirth = "Saturday",
            monthOfBirth = "January",
            yearOfBirth = "2000",
            subject = "Computer Science",
            hobby = "Sports",
            picture = "john-doe.jpeg",
            studentAddress = "Lotus Temple Rd, Shambhu Dayal Bagh, Bahapur, New Delhi",
            state = "NCR",
            city = "Delhi";

    @Test
    void submitStudentRegistrationForm() {

        System.out.println("Hier was Natalie");

        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue(studentFirstName);
        $("#lastName").setValue(studentLastName);
        $("#userEmail").setValue(studentEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(studentMobileNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(String.format("[aria-label='Choose %s, %s %sst, %s']",
                        dayOfWeekOfBirth, monthOfBirth, dayOfBirth, yearOfBirth)).click();
        $("#subjectsInput").setValue(subject).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFromClasspath(picture);
        $("#currentAddress").setValue(studentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table tr", 1).shouldHave(text("Student Name"), text(studentFirstName + " " + studentLastName));
        $(".table tr", 2).shouldHave(text("Student Email"), text(studentEmail));
        $(".table tr", 3).shouldHave(text("Gender"), text(gender));
        $(".table tr", 4).shouldHave(text("Mobile"), text(studentMobileNumber));
        $(".table tr", 5).shouldHave(text("Date of Birth"), text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $(".table tr", 6).shouldHave(text("Subjects"), text(subject));
        $(".table tr", 7).shouldHave(text("Hobbies"), text(hobby));
        $(".table tr", 8).shouldHave(text("Picture"), text(picture));
        $(".table tr", 9).shouldHave(text("Address"), text(studentAddress));
        $(".table tr", 10).shouldHave(text("State and City"), text(state + " " + city));

        $("#closeLargeModal").click();
        $("#example-modal-sizes-title-lg").should(disappear);
    }

}