# Fluxo Arquitetural do Projeto OMDB

Este documento descreve os **principais fluxos arquiteturais** do projeto OMDB.

O objetivo é explicar **como as responsabilidades fluem entre camadas**, deixando explícito
quem decide, quem executa e quem apenas fornece dados — sem entrar em detalhes de
implementação, código ou frameworks específicos.

Os fluxos aqui descritos refletem o **estado atual do sistema**.

---

## Princípios do Fluxo

Os fluxos arquiteturais seguem os princípios abaixo:

- Cada camada possui responsabilidade bem definida
- Dependências sempre apontam para dentro (domínio)
- Integrações externas são tratadas na borda do sistema
- Persistência é explícita e controlada
- Nenhuma camada conhece mais do que precisa

Esses princípios guiam todas as decisões de orquestração do sistema.

---

## Fluxo 1: Buscar Série e Persistir seus Episódios

Este é o fluxo mais completo do sistema atualmente.

### 1. Interface (CLI)

Responsabilidades:

- Receber o nome da série
- Disparar o caso de uso correspondente
- Exibir resultados ao usuário

Limites claros:

- Não contém lógica de negócio
- Não conhece OMDB, JPA ou domínio
- Não decide fluxo nem regras

A interface é considerada **descartável**.

---

### 2. Service de Aplicação

Responsabilidades:

- Orquestrar o caso de uso
- Decidir quais dados devem ser buscados
- Coordenar chamadas ao client OMDB
- Coordenar a criação e persistência do domínio

Responsabilidade principal:

> Controlar o fluxo, não processar dados.

O service **não executa regras de domínio** nem faz parsing de dados externos.

---

### 3. Client de Integração OMDB

Responsabilidades:

- Montar URLs e parâmetros
- Executar chamadas HTTP
- Receber respostas da API externa
- Converter JSON em DTOs externos

Limites claros:

- Não persiste dados
- Não conhece domínio
- Não decide fluxo
- Não contém regras de negócio

É um componente **burro e isolado**, responsável apenas por integração externa.

---

### 4. Conversão para Domínio

Responsabilidades:

- Converter DTOs externos em entidades de domínio
- Tratar inconsistências e instabilidades da API
- Garantir que o domínio receba apenas dados válidos

Características:

- Conversão ocorre **antes** de qualquer persistência
- Factories atuam como fronteira entre mundo externo e domínio
- Nenhuma regra de negócio vive aqui

O domínio permanece **limpo e independente**.

---

### 5. Services de Persistência

Responsabilidades:

- Validar pré-condições de uso
  - Tipo do título
  - Ordem de persistência
  - Existência prévia da série
- Controlar escopo transacional
- Persistir entidades de forma explícita

Decisão central:

- Episódios são persistidos **por temporada**
- Cada temporada é uma **unidade transacional independente**

Os services controlam **quando e como** a persistência ocorre.

---

### 6. Repositories

Responsabilidades:

- Executar operações de banco de dados
- Traduzir comandos em queries persistentes

Limites claros:

- Não conhecem domínio
- Não conhecem OMDB
- Não conhecem fluxo de aplicação
- Apenas executam contratos do Spring Data JPA

São componentes puramente técnicos.

---

### 7. Banco de Dados

Responsabilidades:

- Armazenar o estado persistido
- Refletir decisões tomadas nas camadas superiores

Limites claros:

- Não contém lógica de negócio
- Não valida regras de domínio
- Não executa fluxo

O banco é **passivo**.

---

## Fluxo 2: Persistência Parcial de Série

Este fluxo descreve o comportamento do sistema em caso de falha durante a persistência.

### Cenário

- Temporada 1 é persistida com sucesso
- Temporada 2 falha durante a persistência

### Comportamento do sistema

- A transação da temporada 2 é revertida
- A temporada 1 permanece persistida
- O sistema aceita um estado parcial da série

Motivações dessa decisão:

- Evitar transações longas
- Simplificar controle de falhas
- Manter previsibilidade do sistema
- Tornar o fluxo explícito e rastreável

Essa decisão é **intencional e assumida**.

---

## Fluxos Não Implementados (por enquanto)

Os seguintes fluxos são conhecidos, mas ainda **não fazem parte do sistema**:

- Atualização incremental de episódios
- Reprocessamento de temporadas
- Sincronização entre execuções
- Exposição via API REST
- Read models para análise

Esses fluxos serão documentados **apenas quando implementados**.

---

## Considerações Finais

- O fluxo privilegia clareza sobre abstração
- Cada camada conhece apenas o necessário
- O domínio é protegido contra vazamento de responsabilidades
- Persistência é explícita, previsível e controlada

Este documento deve evoluir junto com o projeto.

Sempre que um fluxo arquitetural mudar de forma relevante,
este arquivo deve ser atualizado para refletir o novo comportamento do sistema.
