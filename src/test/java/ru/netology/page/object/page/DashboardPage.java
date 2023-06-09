package ru.netology.page.object.page;

import com.codeborne.selenide.ElementsCollection;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private ElementsCollection cards = $$(".list__item div");

    private final String balanceStart = "баланс: ";

    private final String balanceFinish = " р.";

    private final SelenideElement heading = $("[data-test-id='dashboard']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(int index) {
        var text = cards.get(index - 1).text();
        return extractBalance(text);
    }

    public TransferPage selectCardToTransfer(int index) {
        cards.get(index - 1).$("button").click();
        return new TransferPage();
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}