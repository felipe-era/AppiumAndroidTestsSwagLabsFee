## ▶️ Gravação de tela dos testes em execução

https://github.com/user-attachments/assets/a08dbe96-086c-4dd6-84ae-629d20f72fdf

---

### ⚙️ Como buildar o projeto

Clone o repositório:

```bash
git clone https://github.com/felipe-era/AppiumAndroidTestsSwagLabsFee.git)
cd AppiumAndroidTestsSwagLabsFee
```

Build do projeto:

```bash
mvn clean install
mvn clean compile
e depois..
mvn test
```


## 💻 Tecnologias Utilizadas
✅ Appium \
✅ Cucumber (BDD) \
✅ Selenium WebDriver \
✅ Java \
✅ JUnit 5 \
✅ Appium Inspector 

# Testes Automatizados - App SwagLabs - 12 Casos de Testes

```gherkin
Scenario: CT001 - Login com credenciais válidas
  Given que estou com o app aberto
  When informo credenciais válidas
  Then o aplicativo deve direcionar à tela inicial

Scenario: CT002 - Login com usuário bloqueado
  Given que estou na tela de login
  When informo credenciais inválidas
  Then o app mostra a mensagem "usuário bloqueado"

Scenario: CT003 - Login com usuário não cadastrado
  Given que estou com o app aberto
  When informo credenciais aleatórias
  Then o app mostra a mensagem de "usuário não cadastrado"

Scenario: CT004 - Valido filtro por nome crescente
  Given que estou logado no app
  When seleciono o filtro de ordenação por nome, do A ao Z
  Then o filtro de ordenação por nome crescente deve ser aplicado

Scenario: CT005 - Valido filtro por nome decrescente
  Given que estou na tela de produtos
  When seleciono o filtro de ordenação por nome, do Z ao A
  Then o filtro por nome decrescente deve ser aplicado

Scenario: CT006 - Valido filtro por preço crescente
  Given que estou na tela de produtos
  When seleciono o filtro de preço crescente
  Then o filtro por preço crescente deve ser aplicado

Scenario: CT007 - Valido filtro por preço decrescente
  Given que estou na tela de produtos
  When seleciono o filtro de preço decrescente
  Then o filtro por preço decrescente deve ser aplicado

Scenario: CT008 - Adicionando produtos ao carrinho
  Given que estou na tela de produtos
  When adiciono produtos ao carrinho
  Then os produtos devem estar no carrinho

Scenario: CT009 - Adicionando produtos ao carrinho (Drag and Drop)
  Given que estou no carrinho
  When adiciono um produto ao carrinho usando drag and drop
  Then os produtos devem estar no carrinho

Scenario: CT010 - Finalizar uma compra e fazer o checkout
  Given que desejo realizar uma compra
  When informo os dados do comprador
  And confirmo as informações e concluo o checkout
  Then visualizo a confirmação da compra

Scenario: CT011 - Validar funcionalidade WebView
  Given que desejo validar a funcionalidade WebView
  When abro o WebView e informo um site válido
  Then visualizo o site informado

Scenario: CT012 - Validar funcionalidade de desenho
  Given que desejo validar a funcionalidade de desenho
  When realizo o desenho em tela
  Then salvo o desenho realizado na galeria

```

---

## ✅ Estrutura simplificada

```markdown
## Estrutura do Projeto
- `features/`
  - Arquivo `.feature` com todos os cenários de testes em Gherkin.
- `runner/`
  - Classe `TestRunner.java` que executa os testes.
- `stepdefinitions/`
  - Código Java que implementa as ações dos testes.
  - Inclui inicialização do driver Appium em `Hooks.java`.
```

![appiumjava](https://github.com/user-attachments/assets/3f6d2df2-3012-44c6-ae20-0b92431a3760)

