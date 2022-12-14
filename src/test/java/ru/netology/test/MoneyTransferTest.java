package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class MoneyTransferTest {
    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    @Test
    void transferFromFirstCardsToSecondCards() {
        var transferAmount = 2000;
        var dashboardBefore = new LoginPage()
                .validLogin(getAuthInfo())
                .validVerify(getVerificationCodeFor(getAuthInfo()));
        var amount1 = DataHelper.decreaseBalance(dashboardBefore.getCardBalance(getFirstCard()), transferAmount);
        var amount2 = DataHelper.increaseBalance(dashboardBefore.getCardBalance(getSecondCard()), dashboardBefore.getCardBalance(getFirstCard()), transferAmount);
        var dashboardAfter = dashboardBefore
                .transferTo(getSecondCard())
                .transferFrom(transferAmount, getFirstCard());
        var balance1 = dashboardAfter.getCardBalance(getFirstCard());
        var balance2 = dashboardAfter.getCardBalance(getSecondCard());
        assertEquals(amount1, balance1);
        assertEquals(amount2, balance2);
    }

    @Test
    void transferFromSecondCardsToFirstCards() {
        var transferAmount = 2000;
        var dashboardBefore = new LoginPage()
                .validLogin(getAuthInfo())
                .validVerify(getVerificationCodeFor(getAuthInfo()));
        var amount1 = DataHelper.decreaseBalance(dashboardBefore.getCardBalance(getSecondCard()), transferAmount);
        var amount2 = DataHelper.increaseBalance(dashboardBefore.getCardBalance(getFirstCard()), dashboardBefore.getCardBalance(getSecondCard()), transferAmount);
        var dashboardAfter = dashboardBefore
                .transferTo(getFirstCard())
                .transferFrom(transferAmount, getSecondCard());
        var balance1 = dashboardAfter.getCardBalance(getSecondCard());
        var balance2 = dashboardAfter.getCardBalance(getFirstCard());
        assertEquals(amount1, balance1);
        assertEquals(amount2, balance2);
    }

    @Test
    void transferFullMoneyFromSecondCardsToFirstCards() {
        var transferAmount = 10000;
        var dashboardBefore = new LoginPage()
                .validLogin(getAuthInfo())
                .validVerify(getVerificationCodeFor(getAuthInfo()));
        var amount1 = DataHelper.decreaseBalance(dashboardBefore.getCardBalance(getSecondCard()), transferAmount);
        var amount2 = DataHelper.increaseBalance(dashboardBefore.getCardBalance(getFirstCard()), dashboardBefore.getCardBalance(getSecondCard()), transferAmount);
        var dashboardAfter = dashboardBefore
                .transferTo(getFirstCard())
                .transferFrom(transferAmount, getSecondCard());
        var balance1 = dashboardAfter.getCardBalance(getSecondCard());
        var balance2 = dashboardAfter.getCardBalance(getFirstCard());
        assertEquals(amount1, balance1);
        assertEquals(amount2, balance2);
    }

    @Test
    void transferFromSecondCardsToFirstCardsUnderLimit() {
        var transferAmount = 2000;
        var dashboardBefore = new LoginPage()
                .validLogin(getAuthInfo())
                .validVerify(getVerificationCodeFor(getAuthInfo()));
        var amount1 = DataHelper.decreaseBalance(dashboardBefore.getCardBalance(getSecondCard()), transferAmount);
        var amount2 = DataHelper.increaseBalance(dashboardBefore.getCardBalance(getFirstCard()), dashboardBefore.getCardBalance(getSecondCard()), transferAmount);
        var dashboardAfter = dashboardBefore
                .transferTo(getFirstCard())
                .transferFrom(transferAmount, getSecondCard());
        var balance1 = dashboardAfter.getCardBalance(getSecondCard());
        var balance2 = dashboardAfter.getCardBalance(getFirstCard());
        assertEquals(amount1, balance1);
        assertEquals(amount2, balance2);
    }
}
