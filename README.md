# 📒 Agenda de contatos — Horizon Earth

Módulo desktop em JavaFX para cadastrar e organizar contatos do projeto Horizon Earth.

## Funcionalidades atuais

- Adicionar contatos com nome, telefone e e-mail opcional.
- Buscar contatos pelo nome.
- Selecionar e editar um contato.
- Remover contatos com confirmação.
- Limpar o formulário e visualizar o total de contatos.
- Exibir a imagem da interface no canto superior direito.

Nome e telefone são obrigatórios. Os contatos ficam em memória durante a execução; ao fechar o programa, os dados são perdidos. A integração com o login e o banco de dados está prevista.

## Tecnologias

Java 17, JavaFX 17.0.12, Maven, FXML, CSS e JUnit 5. O FXML pode ser editado no Scene Builder.

## Como executar

Instale um JDK 17 e Maven. Na pasta do projeto:

```bash
git clone https://github.com/Horizon-Earth/Agenda_Contatos.git
cd Agenda_Contatos
mvn clean javafx:run
```

No NetBeans, use **Arquivo > Abrir projeto**, selecione a pasta que contém `pom.xml` e execute o projeto. O arquivo `nbactions.xml` configura a ação de execução.

## Organização

| Caminho | Conteúdo |
| --- | --- |
| `src/main/java/com/mycompany/agendacontatos/` | Classes da aplicação |
| `src/test/java/` | Testes |
| `resources/com/mycompany/agendacontatos/` | FXML, CSS e imagem da interface |
| `database/` | Espaço reservado para modelos e scripts futuros |
| `docs/` | UML, arquitetura, interface, equipe e planejamento |
| `support/` | Guia da disciplina e materiais de apoio |

`Contato` representa os dados; `Agenda` gerencia a coleção; `PrimaryController` conecta a interface às operações; `App` inicia o JavaFX. A pasta `resources/` é configurada no Maven.

## Validação

Execute `mvn test`. O teste da interface precisa de ambiente gráfico; em Linux com Xvfb, execute `xvfb-run -a mvn verify`. O workflow do GitHub Actions utiliza esse comando.

## Documentação

- [Equipe](docs/equipe.md)
- [Roadmap](docs/roadmap.md)
- [Diagrama de classes](docs/uml/classes.md)
- [Tutorial do NetBeans](support/tutorials/netbeans.md)
- [Referências](docs/referencias.md)

## Equipe

Projeto acadêmico de Programação Orientada a Objetos — IFCE, Campus Maranguape, 2026.2.

| Integrante | Área | GitHub |
| --- | --- | --- |
| CaioStack | Full Stack | [CaioStack](https://github.com/CaioStack) |
| MuriStack | Banco de dados | [MuriStack](https://github.com/MuriStack) |
| BryanStack | Backend | [Bryan9895](https://github.com/Bryan9895) |
| MarioStack | Frontend | [ycarus-236](https://github.com/ycarus-236) |
| MiguelStack | Frontend e design | [MiguelStack](https://github.com/MiguelStack) |

## Contribuição

Crie uma branch para a alteração, faça commits claros e abra um pull request. Confira a execução e os testes disponíveis antes de integrar à `main`.

## Licença

Código e documentação próprios da Horizon Earth distribuídos sob a [licença MIT](LICENSE). Materiais externos, imagens, texturas e dados de APIs mantêm suas licenças e atribuições originais.
