package com.mycompany.agendacontatos;

import java.util.concurrent.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.*;

@EnabledIfEnvironmentVariable(named = "DISPLAY", matches = ".+")
class InterfaceTest {
    @Test void carregaFxmlECadastraContato() throws Exception {
        CountDownLatch iniciado = new CountDownLatch(1);
        Platform.startup(iniciado::countDown);
        try {
            assertTrue(iniciado.await(15, TimeUnit.SECONDS));
            FutureTask<Void> tarefa = new FutureTask<>(() -> {
                Parent tela = FXMLLoader.load(App.class.getResource("primary.fxml"));
                assertNotNull(App.class.getResource("estilo.css"));
                ((TextField) tela.lookup("#campoNome")).setText("Teste");
                ((TextField) tela.lookup("#campoTelefone")).setText("00123");
                ((Button) tela.lookup("#botaoAdicionar")).fire();
                TableView<?> tabela = (TableView<?>) tela.lookup("#tabelaContatos");
                assertEquals(1, tabela.getItems().size());
                ((TextField) tela.lookup("#campoBusca")).setText("inexistente");
                assertTrue(tabela.getItems().isEmpty());
                ((TextField) tela.lookup("#campoBusca")).clear();
                assertEquals(1, tabela.getItems().size());
                return null;
            });
            Platform.runLater(tarefa);
            tarefa.get(15, TimeUnit.SECONDS);
        } finally {
            Platform.exit();
        }
    }
}
