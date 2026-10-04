package com.mycompany.agendacontatos;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class PrimaryController {

    private final Agenda agenda = new Agenda();
    private Contato contatoEmEdicao;

    @FXML private TextField campoNome;
    @FXML private TextField campoTelefone;
    @FXML private TextField campoEmail;
    @FXML private TextField campoBusca;
    @FXML private Label mensagem;
    @FXML private Label totalContatos;
    @FXML private TableView<Contato> tabelaContatos;
    @FXML private TableColumn<Contato, String> colunaNome;
    @FXML private TableColumn<Contato, String> colunaTelefone;
    @FXML private TableColumn<Contato, String> colunaEmail;
    @FXML private Button botaoSalvar;
    @FXML private Button botaoRemover;

    @FXML
    private void initialize() {
        colunaNome.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getNome()));

        colunaTelefone.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getTelefone()));

        colunaEmail.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getEmail()));

        tabelaContatos.getSelectionModel().selectedItemProperty().addListener(
                (obs, antes, contato) -> {
                    contatoEmEdicao = contato;
                    botaoSalvar.setDisable(contato == null);
                    botaoRemover.setDisable(contato == null);

                    if (contato != null) {
                        campoNome.setText(contato.getNome());
                        campoTelefone.setText(contato.getTelefone());
                        campoEmail.setText(contato.getEmail());
                        mensagem.setText(
                                "Contato selecionado. Altere os campos e clique em Salvar edição.");
                    }
                });

        campoBusca.textProperty().addListener((obs, antes, agora) -> {
            atualizarTabela();
            limparCampos();
        });

        atualizarTabela();
        mensagem.setText("Preencha os campos para adicionar seu primeiro contato.");
    }

    @FXML
    private void adicionarContato() {
        if (!validarCampos()) return;

        Contato contato = new Contato(
                campoNome.getText().trim(),
                campoTelefone.getText().trim(),
                campoEmail.getText().trim());

        try {
            agenda.adicionarContato(contato);
            atualizarTabela();
            limparCampos();
            mensagem.setText("Contato adicionado.");
        } catch (SQLException e) {
            mensagem.setText("Erro ao salvar contato no banco.");
            e.printStackTrace();
        }
    }

    @FXML
    private void salvarEdicao() {
        if (contatoEmEdicao == null) {
            mensagem.setText("Selecione um contato na tabela.");
            return;
        }

        if (!validarCampos()) return;

        try {
            agenda.editarContato(
                    contatoEmEdicao,
                    campoNome.getText().trim(),
                    campoTelefone.getText().trim(),
                    campoEmail.getText().trim());

            atualizarTabela();
            limparCampos();
            mensagem.setText("Contato atualizado.");

        } catch (SQLException e) {
            mensagem.setText("Erro ao atualizar contato no banco.");
            e.printStackTrace();
        }
    }

    @FXML
    private void removerContato() {
        if (contatoEmEdicao == null) {
            mensagem.setText("Selecione um contato na tabela.");
            return;
        }

        Contato contato = contatoEmEdicao;

        Alert alerta = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Remover " + contato.getNome() + "?",
                ButtonType.YES,
                ButtonType.NO);

        alerta.setTitle("Remover contato");
        alerta.setHeaderText(null);
        alerta.initOwner(tabelaContatos.getScene().getWindow());

        if (alerta.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
            try {
                agenda.removerContato(contato);
                atualizarTabela();
                limparCampos();
                mensagem.setText("Contato removido.");

            } catch (SQLException e) {
                mensagem.setText("Erro ao remover contato do banco.");
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void limparCampos() {
        tabelaContatos.getSelectionModel().clearSelection();
        contatoEmEdicao = null;

        botaoSalvar.setDisable(true);
        botaoRemover.setDisable(true);

        campoNome.clear();
        campoTelefone.clear();
        campoEmail.clear();

        mensagem.setText("Pronto para um novo contato.");
    }

    private boolean validarCampos() {
        if (campoNome.getText().isBlank()) {
            mensagem.setText("Informe o nome.");
            campoNome.requestFocus();
            return false;
        }

        if (campoTelefone.getText().isBlank()) {
            mensagem.setText("Informe o telefone.");
            campoTelefone.requestFocus();
            return false;
        }

        return true;
    }

    private void atualizarTabela() {
        try {
            tabelaContatos.getItems().setAll(
                    agenda.buscarContatos(campoBusca.getText()));

            tabelaContatos.refresh();

            totalContatos.setText(
                    tabelaContatos.getItems().size()
                    + " exibidos / "
                    + agenda.listarContatos().size()
                    + " cadastrados");

        } catch (SQLException e) {
            mensagem.setText("Erro ao carregar contatos do banco.");
            e.printStackTrace();
        }
    }
}
