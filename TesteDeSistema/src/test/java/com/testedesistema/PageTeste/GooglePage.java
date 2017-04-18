package com.testedesistema.PageTeste;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage {

    private WebDriver driver;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
    }

    public GooglePage buscar(String termo){
        irParaPaginaDeBusca();
        digitarOTermoNoCampoDeBusca(termo);
        clicarEmPesquisar();
        return this;
    }

    private void irParaPaginaDeBusca() {
        driver.get("http://www.google.com");
    }

    private void digitarOTermoNoCampoDeBusca(String termo) {
        driver.findElement(By.name("q")).sendKeys(termo);
    }

    private void clicarEmPesquisar() {
        driver.findElement(By.name("btnG")).click();

    }
}
