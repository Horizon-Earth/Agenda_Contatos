package br.edu.principal;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	
	public static void main(String[] args) {

        List<String> Contato = new ArrayList<>();
        int opcao;
        boolean continuar = true;
        
        Scanner sc = new Scanner(System.in);
        
        Uteis.mostraInicializacao(); 

        while (continuar) {
        	Uteis.mostraMenu();
            opcao = Uteis.selecionaOpcao(sc);

            switch (opcao) {
                case 1-> Agenda.adicionar(sc, Contato);           	
                case 2-> Agenda.listar(Contato);
                case 3-> Agenda.pesquisar(sc, Contato);
                case 4-> Agenda.atualizar(sc, Contato);           	
                case 5-> Agenda.excluir(sc, Contato);          	
                case 6-> continuar = Uteis.sair();
                case 7-> Uteis.sobre();
                default -> System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }
}
