### 
# CRUD de Petshop com MySQL e Java 🐾

Este é um projeto de um sistema CRUD (Create, Read, Update, Delete) para gerenciamento de um petshop, desenvolvido em **Java** e integrado ao banco de dados **MySQL**. O sistema permite realizar operações de cadastro, atualização, exclusão e busca de donos e petModels, oferecendo uma base funcional para entender conceitos de desenvolvimento back-end.

## Funcionalidades

- **Gerenciamento de Donos**: 
  - Cadastro de novos donos.
  - Atualização de informações existentes.
  - Exclusão de registros.
  - Busca por donos no banco de dados.

- **Gerenciamento de Pets**: 
  - Cadastro de novos petModels, incluindo vínculo com um dono.
  - Atualização de informações dos petModels.
  - Exclusão de registros.
  - Busca por petModels por nome.

- **Menu Interativo**: 
  - Sistema baseado em menus para navegação intuitiva entre as operações disponíveis.

## Tecnologias Utilizadas

- **Linguagem de Programação**: Java
- **Banco de Dados**: MySQL
- **Biblioteca de Conexão**: JDBC (Java Database Connectivity)

## Como Configurar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/BrisaTielly/petshop-registration-system.git
   ```

2. **Configure o banco de dados**:
   - Crie um banco de dados no MySQL.
   - Execute o script SQL fornecido no repositório para criar as tabelas necessárias.

3. **Ajuste as configurações de conexão**:
   - Atualize as credenciais de acesso ao banco de dados no arquivo de configuração ou classe `ConnectionFactory`.

4. **Compile e execute o projeto**:
   - Utilize uma IDE como IntelliJ IDEA ou Eclipse para compilar e rodar o código.
   - Alternativamente, compile no terminal usando `javac` e execute com `java`.

## Estrutura do Projeto

- **Modelos**: Representação das entidades do sistema, como `Owner` (dono) e `Pet` (animal).
- **Repositórios**: Responsáveis pela interação com o banco de dados, como `OwnerRepository` e `PetRepository`.
- **Serviços**: Contém a lógica de negócio e validação, como `OwnerService` e `PetService`.
- **Main**: Ponto de entrada do sistema, contendo os menus e a lógica de navegação.

## Próximas Melhorias

- Implementação de uma interface gráfica para maior usabilidade.
- Adição de testes unitários para validação do sistema.
- Integração com APIs externas para funcionalidades adicionais, como buscas de raças.

---

Com este projeto, pude explorar conceitos importantes de desenvolvimento back-end, como:
- Manipulação de dados em um banco relacional.
- Estruturação de um sistema modular em Java.
- Validação de entrada e tratamento de erros.

Sinta-se à vontade para contribuir ou compartilhar feedbacks! 😊
