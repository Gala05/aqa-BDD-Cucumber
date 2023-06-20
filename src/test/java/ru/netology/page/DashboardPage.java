package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $x("//h1[contains(text(), 'Ваши карты')]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage selectCardToTransfer(String cardNumb) {
        cardNumb = cardNumb.substring(12);
        $x("//div[contains(text(), " + cardNumb + ")]//button").click();
        return new TransferPage();
    }

    public void makeTransfer(String sum, String cardNumb) {
        $("[data-test-id='amount'] .input__control").setValue(sum);
        $("[data-test-id='from'] .input__control").setValue(cardNumb);
        $("[data-test-id='action-transfer']").click();
    }

    public void getCardBalance(String sum) {
        heading.shouldBe(visible);
        int firstCardBalance = getFirstCardBalance();
        Assertions.assertEquals(Integer.parseInt(sum), firstCardBalance);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}