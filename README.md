# Projeto RabbitMQ: Produtor Java e Consumidores Python

Este projeto demonstra a comunicação entre um **produtor Java** e **consumidores Python** usando o **RabbitMQ** como intermediário de mensagens. O sistema permite o envio de notificações de atualizações de software, que são consumidas por um ou mais consumidores, incluindo um processo de auditoria.

## Índice

- [Descrição do Cenário](#descrição-do-cenário)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Executando o Progama](#executando-o-progama)
- [Interagindo com o Progama](#interagindo-com-o-progama)
- [Grupo](#grupo)



## Descrição do Cenário

O sistema consiste em:

- **Produtor Java (`SoftwareUpdateProducer.java`)**: Envia mensagens sobre Notificações de software ou/e Atualizações de segurança  para um exchange do RabbitMQ.
- **Consumidor Python (`consumer.py`)**: Recebe as mensagens do exchange e as processa.
- **Auditoria Python (`auditoria.py`)**: Recebe todas as mensagens para fins de auditoria.

A comunicação é feita através de um **exchange do tipo `topic`**, que envia todas as mensagens recebidas para todas as filas ligadas a ele.

## Estrutura do Projeto
```plaintext
RabbitMQ/
├── com/
│   └── example/
│       └── rabbitmq/
│           └── software_producer/
│               ├── SoftwareUpdateProducer.java
│               ├── SoftwareUpdateProducer.class
│               ├── MainMenu.java
│               └── MainMenu.class
├── consumer.py
├── auditoria.py
├── lib/
│   ├── amqp-client-5.22.0.jar
│   ├── slf4j-api-2.0.16.jar
│   └── slf4j-simple-2.0.16.jar
└── venv/
```

## Pré-requisitos

- **Java Development Kit (JDK) 8+**
- **Python 3**
- **RabbitMQ instalado e em execução**
- **Bibliotecas Java:**
  - `amqp-client-5.22.0.jar`
  - `slf4j-api-2.0.16.jar`
  - `slf4j-simple-2.0.16.jar`
- **Biblioteca Python:**
  - `pika`

## Configuração do Ambiente

### 1. Instalar o RabbitMQ

Certifique-se de que o RabbitMQ está instalado e em execução no seu sistema.

- **No macOS:**

  ```bash
  brew install rabbitmq
  brew services start rabbitmq
  ```
  
### 2. Clonar o Repositório ou Baixar os Arquivos

Obtenha os arquivos do projeto e coloque-os em um diretório adequado.

### 3. Configurar o Ambiente Java
- Adicione as bibliotecas Java à pasta `lib/` do projeto:
  - `amqp-client-5.22.0.jar`
  - `slf4j-api-2.0.16.jar`
  - `slf4j-simple-2.0.16.jar`

- **Estrutura de diretórios:**

A estrutura de pacotes do Java deve ser refletida nos diretórios. Certifique-se de que o arquivo `SoftwareUpdateProducer.java` está em `com/example/rabbitmq/software_producer/`.

### 4. Configurar o Ambiente Python
- **Criar um ambiente virtual:**
  ```bash
  python3 -m venv venv
  ```
  
- **Ativar o ambiente virtual:**
  ```bash
  source venv/bin/activate
  ```

- **Instalar a biblioteca pika:**
  ```bash
  pip install pika
  ```
  
## Executando o Progama
### 1. Navegar até o Diretório do Projeto
  
  ```bash
  cd /caminho/para/seu/projeto/RabbitMQ
  ```

### 2. Compilar o Código Java
  ```java
  javac -cp ".:lib/*" com/example/rabbitmq/software_producer/MainMenu.java
```

### 3. Executar o Produtor Java
  ```java
  java -cp ".:lib/*" com.example.rabbitmq.software_producer.MainMenu
```

## Interagindo com o Progama
### 1. Menu
O programa irá dar 4 opções, basta somente escolher qual será a sua função.
- **1. Produtor de Atualizações**
- **2. Consumidor de Atualizações**
- **3. Auditoria**
- **4. Sair**

### 2. Produtor
O programa solicitará:

- **Nome do produtor**
- **Tipo de atualização** 
- **Descrição da atualização**

### 3. Auditoria
O programa solicitará que o usuario execute o comando `control + c` e apos esse comando execute o comando `python3 auditoria.py`

### 3. Consumidor
- O programa solicitará que o usuario execute o comando `control + c` e apos esse comando execute o comando `python3 consumer.py`, após isso será necessario informar o tipo de notificação que o consumidor vai receber (exemplo: Notificação de segurança, Atualização de sistema).

## Grupo
**Integrantes**
- Arthur Reis
- Diego Escorel
- Edmar Alencar
- Gabriel Moura
- Luiz Felipe Soriano 
- Renato Santana