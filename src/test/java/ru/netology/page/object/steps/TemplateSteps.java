package ru.netology.page.object.steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.page.object.data.DataHelper;
import ru.netology.page.object.page.DashboardPage;
import ru.netology.page.object.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemplateSteps {

    private DashboardPage dashboardPage;

    @Пусть("Пусть пользователь залогинен с именем {string} и паролем {string}")
    public void auth(String login, String password) {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var verificationPage = LoginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCodeFor());
    }

    @Когда("Когда пользователь переводит {string} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void makeTransfer(String amount, String debitCardNumber, int creditCardNumber) {
        var transferPage = dashboardPage.selectCardToTransfer(creditCardNumber);
        dashboardPage = transferPage.makeTransfer(amount, debitCardNumber);
    }

    @Тогда("Тогда баланс его {} карты из списка на главной странице должен стать {} рублей")
    public void verifyCreditBalance(int creditCardNumber, int expectedCreditBalance) {
        var actualCreditBalance = dashboardPage.getCardBalance(creditCardNumber);
        assertEquals(expectedCreditBalance, actualCreditBalance);
    }
}
