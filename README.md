# OMDB Application (Spring Boot)

Aplicação backend desenvolvida em Java com Spring Boot para consulta de filmes e séries
utilizando a API pública do OMDB, com foco em modelagem de domínio e arquitetura de software.

O projeto teve origem em exercícios de curso, mas evoluiu para uma arquitetura própria,
com decisões explícitas sobre domínio, camadas e responsabilidades.

Atualmente, o projeto não segue mais o material do curso que o originou.

---

## Visão Geral da Arquitetura

A aplicação segue um modelo em camadas, priorizando:

- Separação clara de responsabilidades
- Isolamento de integrações externas
- Domínio independente de frameworks e APIs externas
- Evolução incremental, evitando antecipar complexidade

As decisões arquiteturais e seus trade-offs estão documentados
em arquivos próprios na pasta `docs/`.

---

## Camadas do Sistema

### Interface (temporária)

Camada de entrada baseada em menu de console (CLI).

- Existe apenas para validação local dos casos de uso
- Não contém lógica de negócio ou regras de domínio
- Será substituída futuramente por controllers REST ou outra interface

---

### Services de Aplicação

Responsáveis por:

- Orquestrar casos de uso
- Coordenar chamadas à integração externa (OMDB)
- Decidir quando entidades de domínio devem ser criadas, carregadas ou agregadas

Não realizam:

- Parsing de dados externos
- Regras internas de domínio
- Persistência direta

---

### Integração Externa (OMDB)

A comunicação com a API do OMDB é centralizada em um client dedicado.

Características:

- Toda chamada HTTP é isolada nesta camada
- DTOs externos existem apenas aqui
- Dados inconsistentes da API são tratados na borda do sistema
- Nenhuma outra camada conhece detalhes da OMDB

---

### Domínio

O domínio representa os conceitos centrais do sistema.

Entidades principais:

- Serie (aggregate root)
- Temporada
- Episodio
- Filme

Decisões:

- O domínio não depende de DTOs externos
- O domínio não depende de JPA ou detalhes de persistência
- Entidades garantem suas próprias invariantes
- A Serie governa suas Temporadas, que por sua vez governam Episodios

O domínio é tratado como a parte mais estável do sistema.

---

### Persistência

A camada de persistência ainda não está implementada.

Diretrizes definidas:

- Uso de JPA restrito à camada de persistência
- Mapeamento explícito de entidades
- Conversão domínio ↔ entidade via mappers dedicados
- Nenhuma regra de domínio deve residir em entidades JPA

A implementação desta camada será feita após a estabilização completa do modelo de domínio.

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

A aplicação depende de variáveis de ambiente para acessar a API do OMDB.

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

API_KEY=sua_chave_da_omdb
ENDERECO=https://www.omdbapi.com/?t=

Onde:

- API_KEY: chave de acesso fornecida pela OMDB
- ENDERECO: endpoint base para consultas por título

---

## Execução
A aplicação pode ser executada a partir da classe principal:

com.sanal.omdb.OmdbApplication

No estado atual, a aplicação inicia um menu simples em modo CLI
utilizado apenas para validar os fluxos de aplicação.

---

## Estado Atual do Projeto

Implementado:

- Integração com a API do OMDB
- Modelagem explícita do domínio
- Services de aplicação para orquestração de casos de uso
- Isolamento claro entre domínio, integração e interface

Planejado:

- Implementação da camada de persistência com JPA
- Exposição de endpoints REST
- Criação de read models para análises
- Tratamento explícito de erros de domínio

---

## Observações Finais

- O menu em modo texto é temporário
- O domínio não depende da interface atual
- O projeto prioriza clareza arquitetural sobre completude
- Este README e os documentos em `docs/` devem evoluir junto com o sistema
