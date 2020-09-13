package ru.netology.web.page;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    public MoneyTransferPage replenishFirstCard() {
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] [type='button']").click();
        return new MoneyTransferPage();
    }

    public MoneyTransferPage replenishSecondCard() {
        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] [type='button']").click();
        return new MoneyTransferPage();
    }

    public static int getFirstCardBalance() {
        String selectedValue = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']").text();
        String balanceOfFirstCard = selectedValue.substring(29, selectedValue.indexOf(" ", 29));
        return Integer.parseInt(balanceOfFirstCard);
    }

    public static int getSecondCardBalance() {
        String selectedValue = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']").text();
        String balanceOfFirstCard = selectedValue.substring(29, selectedValue.indexOf(" ", 29));
        return Integer.parseInt(balanceOfFirstCard);
    }

    public void getError() {
        $("[data-test-id=error-notification]").shouldBe(visible);
    }
}
