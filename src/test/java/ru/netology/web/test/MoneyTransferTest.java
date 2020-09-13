package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Перевод денег с первой карты на вторую")
    void shouldReplenishFirstCard() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstCardBeforeReplenish = DashboardPage.getFirstCardBalance();
        int balanceSecondCardBeforeReplenish = DashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.replenishFirstCard();
        val transferInfo = DataHelper.getSecondCard();
        int sum = 5000;
        moneyTransferPage.submitTransfer(sum, transferInfo);
        int balanceReplenishedCard = DataHelper.checkBalanceRechargeCard(balanceFirstCardBeforeReplenish, sum);
        int balanceDebitingCard = DataHelper.checkBalanceDebitCard(balanceSecondCardBeforeReplenish, sum);
        int balanceFirstCardAfterReplenish = DashboardPage.getFirstCardBalance();
        int balanceSecondCardAfterReplenish = DashboardPage.getSecondCardBalance();
        assertEquals(balanceReplenishedCard, balanceFirstCardAfterReplenish);
        assertEquals(balanceDebitingCard, balanceSecondCardAfterReplenish);
    }

    @Test
    @DisplayName("Перевод денег с второй карты на первую")
    void shouldReplenishSecondCard() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstCardBeforeTransaction = DashboardPage.getFirstCardBalance();
        int balanceSecondCardBeforeTransaction = DashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.replenishSecondCard();
        val transferInfo = DataHelper.getFirstCard();
        int sum = 5000;
        moneyTransferPage.submitTransfer(sum, transferInfo);
        int balanceReplenishedCard = DataHelper.checkBalanceRechargeCard(balanceSecondCardBeforeTransaction, sum);
        int balanceDebitingCard = DataHelper.checkBalanceDebitCard(balanceFirstCardBeforeTransaction, sum);
        int balanceFirstCardAfterTransaction = DashboardPage.getFirstCardBalance();
        int balanceSecondCardAfterTransaction = DashboardPage.getSecondCardBalance();
        assertEquals(balanceReplenishedCard, balanceSecondCardAfterTransaction);
        assertEquals(balanceDebitingCard, balanceFirstCardAfterTransaction);
    }

    @Test
    @DisplayName("Перевод нулевой суммы")
    void transferZeroAmount() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstCardBeforeReplenish = DashboardPage.getFirstCardBalance();
        int balanceSecondCardBeforeReplenish = DashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.replenishFirstCard();
        val transferInfo = DataHelper.getSecondCard();
        int sum = 0;
        moneyTransferPage.submitTransfer(sum, transferInfo);
        int balanceReplenishedCard = DataHelper.checkBalanceRechargeCard(balanceFirstCardBeforeReplenish, sum);
        int balanceDebitingCard = DataHelper.checkBalanceDebitCard(balanceSecondCardBeforeReplenish, sum);
        int balanceFirstCardAfterReplenish = DashboardPage.getFirstCardBalance();
        int balanceSecondCardAfterReplenish = DashboardPage.getSecondCardBalance();
        assertEquals(balanceReplenishedCard, balanceFirstCardAfterReplenish);
        assertEquals(balanceDebitingCard, balanceSecondCardAfterReplenish);
    }

    @Test
    @DisplayName("Перевод больше остатка")
    void transferOverLimit() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int balanceFirstCardBeforeReplenish = DashboardPage.getFirstCardBalance();
        int balanceSecondCardBeforeReplenish = DashboardPage.getSecondCardBalance();
        val moneyTransferPage = dashboardPage.replenishFirstCard();
        val transferInfo = DataHelper.getSecondCard();
        int sum = DashboardPage.getSecondCardBalance() + 1;
        moneyTransferPage.submitTransfer(sum, transferInfo);
        dashboardPage.getError();
    }
}
