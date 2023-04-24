package ru.netology.page.object.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private static SelenideElement loginField = $ ("[data-test-id=login] input");
    private static SelenideElement passwordField = $ ("[data-test-id=password] input");
    private static SelenideElement loginButton = $ ("[data-test-id=action-login]");

    public static VerificationPage validLogin(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
        return new VerificationPage();
    }
}