package com.ya;
import io.qameta.allure.Step;

public class Account {

    private String name;

    public Account(String name) {
        this.name = name;
    }

    @Step("Проверка имени |{this.name}|")
    public boolean checkNameToEmboss() {
        try {
            return name.matches("^(?=.{3,19}$)\\S{1,17}\\s\\S{1,17}$");
        } catch (NullPointerException ex) {
            return false;
        }
    }
}