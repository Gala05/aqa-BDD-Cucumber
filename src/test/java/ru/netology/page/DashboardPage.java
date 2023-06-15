package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
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

//    public int getFirstCardBalance() {
//        val text = cards.first().text();
//        return extractBalance(text);
//    }

    public TransferPage selectCardToTransfer1(String firstCard) {
        $$("[data-test-id='action-deposit']").first().click();
        return new TransferPage();
    }

    public void makeTransfer1(String sum, String secondCard) {
        $("[data-test-id='amount'] .input__control").setValue(sum);
        $("[data-test-id='from'] .input__control").setValue(secondCard);
        $("[data-test-id='action-transfer']").click();
    }

    public boolean getFirstCardBalance1(String firstCard, String sum) {
        val text = cards.first().text();
        extractBalance(text);
        if (!text.equals(sum)) {
            return false;
        } else {
            return true;
        }
    }

//    public int getCardBalance(int id) {
//        var text = cards.get(id).getText();
//        return extractBalance(text);
//    }
//
//    public int getCardBalance(DataHelper.CardInfo cardInfo) {
//        var text = cards.findBy(Condition.text(cardInfo.getCardNumber().substring(15))).getText();
//        return extractBalance(text);
//    }
//
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
//    public TransferPage selectCardToTransfer(DataHelper.CardInfo cardInfo) {
//        cards.findBy(attribute("data-test-id",cardInfo.getCardId())).$("button").click();
//        return new TransferPage();
//    }
}