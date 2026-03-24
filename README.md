# 🚀 ProAgende - Sistema de Agendamento de Serviços

Sistema desktop completo para gerenciamento de agendamentos, desenvolvido em Java com integração a banco de dados MySQL.

---

### 📌 Status do Projeto: ✅ **Concluído**

---

### 📖 Sobre o Projeto
Sistema desktop para gerenciamento de agendamentos de serviços, desenvolvido como **Projeto Integrador** do Curso Técnico em Desenvolvimento de Sistemas no SENAC.

---

### 🛠️ Tecnologias Utilizadas

- **Java** (JDK 8 ou superior)
- **Java Swing** (Interface gráfica)
- **MySQL** (Banco de dados relacional)
- **JDBC** (Conexão com o banco)
- **NetBeans IDE**
- **Git + GitHub** (Versionamento de código)

---

### 🧑🏻‍💻 Equipe de Desenvolvimento

- **Igor** – Desenvolvedor Java

---

### 🎯 Objetivo do Software

O **ProAgende** é um sistema desenvolvido com o objetivo de facilitar o gerenciamento de agendamentos entre clientes e profissionais autônomos, como barbeiros, manicures, personal trainers, entre outros.  

A aplicação centraliza informações importantes e automatiza o processo de marcação de horários, reduzindo erros e melhorando a organização dos atendimentos.

---

### ⚙️ Funcionalidades Principais

### 🔹 CRUD Completo
- Cadastro, visualização, edição e exclusão de **Clientes**
- Cadastro, visualização, edição e exclusão de **Serviços** (com profissional, preço e duração)
- Cadastro e gerenciamento de **Agendamentos**

### 🔹 Funcionalidades Especiais
- Combos dinâmicos que atualizam automaticamente após novos cadastros
- Tela de **Agendamentos Cadastrados** com filtro em cascata:  
  **Serviço → Cliente → Data/Hora**
- Validações de campos obrigatórios e formatos de dados
- Interface moderna com tema escuro

---

### ▶️ Como Executar o Projeto

#### Pré-requisitos
- JDK 8 ou superior instalado
- MySQL Server instalado e rodando
- MySQL Workbench
- NetBeans IDE

#### Passo a passo

**1. Clone o repositório**
- **Git Bash / Terminal:** `git clone https://github.com/seu-usuario/proagende.git`
- **GitHub Desktop:** Clique em **Code → Open with GitHub Desktop**
- **Download direto:** Clique em **Code → Download ZIP** e extraia o arquivo

**2. Configure o banco de dados**
- Abra o **MySQL Workbench** e conecte no seu servidor local
- Vá em **File → Open SQL Script**
- Selecione o arquivo `banco.sql` (disponível na pasta `/sql`)
- Execute o script clicando no botão ⚡ na barra de ferramentas ou pelo atalho **Ctrl + Shift + Enter**

**3. Configure a conexão**
- Abra o arquivo `Conexao.java`
- Insira seu usuário e senha do MySQL

**4. Execute o projeto**
- Importe o projeto no **NetBeans IDE**
- Execute a classe principal `TelaPrincipal.java`

---

### 🗄️ Banco de Dados

O sistema gerencia as seguintes tabelas:
- `cliente`
- `profissional`
- `servico`
- `agendamento`

Utiliza **chaves estrangeiras (Foreign Keys)** para garantir a integridade dos dados.

---

### 🎓 Informações Acadêmicas

**Projeto Integrador**  
Curso Técnico em Desenvolvimento de Sistemas – SENAC

---

## 👨‍💻 Autor

Desenvolvido por **Igor Pereira Lima** – 2026

---

