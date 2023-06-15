package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;


public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static TransferPage transferPage;

    @Пусть("открыта страница с формой авторизации {string}")
    public void openAuthPage(String url) {
        loginPage = Selenide.open(url, LoginPage.class);
    }

    @Когда("пользователь пытается авторизоваться с именем {string} и паролем {string}")
    public void loginWithNameAndPassword(String login, String password) {
        verificationPage = loginPage.validLogin1(login, password);
    }

    @И("пользователь вводит проверочный код 'из смс' {string}")
    public void setValidCode(String code) {
        dashboardPage = verificationPage.validVerify1(code);
    }

    @И ("пользователь нажимает кнопку пополнить карту с номером {string}")
    public void fillUp (String firstCard) {
        dashboardPage.selectCardToTransfer1(firstCard);
    }

    @Когда("пользователь в личном кабинете переводит {string} рублей с карты с номером {string} на свою 1 карту c главной страницы")
    public void transferFromSecond(String sum, String secondCard) {
        dashboardPage.makeTransfer1(sum, secondCard);
    }

    @Тогда("баланс его 1 карты из списка на главной странице должен стать {string} рублей")
    public void finalResult (String sum) {
        dashboardPage.getFirstCardBalance1(sum);
    }
}