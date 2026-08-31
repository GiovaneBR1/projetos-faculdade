

Projeto Tela de Login (JavaFX)

Este é um projeto de aplicação desktop em JavaFX que implementa um sistema completo de autenticação de usuários, incluindo cadastro, login, persistência de sessão, segurança de senha e um painel de administrador.

**- Funcionalidades Principais**

* **Cadastro de Usuários:** Permite que novos usuários se registrem fornecendo Nome, Email, Senha e Data de Nascimento.
* **Autenticação de Login:** Valida usuários com base no email e senha.
* **Segurança de Senha:** As senhas são armazenadas de forma segura no banco de dados usando *hash* **BCrypt** (incluindo um "salt" do sistema).
* **Controle de Tentativas:** Usuários são bloqueados após 5 tentativas de login com falha (`TENTATIVAS_INICIAIS`).
* **Painel de Administrador:** Existe um usuário "Master" (admin) que, ao logar, visualiza uma tabela de todos os usuários do sistema.
* **Gerenciamento de Usuários (Admin):** O usuário Master pode selecionar um usuário bloqueado na tabela e **resetar suas tentativas de login**.
* **Persistência de Sessão:** O sistema salva a sessão do usuário localmente, permitindo "lembrar" do usuário e pular a tela de login na próxima vez que abrir o app.


- **Como Configurar e Executar**

Para rodar este projeto na sua máquina, siga estes 4 passos:

### 1. Banco de Dados (MySQL)

Você precisa ter um servidor MySQL rodando localmente.

1.  Crie um novo banco de dados (schema) chamado `sistema_login`.
2.  Execute os scripts SQL encontrados no arquivo `src/main/resources/fxml/AQUI PARA O BANCO` para criar as tabelas `usuarios` e `acesso`.
3.  Verifique as credenciais no arquivo `src/main/java/config/DBConfig.java`. Por padrão, o projeto tentará conectar como usuário `root` e senha em branco (`""`). Ajuste este arquivo se suas credenciais do MySQL forem diferentes.

### 2. Configuração do Eclipse (Importar Projeto)

1.  No Eclipse, vá em `File` -> `Import...`.
2.  Escolha `Maven` -> `Existing Maven Projects`.
3.  Selecione a pasta raiz do projeto. O Eclipse deve reconhecer o `pom.xml` e importar o projeto, baixando as dependências (JavaFX, MySQL, BCrypt) automaticamente.

### 3. Configuração do JavaFX (Argumentos da VM)

Aplicações JavaFX modernas exigem argumentos específicos da VM para rodar.

1.  Encontre o **caminho** para o seu SDK do JavaFX no seu computador (a pasta `lib`).
2.  No Eclipse, clique com o botão direito no projeto -> `Run As` -> `Run Configurations...`.
3.  Encontre a configuração de `MainApp` (ou crie uma nova para a classe `app.MainApp`).
4.  Vá para a aba `(x)= Arguments`.
5.  No campo `VM arguments`, cole a seguinte linha:

    `--module-path "C:\CAMINHO\PARA\SEU\JAVAFX\lib" --add-modules javafx.controls,javafx.fxml`

    *(**Importante:** Troque `C:\CAMINHO\PARA\SEU\JAVAFX\lib` pelo caminho real no seu PC).*

### 4. Executar o Projeto

Após configurar o banco e os argumentos da VM, clique com o botão direito na classe `src/main/java/app/MainApp.java` e selecione `Run As` -> `Java Application`.

---

## 📖 Guia de Uso

### O Usuário Administrador (Master)

Na primeira vez que você rodar a aplicação, o sistema criará automaticamente um usuário "Master" (Administrador).

* **Email:** `MASTER`
* **Senha:** `ADSIS`

### Funcionalidades

1.  **Tela de Login:**
    * Insira `MASTER` e `ADSIS` para entrar como administrador.
    * Clique em "Cadastrar" para ir para a tela de registro.

2.  **Tela de Cadastro:**
    * Preencha os campos para criar uma conta de usuário comum.
    * A senha deve ter no mínimo 6 caracteres.

3.  **Tela Principal (Usuário Comum):**
    * Se você logar como um usuário comum (não-master), verá apenas uma tela de boas-vindas com seu nome.

4.  **Tela Principal (Administrador):**
    * Se você logar como `MASTER`, verá uma tabela com todos os usuários cadastrados (ID, Nome, Email, Tentativas restantes, Ativo).
    * Para desbloquear um usuário que errou a senha 5 vezes, clique no usuário na tabela e depois no botão **"Resetar tentativas (Master)"**.

5.  **Logout:**
    * Clique no botão "Logout" no canto superior direito para encerrar sua sessão e voltar à tela de login.

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **JavaFX 21** (para a interface gráfica)
* **Maven** (para gerenciamento de dependências)
* **MySQL** (para o banco de dados)
* **BCrypt** (para hashing de senhas)

