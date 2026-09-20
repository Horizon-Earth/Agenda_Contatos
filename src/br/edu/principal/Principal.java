package br.edu.principal;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Agenda agenda = new Agenda();

        boolean continuar = true;

        Uteis.mostraInicializacao();

        new TelaAgenda();

        while (continuar) {

            Uteis.mostraMenu();

            int opcao = Uteis.selecionaOpcao(sc);


            switch (opcao) {

                case 1 -> {

                    System.out.println(
                            "=== ADICIONAR CONTATO ==="
                    );

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();


                    System.out.print("Celular: ");
                    String celular = sc.nextLine();


                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    agenda.adicionar(
                            nome,
                            celular,
                            email
                    );


                    System.out.println(
                            "Contato adicionado com sucesso!"
                    );
                }

                case 2 -> {

                    System.out.println(
                            "=== LISTAR CONTATOS ==="
                    );

                    agenda.listar();
                }

                case 3 -> {

                    System.out.println(
                            "=== PESQUISAR CONTATO ==="
                    );


                    System.out.print(
                            "Digite o nome: "
                    );

                    String nomeBusca = sc.nextLine();

                    Contato contato =
                            agenda.pesquisar(nomeBusca);

                    if (contato == null) {

                        System.out.println(
                                "Contato não encontrado!"
                        );

                    } else {

                        System.out.println(
                                "Nome: "
                                        + contato.getNome()
                        );

                        System.out.println(
                                "Celular: "
                                        + contato.getCelular()
                        );

                        System.out.println(
                                "Email: "
                                        + contato.getEmail()
                        );
                    }
                }

                case 4 -> {

                    System.out.println(
                            "=== ALTERAR CONTATO ==="
                    );
                    System.out.print(
                            "Nome do contato: "
                    );
                    String nomeProcurado =
                            sc.nextLine();

                    Contato contato =
                            agenda.pesquisar(nomeProcurado);

                    if (contato == null) {

                        System.out.println(
                                "Contato não encontrado!"
                        );

                        break;
                    }

                    System.out.print(
                            "Novo nome: "
                    );

                    String novoNome =
                            sc.nextLine();


                    System.out.print(
                            "Novo celular: "
                    );

                    String novoCelular =
                            sc.nextLine();


                    System.out.print(
                            "Novo email: "
                    );

                    String novoEmail =
                            sc.nextLine();


                    // Agenda altera o objeto.
                    agenda.atualizar(
                            nomeProcurado,
                            novoNome,
                            novoCelular,
                            novoEmail
                    );


                    System.out.println(
                            "Contato atualizado!"
                    );
                }

                case 5 -> {

                    System.out.println(
                            "=== EXCLUIR CONTATO ==="
                    );


                    System.out.print("Digite o nome: ");
                    String nome = sc.nextLine();
                    boolean excluiu =
                            agenda.excluir(nome);

                    if (excluiu) {
                        System.out.println(
                                "Contato excluído!"
                        );
                    } else {
                        System.out.println(
                                "Contato não encontrado!"
                        );
                    }
                }

                case 6 -> {

                    continuar = false;

                    System.out.println(
                            "Saindo da agenda..."
                    );
                }

                case 7 -> Uteis.sobre();


                default -> System.out.println(
                        "Opção inválida!"
                );
            }
        }

        sc.close();
    }
}