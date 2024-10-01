# Projeto RabbitMQ: Produtor Java e Consumidores Python

O sistema tem como objetivo enviar e receber notificações sobre novas atualizações de software para diferentes usuários de um sistema, permitindo que administradores de TI enviem alertas sobre correções de segurança, atualizações de funcionalidades e outros tipos de releases. Essas mensagens são distribuídas entre os consumidores, que podem ser dispositivos de usuários finais ou servidores corporativos. Além disso, um componente de auditoria registra todas as mensagens enviadas para fins de monitoramento e histórico.

## Índice

- [Descrição do Cenário](#descrição-do-cenário)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Executando o Programa](#executando-o-programa)
- [Interagindo com o Programa](#interagindo-com-o-programa)
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
  
## Executando o Programa
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

## Interagindo com o Programa
### 1. Menu
O programa apresentará quatro opções.
- **1. Produtor de Atualizações**
- **2. Consumidor de Atualizações**
- **3. Auditoria**
- **4. Sair**

### 2. Produtor
O programa solicitará:

- **Nome do produtor**
- **Tipo de atualização** 
- **Descrição da atualização**
- **Tipo de notificação**

### 3. Auditoria
O programa solicitará que o usuário execute o comando `control + c` e após esse comando execute o comando `python3 auditoria.py`

### 4. Consumidor
- O programa solicitará que o usuário execute o comando `control + c` e após esse comando execute o comando `python3 consumer.py`, após isso será necessário informar o tipo de notificação que o consumidor vai receber (exemplo: Notificação de segurança, Atualização de sistema).

## Grupo
**Integrantes**
- Arthur Reis
- Diego Escorel
- Edmar Alencar
- Gabriel Moura
- Luiz Felipe Soriano 
- Renato Santana