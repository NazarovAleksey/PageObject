package ru.netology.web.page;

import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    public VerificationPage() {
        $("[data-test-id=code] input").shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        $("[data-test-id=code] input").setValue(verificationCode.getCode());
        $("[data-test-id=action-verify]").click();
        return new DashboardPage();
    }
}
