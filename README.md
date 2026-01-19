# OMDB Application (Spring Boot)

Aplicação backend desenvolvida em Java com Spring Boot para consulta e persistência de títulos (filmes e séries) utilizando a API pública do OMDB.

O projeto teve origem em exercícios de curso, mas evoluiu para uma arquitetura própria, com separação clara de responsabilidades, integração externa isolada, persistência explícita e documentação arquitetural mínima.

Atualmente, o projeto já não está mais diretamente acoplado ao material do curso que o originou.

---

## Visão Geral da Arquitetura

A aplicação segue um modelo em camadas, priorizando:

* Separação clara de responsabilidades
* Isolamento de integrações externas
* Domínio independente de frameworks e APIs externas
* Persistência explícita e previsível
* Evolução incremental, evitando antecipar complexidade

As decisões arquiteturais e seus trade-offs estão documentadas em arquivos próprios na pasta docs/.

---

## Camadas do Sistema

### Interface (temporária)

Camada de entrada baseada em menu de console (CLI).

* Existe apenas para validação local de fluxos
* Não contém lógica de negócio
* Será substituída futuramente por controllers REST

---

### Services de Aplicação

Responsáveis por:

* Orquestrar casos de uso
* Coordenar chamadas ao client de integração externa
* Decidir quando e como dados devem ser persistidos

Não realizam:

* Persistência direta
* Parsing de dados externos
* Lógica de análise complexa

---

### Integração Externa (OMDB)

A comunicação com a API do OMDB é centralizada em um client dedicado.

Características:

* Toda chamada HTTP é isolada nesta camada
* DTOs externos existem apenas aqui
* Dados inconsistentes ou instáveis são tratados na borda do sistema
* Nenhuma outra camada conhece detalhes da API externa

---

### Domínio

O domínio representa os conceitos centrais do sistema, como títulos, filmes e séries.

Decisões:

* O domínio não depende de DTOs externos
* O domínio não depende de JPA ou detalhes de persistência
* Conversões externas para domínio são feitas por factories dedicadas

O domínio é tratado como a parte mais estável do sistema.

---

### Persistência

A camada de persistência é explícita e desacoplada do restante da aplicação.

Características:

* Uso de JPA apenas nesta camada
* Conversão domínio → entidade via mappers dedicados
* Ordem de persistência controlada manualmente pelos services

---

#### Aggregate Root

* Série é tratada como aggregate root
* Episódios dependem de uma série existente
* Episódios não existem de forma independente no sistema

---

#### Persistência de Episódios

Regras adotadas:

* A série deve estar persistida antes de qualquer episódio
* Episódios são persistidos por temporada
* Cada temporada é tratada como uma unidade transacional
* Não existe transação global envolvendo todas as temporadas da série

Consequências assumidas:

* Falha em uma temporada não afeta temporadas já persistidas
* O sistema aceita estados parciais por série
* Idempotência não é garantida neste estágio

---

#### Relacionamentos e Cascade

Decisões atuais:

* Não é utilizado cascade JPA
* Relacionamentos são unidirecionais
* Persistência é totalmente controlada pelos services

Essas decisões evitam complexidade prematura e tornam o fluxo explícito.

---

## Configuração do Ambiente

A aplicação depende de variáveis de ambiente para acessar serviços externos.

Crie um arquivo .env na raiz do projeto com as seguintes variáveis:

API_KEY=sua_chave_da_omdb
ENDERECO=[https://www.omdbapi.com/?t=](https://www.omdbapi.com/?t=)

Descrição:

* API_KEY: chave de acesso fornecida pela API do OMDB
* ENDERECO: endpoint base utilizado para consultas por título

---

## Execução

A aplicação pode ser executada a partir da classe principal:

com.sanal.omdb.OmdbApplication

No estado atual, a aplicação inicia um menu em modo CLI que permite:

* Buscar filmes e séries
* Listar episódios de séries
* Executar análises simples sobre episódios

---

## Estado Atual do Projeto

Funcionalidades implementadas:

* Integração com a API do OMDB
* Persistência de filmes, séries e episódios
* Persistência transacional por temporada
* Testes de integração com banco PostgreSQL
* Documentação arquitetural mínima

Funcionalidades planejadas:

* Exposição de endpoints REST
* Tratamento explícito de erros de domínio
* Atualização incremental de dados persistidos
* Evolução do modelo de concorrência

---

## Observações Finais

* O menu em modo texto é temporário
* A lógica de negócio não depende da interface atual
* O projeto prioriza clareza arquitetural e decisões explícitas
* Este README e os arquivos em docs/ devem evoluir junto com o sistema
