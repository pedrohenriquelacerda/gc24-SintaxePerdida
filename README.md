# Projeto Final - Sintaxe Perdida
Projeto de conclusão da trilha de Programação Java do GC 2024.
<p align="center">
     <a alt="Java" href="https://java.com" target="_blank">
        <img src="https://img.shields.io/badge/Java-v21.0.4-ED8B00.svg" />
    </a>
     <a alt="Maven" href="https://maven.apache.org/index.html" target="_blank">
        <img src="https://img.shields.io/badge/Maven-v4.0.0-CD2335.svg" />
    </a>
     <a alt="IntelliJ IDEA" href="https://www.jetbrains.com/idea/" target="_blank">
        <img src="https://img.shields.io/badge/IntelliJ IDEA-2024.2.4-087CFA.svg" />
    </a>
</p>

## Visão Geral do Projeto

O objetivo desta atividade é revisar e praticar diversos conceitos de Java,
incluindo tipos de dados, enum, interfaces, arrays, listas, mapas e muito
mais \o/, enquanto aprimoramos nossas habilidades com o Git e implementamos
a metodologia ágil Scrum, utilizando o software Trello.

### Sobre o Projeto
Este projeto proporcionará aos entusiastas da franquia Zelda uma plataforma envolvente e interativa, reunindo informações 
detalhadas e permitindo a interação social entre os usuários. Prepare-se para mergulhar no universo de Zelda e explorar 
o potencial da arquitetura de microserviços em Java com Spring!

---

## Sumário

- [Funcionalidades](#funcionalidades)
- [Entregáveis](#entregáveis)
- [Funcionalidades a Implementar](#funcionalidades-a-implementar)
- [Apêndice](#apêndice)
- [Autores](#autores)

---
## Funcionalidades

Acordos para o Trabalho Final:

- **MVP (Mínimo Produto Viável):**
    - Compreende a API de Usuário e a API de consulta à API pública do Zelda, com os nomes "user-service" e "zelda-service", respectivamente.
    - O MVP também inclui testes unitários em ambas as APIs.

- **Primeira Versão da Aplicação (V1):**
    - Implementar a API Gateway, chamada "gateway-service", que terá TODOS os endpoints das outras APIs que ela vai chamar.
    - Para mais informações sobre o padrão gateway, consulte [https://microservices.io/patterns/apigateway.html](https://microservices.io/patterns/apigateway.html).
    - Opcionalmente, implementar um front-end com tecnologias de sua escolha, mas que deve chamar SOMENTE o "gateway-service".

- **Segunda Versão (V2):**
    - Implementar autenticação no "user-service".
    - Após autenticado, gerar um token que deve ser passado nos outros endpoints do sistema.

- **Final Versão (V3):**
    - Implementar um cache das informações da API externa.
    - Com essas informações, associar um usuário a VÁRIOS jogos, retornando os dados do usuário junto com seus jogos preferidos da franquia Zelda.

    - **V4:**
      O Cliente conversou com o PO e chegou uma nova demanda para nós:
        - Eu, como usuário do sistema, desejo escrever uma análise ou a minha opinião sobre meus jogos preferidos da franquia;
        - Eu, como usuário do sistema, desejo poder atualizar minhas análises;
        - Eu, como usuário do sistema, desejo poder ver todas as minhas análises e ver uma análise por vez.
          Cabe a nós, como desenvolvedores, pensar na solução e codificá-la.

---

## Entregáveis

- O projeto deve ser desenvolvido por um grupo de 5 alunos.
- O projeto deve ser entregue no GitHub em um repositório privado.
- Deve ser criada uma branch "dev" a partir da "main" e para todas as tarefas, deve-se criar uma branch a partir da "dev".
- A documentação deve estar no arquivo `README.md`.

---

## Funcionalidades a Implementar

- **API de usuário (CRUD)**.
- **API de consulta à API pública Zelda**
- **API Gateway para Integração**

---

## Apêndice

### 1. Detalhes Adicionais

**Tecnologias Utilizadas:**
- Utilize Spring Boot para implementar os microserviços, aproveitando a robustez e a flexibilidade oferecidas por esse framework.
- O banco de dados PostgreSQL será a base de armazenamento para as informações dos usuários.
- A escolha entre Maven ou Gradle fica a critério do time.
- Utilize a biblioteca Log4J para imprimir logs de sucesso ou erros, uma prática comum no dia a dia de desenvolvedores.

**Testes e Qualidade de Código:**
- Implemente testes unitários para assegurar a robustez das funcionalidades de cada microserviço.
- Mantenha um código claro e bem-estruturado, aderindo às melhores práticas de desenvolvimento.

**Metodologia Ágil:**
- Assim como na amarelinha, vamos utilizar um quadro Kanban para organização das features.
- O quadro Kanban deve ser apresentado no final do projeto.

### 2. Testes

Projeto em desenvolvimento


---

## Autores
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/BrunoBitencourt99">
        <img loading="lazy" src="https://avatars.githubusercontent.com/u/158428292?v=4" width="115"/><br />
        <sub><b>Bruno Bitencourt</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Diasvcarol">
        <img loading="lazy" src="https://avatars.githubusercontent.com/u/174751469?v=4" width="115"/><br />
        <sub><b>Carolina Viola</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/GFerrDev">
        <img loading="lazy" src="https://avatars.githubusercontent.com/u/173212989?v=4" width="115"/><br />
        <sub><b>Gabriel Ferreira</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/juliana-oliveira7">
        <img loading="lazy" src="https://avatars.githubusercontent.com/u/124221589?v=4" width="115"/><br />
        <sub><b>Juliana Oliveira</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/pedrohenriquelacerda">
        <img loading="lazy" src="https://avatars.githubusercontent.com/Nathalia-Asantos?v=4" width="115"/><br />
        <sub><b>Pedro Henrique Lacerda</b></sub>
      </a>
    </td>
  </tr>
</table>
