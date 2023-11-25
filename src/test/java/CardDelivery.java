import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDelivery {

    @BeforeEach
    void setUpp() {
        open("http://localhost:7777/");
    }

    //Отправка формы с валидными данными + дата с точками
    @Test
    void validSubmissionOne() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        LocalDate currentDate = LocalDate.now(); // Получить текущую дату
        LocalDate futureDate = currentDate.plusDays(3); // Добавить дни к текущей дате
        String Date = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); // Форматировать дату в строку в формате
        $("[data-test-id='date'] .input__control").setValue(Date);
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
    }

    //Отправка формы с валидными данными + дата без точек
    @Test
    void validSubmissionTwo() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        LocalDate currentDate = LocalDate.now(); // Получить текущую дату
        LocalDate futureDate = currentDate.plusDays(4); // Добавить дни к текущей дате
        String Date = futureDate.format(DateTimeFormatter.ofPattern("ddMMyyyy")); // Форматировать дату в строку в формате
        $("[data-test-id='date'] .input__control").setValue(Date);
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(11));
    }

    //Отправка формы с валидными данными без отмеченного чекбокса
    @Test
    void validSubmissionWithoutCheckbox() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        LocalDate currentDate = LocalDate.now(); // Получить текущую дату
        LocalDate futureDate = currentDate.plusDays(5); // Добавить дни к текущей дате
        String Date = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); // Форматировать дату в строку в формате
        $("[data-test-id='date'] .input__control").setValue(Date);
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $(".button.button_view_extra").click();
        $("[data-test-id='agreement'].input_invalid").shouldHave(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    //Отправка пустой формы без отмеченного чекбокса
    @Test
    void emptyFormWithoutCheckbox() {
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("");
        $("[data-test-id='name'] .input__control").setValue("");
        $("[data-test-id='phone'] input").setValue("");
        $(".button.button_view_extra").click();
        $("[data-test-id='city'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    //Отправка пустой формы с отмеченным чекбоксом
    @Test
    void emptyFormWithCheckbox() {
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("");
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='city'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    //Отправка формы с пустым полем "Город"
    @Test
    void validSubmissionWithoutCity() {
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        LocalDate currentDate = LocalDate.now(); // Получить текущую дату
        LocalDate futureDate = currentDate.plusDays(6); // Добавить дни к текущей дате
        String Date = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); // Форматировать дату в строку в формате
        $("[data-test-id='date'] .input__control").setValue(Date);
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='city'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    //Отправка формы с не валидным значением поля "Город"
    @Test
    void submittingAFormWithAnInvalidCity() {
        $("[data-test-id='city'] input").setValue("qwERTY");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        LocalDate currentDate = LocalDate.now(); // Получить текущую дату
        LocalDate futureDate = currentDate.plusDays(7); // Добавить дни к текущей дате
        String Date = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); // Форматировать дату в строку в формате
        $("[data-test-id='date'] .input__control").setValue(Date);
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }

    //Отправка формы с двумя городами в поле "Город"
    @Test
    void submittingAFormWithAnInvalidTwoCity() {
        $("[data-test-id='city'] input").setValue("Москва Питер");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        LocalDate currentDate = LocalDate.now(); // Получить текущую дату
        LocalDate futureDate = currentDate.plusDays(50); // Добавить дни к текущей дате
        String Date = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); // Форматировать дату в строку в формате
        $("[data-test-id='date'] .input__control").setValue(Date);
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }

    //Отправка формы с пустым полем "Дата"
    @Test
    void validSubmissionWithoutDate() {
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("");
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(Condition.text("Неверно введена дата"));
    }

    //Отправка формы с несуществующей датой в поле "Дата"
    @Test
    void submittingAFormWithANonExistentDate() {
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("35.15.2050");
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(Condition.text("Неверно введена дата"));
    }

    //Отправка формы с прошедшей датой в поле "Дата"
    @Test
    void submittingAFormWithAPastDate() {
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("27.11.2023");
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(Condition.text("Заказ на выбранную дату невозможен"));
    }

    //Отправка формы с пустым полем "ФИО"
    @Test
    void submittingAFormWithAnEmptyNameField() {
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        LocalDate currentDate = LocalDate.now(); // Получить текущую дату
        LocalDate futureDate = currentDate.plusDays(100); // Добавить дни к текущей дате
        String Date = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); // Форматировать дату в строку в формате
        $("[data-test-id='date'] .input__control").setValue(Date);
        $("[data-test-id='name'] .input__control").setValue("");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    //Отправка формы с не разрешенными символами "ФИО"
    @Test
    void submittingAFormWithInvalidData() {
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        LocalDate currentDate = LocalDate.now(); // Получить текущую дату
        LocalDate futureDate = currentDate.plusDays(200); // Добавить дни к текущей дате
        String Date = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); // Форматировать дату в строку в формате
        $("[data-test-id='date'] .input__control").setValue(Date);
        $("[data-test-id='name'] .input__control").setValue("QWERty");
        $("[data-test-id='phone'] input").setValue("+79500060445");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    //Отправка формы с пустым полем "Телефон"
    @Test
    void validSubmissionWithoutPhone() {
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        LocalDate currentDate = LocalDate.now(); // Получить текущую дату
        LocalDate futureDate = currentDate.plusDays(31); // Добавить дни к текущей дате
        String Date = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); // Форматировать дату в строку в формате
        $("[data-test-id='date'] .input__control").setValue(Date);
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова-Петрова");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    //Отправка формы с не валидным значением в поле "Телефон"
    @Test
    void sendingAFormWithAnInvalidValueInThePhone() {
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        LocalDate currentDate = LocalDate.now(); // Получить текущую дату
        LocalDate futureDate = currentDate.plusDays(35); // Добавить дни к текущей дате
        String Date = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); // Форматировать дату в строку в формате
        $("[data-test-id='date'] .input__control").setValue(Date);
        $("[data-test-id='name'] .input__control").setValue("Анна Кочергина-Иванова-Петрова");
        $("[data-test-id='phone'] input").setValue("Hello");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}