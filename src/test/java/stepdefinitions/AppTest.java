package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static stepdefinitions.Hooks.scenarioGlobal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static stepdefinitions.Hooks.driver;


public class AppTest {

    @Given("que estou com o app aberto")
    public void app_aberto() throws Exception {
        //scenarioGlobal.log("hi :)");
        Thread.sleep(500);
    }

    @When("informo credenciais validas")
    public void Informo_crecenciais_validas() {
        WebElement el1 = driver.findElement(AppiumBy.accessibilityId("test-Username"));
        el1.clear();
        el1.sendKeys("standard_user");
        WebElement el2 = driver.findElement(AppiumBy.accessibilityId("test-Password"));
        el2.clear();
        el2.sendKeys("secret_sauce");
        WebElement el3 = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
        el3.click();
    }

    @Then("o aplicativo deve direcionar a tela inicial")
    public void O_aplicativo_deve_direcionar_a_tela_inicial() throws Exception {
        Thread.sleep(500);
        String actualProductsText = driver.findElement(
                new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"PRODUCTS\")")
        ).getText();
        assertEquals("PRODUCTS", actualProductsText);
        Thread.sleep(500);
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print Tela Inicial");
    }

    @Given("que estou na tela de login")
    public void que_estou_na_tela_de_login() throws Exception {
        Thread.sleep(500);
        WebElement menuButton = driver.findElement(
                AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup/android.widget.ImageView")
        );
        menuButton.click();

        Thread.sleep(500);

        WebElement logoutButton = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@text='LOGOUT']")
        );
        logoutButton.click();
    }

    @When("informo credenciais invalidas")
    public void informo_credenciais_invalidas() throws Exception {
        Thread.sleep(1000);

        WebElement el1 = driver.findElement(AppiumBy.accessibilityId("test-Username"));
        el1.sendKeys("locked_out_user");
        WebElement el2 = driver.findElement(AppiumBy.accessibilityId("test-Password"));
        el2.sendKeys("secret_sauce");
        WebElement el3 = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
        el3.click();
    }

    @Then("o app mostra a mensagem usuario bloqueado")
    public void o_app_mostra_a_mensagem_usuario_bloqueado() throws Exception {
        WebElement el1 = driver.findElement(AppiumBy.accessibilityId("test-Username"));
        el1.sendKeys("locked_out_user");
        WebElement el2 = driver.findElement(AppiumBy.accessibilityId("test-Password"));
        el2.sendKeys("secret_sauce");
        WebElement el3 = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
        el3.click();
        String actualLockedOutMessage = driver.findElement(
                new AppiumBy.ByAndroidUIAutomator(
                        "new UiSelector().text(\"Sorry, this user has been locked out.\")"
                )
        ).getText();
        assertEquals("Sorry, this user has been locked out.", actualLockedOutMessage);
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print User Bloqueado");
    }

    @When("informo credenciais aleatorias")
    public void informo_credenciais_aleatorias() throws Exception {
        Thread.sleep(1000);

        WebElement el1 = driver.findElement(AppiumBy.accessibilityId("test-Username"));
        el1.clear();
        WebElement el2 = driver.findElement(AppiumBy.accessibilityId("test-Password"));
        el2.clear();
        el1.sendKeys("felipefee");
        el2.sendKeys("123456");
        WebElement el3 = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
        el3.click();

        Thread.sleep(500);
    }

    @Then("o app mostra a mensagem de usuario nao cadastrado")
    public void mensagem_usuario_invalido() throws Exception {
        String actualLockedOutMessage = driver.findElement(
                new AppiumBy.ByAndroidUIAutomator(
                        "new UiSelector().text(\"Username and password do not match any user in this service.\")"
                )
        ).getText();
        assertEquals("Username and password do not match any user in this service.", actualLockedOutMessage);
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print Login Dados Invalidos");
    }

    @Given("que estou logado no app")
    public void que_estou_logado() throws Exception {
        Thread.sleep(1000);
        Informo_crecenciais_validas();
    }

    @When("seleciono o filtro de ordenacao por nome, do A ao Z")
    public void filtro_nome_descrente() throws Exception {
        Thread.sleep(1000);
        WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(5)"));
        el2.click();
        Thread.sleep(1000);
        WebElement el3 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Name (A to Z)\")"));
        el3.click();
    }

    @Then("o filtro de ordenacao por nome crescente deve ser aplicado")
    public void valida_filtro_ordem_crescente() throws Exception {
        Thread.sleep(1000);
        WebElement itemTitle = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@content-desc='test-Item title' and @text='Sauce Labs Backpack']")
        );
        String actualText = itemTitle.getText();
        assertEquals("Sauce Labs Backpack", actualText);
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print nome ordem crescente");
    }

    @Given("que estou na tela de produtos")
    public void que_estou_em_produtos() throws Exception {
        Thread.sleep(1000);
    }

    @When("seleciono o filtro de ordenacao por nome, do Z ao A")
    public void filtro_nome_decrescente() throws Exception {
        Thread.sleep(1000);
        WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(5)"));
        el2.click();
        Thread.sleep(1000);
        WebElement el3 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Name (Z to A)\")"));
        el3.click();
    }

    @Then("o filtro por nome decrescente deve ser aplicado")
    public void valida_filtro_ordem_decrescente() throws Exception {
        Thread.sleep(1000);
        WebElement itemTitle = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@content-desc='test-Item title' and @text='Test.allTheThings() T-Shirt (Red)']")
        );
        String actualText = itemTitle.getText();
        assertEquals("Test.allTheThings() T-Shirt (Red)", actualText);
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print nome ordem decrescente");
    }

    @When("seleciono o filtro de preco crescente")
    public void filtro_preco_crescente() throws Exception {
        Thread.sleep(1000);
        WebElement el1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(5)"));
        el1.click();
        Thread.sleep(1000);
        WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Price (low to high)\")"));
        el2.click();
    }

    @Then("o filtro por preco crescente deve ser aplicado")
    public void valida_filtro_preco_crescente() throws Exception {
        Thread.sleep(1000);
        WebElement itemTitle = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@content-desc='test-Item title' and @text='Sauce Labs Onesie']")
        );
        String actualText = itemTitle.getText();
        assertEquals("Sauce Labs Onesie", actualText, "O item 'Sauce Labs Onesie' não está visível na tela.");
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print preco ordem crescente");
    }

    @When("seleciono o filtro de preco decrescente")
    public void filtro_preco_decrescente() throws Exception {
        Thread.sleep(1000);
        WebElement el1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(5)"));
        el1.click();
        Thread.sleep(1000);
        WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Price (high to low)\")"));
        el2.click();
    }

    @Then("o filtro por preco decrescente deve ser aplicado")
    public void valida_filtro_preco_decrescente() throws Exception {
        Thread.sleep(1000);
        WebElement itemTitle = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@content-desc='test-Item title' and @text='Sauce Labs Fleece Jacket']")
        );
        String actualText = itemTitle.getText();
        assertEquals("Sauce Labs Fleece Jacket", actualText, "O item 'Sauce Labs Fleece Jacket' não está visível na tela.");
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print preco ordem decrescente");
    }

    @When("adiciono o produtos ao carrinho")
    public void add_produtos_carrinho() throws Exception {
        Thread.sleep(500);
        WebElement el1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(4)"));
        el1.click();
        Thread.sleep(500);
        WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"+\").instance(2)"));
        el2.click();
        Thread.sleep(500);
        WebElement el3 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"+\").instance(1)"));
        el3.click();
        Thread.sleep(500);
        WebElement el4 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"+\").instance(0)"));
        el4.click();
        Thread.sleep(500);
    }

    @Then("os produtos devem estar no carrinho")
    public void valida_produtos_carrinho() throws Exception {
        WebElement el2 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").instance(3)"));
        el2.click();
        Thread.sleep(2000);

        boolean tshirtFound = driver.findElements(
                AppiumBy.xpath("//*[contains(@text, 'Test.allTheThings() T-Shirt (Red)')]")
        ).size() > 0;

        boolean backpackFound = driver.findElements(
                AppiumBy.xpath("//*[contains(@text, 'Sauce Labs Backpack')]")
        ).size() > 0;
        assertTrue(tshirtFound && backpackFound, "Algum dos textos esperados não está visível na tela.");

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print Produtos no carrinho");
    }

    @Given("que estou no carrinho")
    public void que_estou_no_carrinho() throws Exception {
        driver.navigate().back();
        Thread.sleep(2000);
    }

    @When("adiciono um produto ao carrinho com drag and drop")
    public void add_produtos_carrinho_draganddrop() throws Exception {
        // Localiza o elemento pelo description
        WebElement dragElement = driver.findElement(
                new AppiumBy.ByAndroidUIAutomator(
                        "new UiSelector().description(\"test-Drag Handle\").instance(0)"
                )
        );

        // Pega o centro do elemento
        org.openqa.selenium.Point start = dragElement.getLocation();
        org.openqa.selenium.Dimension size = dragElement.getSize();
        int startX = start.getX() + size.getWidth() / 2;
        int startY = start.getY() + size.getHeight() / 2;

        // Define as coordenadas finais
        int endX = 500;
        int endY = 340;

        // Cria ação W3C
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragNDrop = new Sequence(finger, 1);

        dragNDrop.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(new Pause(finger, Duration.ofSeconds(1)));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), endX, endY));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(java.util.List.of(dragNDrop));
        Thread.sleep(500);

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print produtos carrinho (DragAndDrop)");
        //Thread.sleep(500);
    }

    @Given("que desejo realizar uma compra")
    public void que_realizo_uma_compra() throws Exception {
        Thread.sleep(2000);
        driver.findElement(
                new AppiumBy.ByAndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).flingToEnd(5)"
                )
        );
        Thread.sleep(500);
        WebElement el1 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"CHECKOUT\")"));
        el1.click();
    }

    @When("informo os dados do comprador")
    public void informo_dados_comprados() throws Exception {
        Thread.sleep(300);
        WebElement el2 = driver.findElement(AppiumBy.accessibilityId("test-First Name"));
        el2.sendKeys("Felipe");
        WebElement el3 = driver.findElement(AppiumBy.accessibilityId("test-Last Name"));
        el3.sendKeys("Eduardo");
        WebElement el4 = driver.findElement(AppiumBy.accessibilityId("test-Zip/Postal Code"));
        el4.sendKeys("99999-999");
        WebElement el5 = driver.findElement(AppiumBy.accessibilityId("test-CONTINUE"));
        el5.click();
    }

    @And("confirmo as informacoes e concluo o checkout")
    public void confirmo_checkout() throws Exception {
        Thread.sleep(1000);
        driver.findElement(
                new AppiumBy.ByAndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true)).flingToEnd(5)"
                )
        );
        Thread.sleep(2000);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(
                Duration.ZERO,
                PointerInput.Origin.viewport(),
                530,
                645
        ));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(java.util.List.of(tap));
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 530, 645));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        Thread.sleep(5000);
    }

    @Then("visualizo a confirmacao da compra")
    public void confirmacao_compra() throws Exception {
        WebElement el = driver.findElement(
                new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"THANK YOU FOR YOU ORDER\")")
        );
        String actualText = el.getText();
        assertEquals("THANK YOU FOR YOU ORDER", actualText);

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print Confirmacao de Compra");
    }

    @Given("que desejo validar a funcionalidade WebView")
    public void valida_webView() throws Exception {
        Thread.sleep(2000);
        WebElement menuButton = driver.findElement(
                AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup/android.widget.ImageView")
        );
        menuButton.click();

        Thread.sleep(500);

        WebElement logoutButton = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@text='WEBVIEW']")
        );
        logoutButton.click();
    }

    @When("abro o WebView e informo um site valido")
    public void informo_site_webview() throws Exception {
        Thread.sleep(500);
        WebElement el3 = driver.findElement(AppiumBy.accessibilityId("test-enter a https url here..."));
        el3.sendKeys("www.google.com.br");
        WebElement el4 = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"GO TO SITE\")"));
        el4.click();
    }

    @Then("visualizo o site informado")
    public void visualizo_site_digitado() throws Exception {
        Thread.sleep(2000);
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print WebView Site");
    }

    @Given("que desejo validar a funcionalidade desenho")
    public void valida_drawn() throws Exception {
//        Informo_crecenciais_validas();
        Thread.sleep(2000);
        WebElement menuButton = driver.findElement(
                AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup/android.widget.ImageView")
        );
        menuButton.click();

        Thread.sleep(500);

        WebElement logoutButton = driver.findElement(
                AppiumBy.xpath("//android.widget.TextView[@text='DRAWING']")
        );
        logoutButton.click();
    }

    @When("realizo o desenho em tela")
    public void faz_o_desenho() throws Exception {
        Thread.sleep(3000);
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence heart = new Sequence(finger, 1);

        // Começa no primeiro ponto
        heart.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 540, 1100));
        heart.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // Pontos do coração
        int[][] points = {
                {340, 900},
                {240, 1100},
                {340, 1300},
                {540, 1500},
                {740, 1300},
                {840, 1100},
                {740, 900},
                {540, 1100}
        };

        for (int[] p : points) {
            heart.addAction(finger.createPointerMove(
                    Duration.ofMillis(300),
                    PointerInput.Origin.viewport(),
                    p[0],
                    p[1]
            ));
        }
        heart.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(heart));
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print Desenho");
    }

    @Then("salvo o desenho realizado na galeria")
    public void salvo_desenho() throws Exception {
        Thread.sleep(2000);


        WebElement saveButton = driver.findElement(
                new AppiumBy.ByAndroidUIAutomator(
                        "new UiSelector().description(\"test-SAVE\")"
                )
        );
        saveButton.click();

        Thread.sleep(3000);

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        byte[] smallerScreenshot = resizeImage(screenshot, 0.5);
        scenarioGlobal.attach(smallerScreenshot, "image/png", "Print Confirmacao Desenho");
        WebElement el3 = driver.findElement(AppiumBy.id("android:id/button1"));
        el3.click();
    }

    //metodo para tratar tamanho da imagem
    public byte[] resizeImage(byte[] originalBytes, double scale) throws Exception {
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(originalBytes));

        int newWidth = (int) (originalImage.getWidth() * scale);
        int newHeight = (int) (originalImage.getHeight() * scale);

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "png", baos);
        return baos.toByteArray();
    }

}
