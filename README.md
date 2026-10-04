# Agenda de Contatos | Horizon Earth

Aplicação didática de Programação Orientada a Objetos para cadastrar, consultar, editar e remover contatos. A aplicação utiliza JavaFX, Maven e persistência de dados em MySQL.

## Funcionalidades

- Cadastro de contatos com nome e telefone obrigatórios e e-mail opcional.
- Listagem dos contatos cadastrados.
- Busca por nome, ignorando maiúsculas e minúsculas.
- Edição do contato selecionado.
- Remoção do contato com confirmação.
- Persistência dos contatos em banco de dados MySQL.
- Carregamento dos contatos salvos ao iniciar a aplicação.
- Validação dos campos obrigatórios.
- Testes automatizados da agenda e da interface.

## Tecnologias

- Java 17
- JavaFX 17.0.12
- FXML e CSS
- Maven
- MySQL
- MySQL Connector/J 9.4.0
- JUnit 5.10.2
- NetBeans
- Scene Builder

## Banco de dados

O projeto utiliza o banco `agenda_contatos` e a tabela `contatos`.

O script de criação está em:

```
database/scripts/schema.sql
```

Para criar o banco e a tabela, com o MySQL instalado e em execução, execute na raiz do projeto:

```bash
mysql -u root -p < database/scripts/schema.sql
```

O script cria:

```text
agenda_contatos
└── contatos
    ├── id
    ├── nome
    ├── telefone
    └── email
```

## Configuração da senha do MySQL

A senha do MySQL **não deve ser colocada no código nem commitada no GitHub**.

A classe `Conexao` lê a senha pela variável de ambiente `DB_SENHA`:

```java
private static final String SENHA = System.getenv("DB_SENHA");
```

Antes de executar a aplicação, configure a variável no mesmo terminal que será usado para iniciar o Maven:

```bash
export DB_SENHA='SUA_SENHA_DO_MYSQL'
```

É possível conferir se a variável está configurada sem exibir a senha:

```bash
if [ -n "$DB_SENHA" ]; then echo "DB_SENHA configurada"; else echo "DB_SENHA vazia"; fi
```

Deve aparecer:

```text
DB_SENHA configurada
```

> A variável de ambiente vale apenas para o terminal atual e para os processos iniciados a partir dele. Se um novo terminal for aberto, configure `DB_SENHA` novamente.

## Executar

### Pelo NetBeans

1. Abra a pasta do projeto no NetBeans usando **File → Open Project**.
2. Configure o projeto para utilizar JDK 17.
3. Garanta que o MySQL esteja em execução.
4. Crie o banco usando o script indicado acima.
5. Configure `DB_SENHA` no ambiente do processo que iniciar o NetBeans/aplicação.
6. Execute o projeto.

### Pelo terminal

Na raiz do projeto:

```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"
export DB_SENHA='SUA_SENHA_DO_MYSQL'
mvn clean javafx:run
```

A primeira execução pode demorar porque o Maven precisa baixar as dependências.

A aplicação utiliza:

```text
jdbc:mysql://localhost:3306/agenda_contatos
```

com o usuário `root`.

## Fluxo da persistência

As operações da interface passam pela seguinte estrutura:

```text
PrimaryController
      ↓
    Agenda
      ↓
  ContatoDAO
      ↓
   Conexao
      ↓
     MySQL
```

O `ContatoDAO` é responsável pelas operações de persistência:

- `salvar()` → INSERT
- `listar()` → SELECT
- `editar()` → UPDATE
- `remover()` → DELETE

A classe `Contato` possui também o `id` do registro, utilizado para identificar o contato no banco.

## Testes

Para executar os testes:

```bash
mvn test
```

Para executar a verificação completa, incluindo o teste da interface:

```bash
xvfb-run -a mvn verify
```

Os testes automatizados utilizam MySQL no GitHub Actions. O workflow cria um serviço MySQL, inicializa o schema e executa os testes com Java 17.

O workflow está em:

```
.github/workflows/java.yml
```

## Organização

| Caminho | Conteúdo |
| --- | --- |
| `src/main/java/` | Classes Java da aplicação |
| `src/test/java/` | Testes da aplicação |
| `resources/` | FXML, CSS, ícones e imagens |
| `database/DER` | Modelo entidade-relacionamento |
| `database/DL` | Diagramas relacionados ao banco |
| `database/scripts` | Scripts SQL |
| `docs/uml/` | Diagrama de classes |
| `docs/ui-ux/` | Wireframes, mockups e fluxo da interface |
| `docs/diagrams/` | Arquitetura da aplicação |
| `.github/` | GitHub Actions e configurações do repositório |

## Classes principais

- `Contato`: representa os dados de um contato e seu identificador no banco.
- `ContatoDAO`: realiza as operações de persistência no MySQL.
- `Conexao`: centraliza a criação das conexões JDBC.
- `Agenda`: coordena as operações da agenda.
- `PrimaryController`: conecta a interface JavaFX às operações da agenda.
- `App`: inicia a aplicação JavaFX.

## Segurança

Não versione senhas, chaves ou outras credenciais.

A senha do MySQL utilizada localmente deve permanecer em uma variável de ambiente, como `DB_SENHA`. O GitHub Actions utiliza uma senha própria apenas para o banco temporário dos testes automatizados.

## Colaboração

Desenvolva em branches `feature/*` ou `fix/*`, teste as alterações e abra um Pull Request para `main`, conforme as regras da disciplina.

Organização: [Horizon Earth](https://github.com/Horizon-Earth).

## Licença

MIT — consulte [LICENSE](LICENSE).
