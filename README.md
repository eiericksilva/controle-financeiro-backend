# SOFTWARE DE CONTROLE FINANCEIRO

Sistema para controle financeiro voltado, primeiramente,
para a Gestão Financeira familiar. Pontos importantes:

- **ALGUMAS FUNCIONALIDADES**:
  - Contas a pagar
  - Contas a receber
  - Transaferências entre Contas
  - Registrar as Transações
  - Categorizar as Transações com:
    - Categoria
    - Subcategoria
    - Tags
  - Gestão de Usuário
    - Criar um novo Usuário
    - Recuperação de Senha do Usuário
    - Autenticação e Autorização
  - Entre outras funcionalidades...

## Padrão de Desenvolvimento
- Padrão de Projetos em **Camadas**
  - **Service**: Contem toda as regras de negócio
  - **Repository**: Interface responsável por acessar o Banco de Dados
  - **Controller**: Última camada para Controlar o fluxo entre Request e Response e expor as funcionalidades / Serviços da API para o Client
  - **Entity**: Representação o Domínio do Negócio como Tabelas do Banco de Dados
  - **DTO**: Uma camada extra para evitar expor o Domínio do negócio;

## TECNOLOGIAS & FERRAMENTAS UTILIZADAS
- **Intellij IDEA** (IDE para desenvolver o Backend Java);
- **Maven** (Ferramenta de Build utilizada);
- **MySQL Workbench** (Client para o SGBD do MySQL);
- **Intellij IDEA** (IDE para desenvolver o Backend Java);
- **JUnit 5** (Framework para desenvolvimento de Testes Unitários);

- **Java**:
  - JPA (Java Persistence API): Uma especificação para trabalhar com Mapeamento Entidade Relacionamento
  - Hibernate (Framework ORM): Utilizado para aplicação do JPA
- Banco de Dados
  - H2 (Para Ambiente de Teste)
  - MySQL (Para ambiente de Desenvolvimento)

## SELOS

- ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
- ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)

## IMAGENS / VIDEOS / GIFS 
![ConceitualControleFinanceiro.png](src/main/java/com/eiericksilva/controle_financeiro/assets/ConceitualControleFinanceiro.png)
Esta é apenas uma representação visual  **(modelo conceitual)** do software. 
Desculpe por eventuais erros técnicos que possa haver Diagrama. 
Este modelo está sendo utilizado como base para o desenvolvimento, mas ao longo do processo de implementação ajustes e adaptações podem ter 
sido feitos para atender às necessidades específicas do projeto e não condizer com exatidão o Diagrama.
## INSTALAÇÃO

## USO (EXEMPLOS DE USO)

Escolha o menor Caso de Uso.

## SUPORTE

Deixe seu contato para possíveis tira-dúvidas

## IMPLEMENTAÇÕES FUTURAS

1. [ ] Autenticação & Autorização
2. [ ] Paginação
3. [ ] Testes Unitários

## CONTRIBUIÇÃO

- **Spring Doc Open API(Swagger)** - [IR PARA A DOCUMENTAÇÃO](http://localhost:8080/api/swagger-ui/index.html);

## AUTORES E AGRADECIMENTOS
[![Autor](https://img.shields.io/badge/Autor-ERICK_SILVA-orange)](mailto:ericksilvaredes@gmail.com)

## STATUS DO PROJETO
[![Status do Projeto](https://img.shields.io/badge/Status-Em_Desenvolvimento-green)](https://github.com/eiericksilva/controle-financeiro-backend)
q
