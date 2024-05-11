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

## IMAGENS / (VIDEOS / GIFS )

## INSTALAÇÃO

## USO (EXEMPLOS DE USO)

Escolha o menor Caso de Uso.

## SUPORTE

Deixe seu contato para possíveis tira-dúvidas

## ROADMAP

Coloque aqui as ideias que deseja implementar futuramente

## CONTRIBUIÇÃO

- Se estiver aberto para contribuições indique os requisitos de aceitação
- Indique a documentação
- Fale as Variáveis de Ambiente que devem definir
- Comandos para executar testes

## AUTORES E AGRADECIMENTOS
Erick Oliveira da Silva

## LICENÇA

## STATUS DO PROJETO
Projeto está em processo de **Desenvolvimento** e estou desenvolvendo paralelamente o Backend e o Frontend.
