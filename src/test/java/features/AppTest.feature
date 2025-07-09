Feature: Teste app SwagLabs
  Scenario: CT001 - Login com credenciais validas
    Given que estou com o app aberto
    When informo credenciais validas
    Then o aplicativo deve direcionar a tela inicial

  Scenario: CT002 - Login com usuario Bloqueado
    Given que estou na tela de login
    When informo credenciais invalidas
    Then o app mostra a mensagem usuario bloqueado

  Scenario: CT003 - Login com usuario nao cadastrado
    Given que estou com o app aberto
    When informo credenciais aleatorias
    Then o app mostra a mensagem de usuario nao cadastrado

  Scenario: CT004 - Valido filtro por nome crescente
    Given que estou logado no app
    When seleciono o filtro de ordenacao por nome, do A ao Z
    Then o filtro de ordenacao por nome crescente deve ser aplicado

  Scenario: CT005 - Valido filtro por nome decrescente
    Given que estou na tela de produtos
    When seleciono o filtro de ordenacao por nome, do Z ao A
    Then o filtro por nome decrescente deve ser aplicado

  Scenario: CT006 - Valido filtro por preco crescente
    Given que estou na tela de produtos
    When seleciono o filtro de preco crescente
    Then o filtro por preco crescente deve ser aplicado

  Scenario: CT007 - Valido filtro por preco decrescente
    Given que estou na tela de produtos
    When seleciono o filtro de preco decrescente
    Then o filtro por preco decrescente deve ser aplicado

  Scenario: CT008 - Adicionando produtos ao carrinho
    Given que estou na tela de produtos
    When adiciono o produtos ao carrinho
    Then os produtos devem estar no carrinho

  Scenario: CT009 - Adicionando produtos ao carrinho (Drag and Drop)
    Given que estou no carrinho
    When adiciono um produto ao carrinho com drag and drop
    Then os produtos devem estar no carrinho

  Scenario: CT010 - Finalizar uma compra e fazer o checkout
    Given que desejo realizar uma compra
    When informo os dados do comprador
    And confirmo as informacoes e concluo o checkout
    Then visualizo a confirmacao da compra

  Scenario: CT011 - Validar funcionalidade WebView
    Given que desejo validar a funcionalidade WebView
    When abro o WebView e informo um site valido
    Then visualizo o site informado

  Scenario: CT012 - Validar funcionalidade de desenho
    Given que desejo validar a funcionalidade desenho
    When realizo o desenho em tela
    Then salvo o desenho realizado na galeria
