# Projeto de Gerência de Estoque SwiftLog
<p1 align="center">
Repositório dedicado ao Backend em <b>desenvolvimento</b> da aplicação
<a href="https://github.com/watanabe9090/gerenciador_estoque_react"> 
gerenciador_estoque_react
</a>
onde é desenvolvido um software que abrange as funcionalidades básicas de um gerenciador de estoque
</p1>

### Conteúdos

* Introdução e Estado de desenvolvimento do projeto
* Features 
* Arquitetura 
* Instalação
* Tecnologias utilizadas

### Introdução e Estado do projeto

O Sistema ainda não possui uma primeira versão, pois 
está em desenvolvimento, com algumas funcionalidades 
incompletas e outras ainda não desenvolvidas.
O projeto visa um sistema básico para gerir o
estoque de uma pequena loja.

### Features

* [X] Entrada de produtos
* [ ] Saída de produtos</li>
* [X] Cadastro / Alteração / Detalhamento de Locais
* [X] Cadastro / Alteração / Detalhamento de Setores
* [X] Cadastro / Alteração / Detalhamento de Fornecedores
* [X] Cadastro / Alteração / Detalhamento de Marcas
* [X] Cadastro / Alteração / Detalhamento de Clientes<
* [X] Cadastro / Alteração / Detalhamento de Colaboradores<
* [ ] Cadastro / Alteração / Detalhamento de Lote
* [ ] Buscas  
* [ ] Paginação
* [ ] Relatórios




### Pré-requisitos
<ol>
    <li>É necessário ter no mínimo a versão 8 do Java, juntamente ao Maven</li>
    <li>Estando na pasta raiz do projeto, onde no mesmo nível está localizado o 
    pom.xml, utilize o comando<code>mvn install</code> para baixar as dependências
    do projeto</li>
    <li>Após isso será gerado uma pasta chamada <i>target</i>, nela estará contido o
    arquivo <b>.jar</b>, para executar, utilize o comando java -jar 'gerenciador_estoque-0.0.1-SNAPSHOT.jar'</li>
    <li>A porta 8080 deve estar disponível, 
    caso contrário, pode ser alterado com 
    o commando --server.port=<b>NUM_PORTA</b>, ou diretamente 
    no arquivo application.properties
    </li>
</ol>

### Tecnologias Utilizadas
<ul>
    <li>Java</li>
    <li>Framework Spring e Spring boot</li>
    <li>Banco de dados H2</li>
    <li>Lombok</li>
    <li>JUnit4</li>
</ul>


