# Arquitetura do Projeto OMDB

## Visão Geral

Este documento registra as **principais decisões arquiteturais** do projeto OMDB.

O objetivo não é descrever detalhes de implementação, mas documentar **intenções, limites e trade-offs**
assumidos ao longo da evolução do sistema, servindo como referência para manutenção, evolução e revisão
técnica futura.

Este documento reflete o **estado atual do entendimento arquitetural** do projeto e deve evoluir
junto com ele.

---

## Princípios Gerais

As decisões arquiteturais do projeto seguem os princípios abaixo:

- Separação clara de responsabilidades entre camadas
- Isolamento explícito de integrações externas
- Domínio independente de frameworks e APIs externas
- Evolução incremental, evitando antecipar complexidade
- Clareza e previsibilidade acima de abstrações sofisticadas

O projeto prioriza **coerência arquitetural** em vez de soluções completas desde o início.

---

## Integração com a API do OMDB

A comunicação com a API do OMDB é centralizada em um **client dedicado**.

### Decisões

- DTOs da OMDB existem apenas na camada de integração
- Dados instáveis ou inconsistentes da API são tratados na borda do sistema
- Nenhuma outra camada conhece detalhes da API externa

### Motivações

- Reduzir acoplamento com serviços externos
- Facilitar substituição ou extensão futura da integração
- Evitar contaminação do domínio com regras ou formatos externos

### Observação adicional

Tratamentos defensivos simples de dados externos (ex.: valores ausentes ou inválidos)
podem ocorrer em **factories ou mappers**, desde que **não envolvam regras de negócio**
nem decisões de fluxo da aplicação.

---

## Domínio

O domínio representa os conceitos centrais do sistema, como **filmes, séries, temporadas e episódios**.

### Decisões importantes

- O domínio **não depende** de DTOs externos
- O domínio **não depende** de JPA ou detalhes de persistência
- Entidades garantem suas próprias invariantes
- Conversões de dados externos da OMDB para o domínio são realizadas por **factories dedicadas**

### Organização do Domínio

- **Serie** é tratada como *aggregate root*
- **Temporada** existe exclusivamente no contexto de uma série
- **Episodio** existe exclusivamente no contexto de uma temporada
- **Filme** é uma entidade independente, sem agregações internas

O domínio é tratado como a parte mais **estável e protegida** do sistema.

---

## Services

### Services de Aplicação

Responsáveis por:

- Orquestrar casos de uso
- Coordenar chamadas à integração externa
- Decidir quando entidades de domínio devem ser criadas ou agregadas

Não fazem:

- Parsing de dados externos
- Regras internas do domínio
- Persistência direta
- Análises ou estatísticas

Os services de aplicação atuam como **camada de coordenação**, não como centro de regras.

---

### Services de Persistência

Responsáveis por:

- Persistir entidades respeitando as invariantes definidas pelo domínio
- Garantir a ordem correta de persistência
- Controlar atomicidade e escopo transacional

Não fazem:

- Busca de dados externos
- Decisão de fluxo da aplicação
- Regras de negócio ou análise

---

## Persistência

As decisões abaixo descrevem **diretrizes arquiteturais definidas**
para a futura implementação da camada de persistência.

### Aggregate Root

- **Serie** é tratada como *aggregate root*
- **Episodio** depende de uma série existente
- Episódios **não existem de forma independente** no sistema

#### Motivações

- Episódios não fazem sentido sem uma série
- Garantia de consistência estrutural
- Evita entidades órfãs
- Simplifica controle transacional

---

### Persistência de Episódios

A persistência de episódios seguirá regras explícitas:

- A série deve estar persistida antes de qualquer episódio
- Episódios são persistidos **por temporada**
- Cada temporada é tratada como **unidade transacional**
- Não existe transação global envolvendo todas as temporadas da série

#### Consequências assumidas

- Falha em uma temporada não afeta temporadas já persistidas
- O sistema aceita estados parciais por série
- Idempotência **não é garantida** neste estágio

Essas decisões evitam transações longas e simplificam o controle de falhas.

---

### Relacionamentos e Cascade

Decisões atuais:

- Não é utilizado cascade JPA
- Relacionamentos são unidirecionais
- A ordem de persistência é controlada manualmente pelos services

#### Motivações

- Evitar complexidade prematura
- Tornar a persistência explícita e previsível
- Facilitar entendimento e depuração do fluxo

Essas decisões podem ser revisitadas conforme o projeto evoluir.

---

## O que o Projeto NÃO Resolve (por enquanto)

As seguintes preocupações são conhecidas, mas **deliberadamente não resolvidas**
neste estágio:

- Idempotência na persistência de episódios
- Atualização incremental de dados persistidos
- Controle de concorrência
- Otimizações de performance em lote
- Tratamento avançado de erros de domínio
- Read models para análise e estatísticas

Esses pontos serão abordados **somente quando surgirem necessidades reais**.

---

## Considerações Finais

- A arquitetura prioriza decisões explícitas e rastreáveis
- O domínio é protegido contra contaminação externa
- Interfaces e mecanismos de entrada são considerados descartáveis
- Persistência será introduzida apenas após a estabilização do modelo de domínio

Este documento deve ser atualizado sempre que uma decisão arquitetural relevante
for revisitada ou alterada.
