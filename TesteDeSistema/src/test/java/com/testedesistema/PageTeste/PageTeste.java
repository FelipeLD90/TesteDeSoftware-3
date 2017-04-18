package com.testedesistema.PageTeste;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class PageTeste {

    @Test
    public void deve_buscar_high_tech_cursos_no_google(){
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://www.google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("high tech cursos");

        element.submit();

        assertEquals("high tech cursos - Pesquisa Google", driver.getTitle());

    }

    @Test
    public void deve_buscar_high_tech_cursos_no_google_usando_chrome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\High Tech - Aulas\\TDD\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("high tech cursos");

        element.submit();

        new WebDriverWait(driver,2).until(ExpectedConditions.titleContains("high tech cursos - Pesquisa Google"));

        assertEquals("high tech cursos - Pesquisa Google", driver.getTitle());

    }

    @Test
    public void deve_acessar_o_canal_do_youtube_da_high_tech_usando_chrome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\High Tech - Aulas\\TDD\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("high tech cursos");
        element.submit();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[contains(.,'High Tech Cursos - YouTube')]")).click();

        Thread.sleep(2000);

        assertEquals("High Tech Cursos - YouTube", driver.getTitle());

    }

    @Test
    public void deve_obter_a_data_de_um_curso_no_site_do_fabrica_usando_chrome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\High Tech - Aulas\\TDD\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.fabricadeprogramador.com.br");

        Thread.sleep(3000);

        driver.findElement(By.xpath("//a/descendant::span[text()='Cursos']")).click();

        Thread.sleep(1000);

        String textoEncontrado =
                driver.findElement(
                        By.xpath("//md-tab-content[@id='tab-content-4']/div/md-content/div/ng-include/div/ng-include/div/md-table-container/table/tbody/tr/td[3]"))
                        .getText();

        assertEquals("11/02/2017", textoEncontrado);
    }

    @Test
    public void deve_buscar_high_tech_cursos_usando_Google_Page(){
        System.setProperty("webdriver.chrome.driver", "D:\\High Tech - Aulas\\TDD\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        GooglePage googlePage = new GooglePage(driver);
        String termo = "high tech cursos";
        googlePage.buscar(termo);

        new WebDriverWait(driver,5).until(ExpectedConditions.titleContains("high tech cursos"));


        assertEquals("high tech cursos - Pesquisa Google", driver.getTitle());
    }


}
