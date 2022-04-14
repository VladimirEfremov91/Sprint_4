package com.ya;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;

public class Account {

    private String name;
    public Account(String name) {
        this.name = name;
    }
    @Step("Проверка имени")
    public boolean checkNameToEmboss() {
        String firstChar = String.valueOf(name.charAt(0));
        String lastChar = String.valueOf(name.charAt(name.length() - 1));
        int spaceCounter = StringUtils.countMatches(name, " ");
        if (name.length() < 3 || name.length() > 19 || firstChar.equals(" ") || lastChar.equals(" ") || spaceCounter != 1 ) {
            return false;
        }
        return true;
    }
}

