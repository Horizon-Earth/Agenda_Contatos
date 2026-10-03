# Agenda de Contatos | Horizon Earth

Aplicação didática de Programação Orientada a Objetos para cadastrar, consultar, editar e remover contatos. Interface JavaFX editável no Scene Builder e código organizado conforme o Guia de Organização do GitHub V2.0 da disciplina.

## Objetivos

- Praticar classes, objetos, encapsulamento e coleções.
- Separar os dados e operações da agenda das ações da interface.
- Registrar evolução, documentação e colaboração no GitHub.

## Funcionalidades

- Cadastro com nome e telefone obrigatórios e e-mail opcional.
- Listagem e busca por nome, ignorando maiúsculas/minúsculas.
- Edição do contato selecionado e exclusão com confirmação.
- Limpeza dos campos e contador dos contatos.

**Esta versão guarda os dados em memória. Ao fechar, os contatos são perdidos.**

## Tecnologias

Java 17+, JavaFX 17, FXML, CSS, Maven, JUnit 5, NetBeans e Scene Builder.

> O guia da disciplina exige Swing e MySQL na versão final. Esta implementação utiliza JavaFX por escolha no desenvolvimento atual. A persistência MySQL ainda não foi implementada; as pastas de banco estão reservadas para trabalho futuro. Confirmar com o professor a aceitação de JavaFX antes da entrega acadêmica.

## Executar

1. Clone o repositório:

```bash
git clone https://github.com/Horizon-Earth/Agenda_Contatos.git
cd Agenda_Contatos
```

2. No NetBeans, use **File → Open Project** e selecione a pasta com `pom.xml`.
3. Selecione um JDK 17 ou superior, aguarde o download das dependências e pressione **F6**.

Com JDK e Maven instalados, também é possível executar pelo terminal:

```bash
mvn clean javafx:run
```

A primeira execução requer internet para baixar as dependências. `nbactions.xml` configura o botão Executar do NetBeans.

## Editar a interface

Abra `resources/com/mycompany/agendacontatos/primary.fxml` no Scene Builder. Preserve `fx:id`, `On Action` e `fx:controller` ao alterar o layout. O aplicativo aplica `estilo.css` ao abrir a janela.

O Maven inclui a pasta `resources/` no classpath explicitamente, mantendo os caminhos relativos usados pelo `App`. Não há cópias duplicadas dos arquivos FXML/CSS.

## Organização

| Caminho | Conteúdo |
| --- | --- |
| `src/main/java/` | Classes Java da aplicação |
| `src/test/java/` | Testes de agenda e carregamento da interface |
| `resources/` | FXML, CSS, ícones e imagens da aplicação |
| `database/DER`, `database/DL`, `database/scripts` | Materiais da futura persistência MySQL |
| `docs/uml/` | Diagrama de classes |
| `docs/ui-ux/` | Wireframes, mockups e fluxo da interface |
| `docs/diagrams/` | Arquitetura da aplicação |
| `docs/presentations/` | Apresentações da equipe |
| `support/` | Guia da disciplina, tutoriais e referências |
| `.github/` | CI e modelo de Pull Request |

## Classes

- `Contato`: nome, telefone e e-mail, construtor, getters e setters.
- `Agenda`: gerencia a coleção de objetos `Contato`.
- `PrimaryController`: recebe ações do FXML, chama a agenda e atualiza a tabela.
- `App`: inicia o JavaFX e abre a janela.

## Testes

```bash
mvn test
```

Os testes da interface exigem um display gráfico. No Linux com Xvfb instalado:

```bash
xvfb-run -a mvn verify
```

O GitHub Actions executa os testes da agenda e o carregamento real do FXML em um display virtual. Sem a variável `DISPLAY`, o teste gráfico é ignorado.

## Equipe

Organização: [Horizon Earth](https://github.com/Horizon-Earth).

A equipe deve cadastrar os nomes, perfis e responsabilidades em [docs/equipe.md](docs/equipe.md). Esses dados não foram presumidos.

## Colaboração e próximos passos

Desenvolver em branches `feature/*` ou `fix/*`, testar e abrir Pull Request para `main`, conforme o guia. Este repositório corresponde somente à agenda; login e projeto livre pertencem a repositórios próprios.

Pendências: persistência MySQL, modelagem do banco, capturas reais da interface e confirmação da tecnologia para entrega. Consulte [docs/roadmap.md](docs/roadmap.md).

Pastas reservadas são mantidas com `.gitkeep`. Referências e autoria dos materiais de apoio estão em [docs/referencias.md](docs/referencias.md).

## Licença

MIT — consulte [LICENSE](LICENSE).
