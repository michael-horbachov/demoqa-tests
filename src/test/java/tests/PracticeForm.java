package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeForm {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void submitStudentRegistrationForm() {
        String studentFirstName = "Jonh";
        String studentLastName = "Doe";
        String studentEmail = "jonh.doe@mail.com";
        String studentMobileNumber = "0123456789";
        String studentAddress = "Lotus Temple Rd, Shambhu Dayal Bagh, Bahapur, New Delhi";

        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(studentFirstName);
        $("#lastName").setValue(studentLastName);
        $("#userEmail").setValue(studentEmail);
        $("#gender-radio-1").parent().click();
        $("#userNumber").setValue(studentMobileNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("2000");
        $("[aria-label='Choose Saturday, January 1st, 2000']").click();
        $("#subjectsInput").setValue("sci").pressEnter();
        $("#hobbies-checkbox-1").parent().click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/john-doe.jpeg"));
        $("#currentAddress").setValue(studentAddress);
        $("#react-select-3-input").setValue("ncr").pressEnter();
        $("#react-select-4-input").setValue("del").pressEnter();
        $("#submit").click();

        $(".table").shouldBe(visible);
        $(".table tr", 1).shouldHave(text("Student Name"), text(studentFirstName), text(studentLastName));
        $(".table tr", 2).shouldHave(text("Student Email"), text(studentEmail));
        $(".table tr", 3).shouldHave(text("Gender"), text("Male"));
        $(".table tr", 4).shouldHave(text("Mobile"), text(studentMobileNumber));
        $(".table tr", 5).shouldHave(text("Date of Birth"), text("01 January,2000"));
        $(".table tr", 6).shouldHave(text("Subjects"), text("Computer Science"));
        $(".table tr", 7).shouldHave(text("Hobbies"), text("Sports"));
        $(".table tr", 8).shouldHave(text("Picture"), text("john-doe.jpeg"));
        $(".table tr", 9).shouldHave(text("Address"), text(studentAddress));
        $(".table tr", 10).shouldHave(text("State and City"), text("NCR Delhi"));
        $("#closeLargeModal").click();
        $(".table").shouldNotBe(visible);
    }
}