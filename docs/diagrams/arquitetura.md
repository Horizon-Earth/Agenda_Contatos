# Arquitetura

O App carrega primary.fxml e seu controller. O controller lê os campos e chama Agenda. Agenda mantém os objetos Contato em memória. A tabela recebe o resultado de listar/buscar; editar e remover usam a mesma instância de Contato selecionada.

resources/ é copiada para o classpath pelo Maven. Banco de dados ainda não participa desse fluxo.
