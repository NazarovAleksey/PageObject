package ru.netology.web.page;

import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MoneyTransferPage {
    public void submitTransfer(int sum, DataHelper.CardInfo info) {
        $("[data-test-id='amount'] input").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='amount'] input").setValue(String.valueOf(sum));
        $("[data-test-id='from'] input ").sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        $("[data-test-id='from'] input ").setValue(info.getCardNumber());
        $$(".button__content").find(exactText("Пополнить")).click();
    }
}
