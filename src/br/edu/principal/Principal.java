package br.edu.principal;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) {

        List<String> contatos = new ArrayList<>();
        int opcao;
        boolean continuar = true;
        
        Scanner sc = new Scanner(System.in);
        
        Uteis.mostraInicializacao(); 

        while (continuar) {
        	Uteis.mostraMenu();
            opcao = Uteis.selecionaOpcao(sc);

            switch (opcao) {
                case 1-> Agenda.adicionar(sc, contatos);           	
                case 2-> Agenda.listar(contatos);
                case 3-> Agenda.pesquisar(sc, contatos);
                case 4-> Agenda.atualizar(sc, contatos);           	
                case 5-> Agenda.excluir(sc, contatos);          	
                case 6-> continuar = Uteis.sair();
                case 7-> Uteis.sobre();
                default -> System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }
}
