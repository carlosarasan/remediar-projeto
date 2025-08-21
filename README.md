# Remediar - Projeto

## 📖 Descrição
Sistema de gerenciamento de medicamentos desenvolvido para a disciplina de **Desenvolvimento de Sistemas Corporativos**.
O projeto permite cadastrar pacientes, medicamentos, realizar prescrições médicas e gerar relatórios de forma organizada.

---

## ⚙️ Funcionalidades
- Cadastro de pacientes
- Cadastro de medicamentos
- Emissão de prescrição médica
- Relatórios de uso
- Busca de registros

---

## 🛠 Tecnologias Utilizadas
- Java
- JavaFX (interface gráfica)
- MySQL (banco de dados)
- Maven (gerenciador de dependências)
- Scene Builder (construção das telas)

---

## 🚀 Como Executar o Projeto

### 1. Clonar o repositório
Abra o terminal e execute os comandos:

git clone https://github.com/seu-usuario/remediar-projeto.git
cd remediar-projeto

---

### 2. Configurar o banco de dados
1. Abra o MySQL no seu computador.
2. Execute o script localizado na pasta ScriptSQL:

script-remediar.sql

Esse script cria o banco de dados `remediar` e todas as tabelas necessárias.

Exemplo no terminal MySQL:

SOURCE ScriptSQL/script-remediar.sql;

3. Use as credenciais padrão para o banco de dados:

Usuário: admin  
Senha: admin123

4. Verifique se essas informações estão corretas na configuração do projeto, geralmente no arquivo:

src/main/resources/application.properties

ou na classe de conexão Java, caso não exista esse arquivo.

---

### 3. Importar no IntelliJ IDEA
1. Abra o IntelliJ IDEA.
2. Vá em File > Open e selecione a pasta do projeto.
3. O IntelliJ deve reconhecer o projeto como Maven Project automaticamente.
   - Se não reconhecer, clique com botão direito no pom.xml e selecione Add as Maven Project.

---

### 4. Configurar JavaFX no IntelliJ
1. Certifique-se de que o JavaFX SDK está instalado.
2. Vá em File > Project Structure > Libraries e adicione o caminho do JavaFX.
3. Nas Run Configurations, adicione o parâmetro VM:

--module-path "caminho/do/javafx/lib" --add-modules javafx.controls,javafx.fxml

---

### 5. Executar o projeto
1. Localize a classe principal:

src/main/java/com/remediar/HelloApplication.java

2. Clique em Run para iniciar o sistema.

Se tudo estiver configurado corretamente, a aplicação abrirá a interface gráfica do sistema.

---

## 👨‍💻 Autores
- Carlos Daniel
- André Gustavo

## 👨‍🏫 Professor
- Paulo Veloso Santos

---

## ⚠️ Problemas Comuns
- Erro de conexão com o banco: verifique usuário (`admin`), senha (`admin123`) e porta no arquivo application.properties.
- Erro JavaFX: certifique-se de que o JavaFX SDK está instalado e configurado corretamente nas Run Configurations.
- Projeto Maven não reconhecido: clique com botão direito no pom.xml e selecione Add as Maven Project.
