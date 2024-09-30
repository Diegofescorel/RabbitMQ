# Projeto RabbitMQ: Produtor Java e Consumidores Python

Este projeto demonstra a comunicação entre um **produtor Java** e **consumidores Python** usando o **RabbitMQ** como intermediário de mensagens. O sistema permite o envio de notificações de atualizações de software, que são consumidas por um ou mais consumidores, incluindo um processo de auditoria.

## Índice

- [Descrição do Cenário](#descrição-do-cenário)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Configuração do Ambiente](#configuração-do-ambiente)
- [Executando o Produtor Java](#executando-o-produtor-java)
- [Executando os Consumidores Python](#executando-os-consumidores-python)

## Descrição do Cenário

O sistema consiste em:

- **Produtor Java (`SoftwareUpdateProducer.java`)**: Envia mensagens sobre atualizações de software para um exchange do RabbitMQ.
- **Consumidor Python (`consumer.py`)**: Recebe as mensagens do exchange e as processa.
- **Auditoria Python (`auditoria.py`)**: Recebe todas as mensagens para fins de auditoria.

A comunicação é feita através de um **exchange do tipo `fanout`**, que envia todas as mensagens recebidas para todas as filas ligadas a ele.

## Estrutura do Projeto
```plaintext
RabbitMQ/
├── com/
│   └── example/
│       └── rabbitmq/
│           └── software_producer/
│               ├── SoftwareUpdateProducer.java
│               └── SoftwareUpdateProducer.class
├── consumer.py
├── auditoria.py
├── lib/
│   ├── amqp-client-5.22.0.jar
│   ├── slf4j-api-2.0.16.jar
│   └── slf4j-simple-2.0.16.jar
└── venv/
```

## Pré-requisitos

- **Java Development Kit (JDK) 8 ou superior**
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
  ```python3 -m venv venv```
  
- **Ativar o ambiente virtual:**
  ```source venv/bin/activate```

- **Instalar a biblioteca pika:**
  ```pip install pika```
  
  
## Executando o Produtor Java**
### 1. Navegar até o Diretório do Projeto
  
  ```bash
  cd /caminho/para/seu/projeto/RabbitMQ
  ```

### 2. Compilar o Código Java
  ```java
  javac -cp ".:lib/*" com/example/rabbitmq/software_producer/SoftwareUpdateProducer.java
```

### 3. Executar o Produtor Java
  ```java
  java -cp ".:lib/*" com.example.rabbitmq.software_producer.SoftwareUpdateProducer
```

### 4. Interagir com o Programa
O programa solicitará:

- **Nome do produtor**
- **Tipo de atualização** 
- **Descrição da atualização**

## Executando os Consumidores Python
### 1. Ativar o Ambiente Virtual
Em cada terminal onde você executará um consumidor:
  
  ```bash
  cd /caminho/para/seu/projeto/RabbitMQ
  source venv/bin/activate
```

### 2. Executar o consumer.py
```python consumer.py```

### 3. Executar o auditoria.py
Em outro terminal com o ambiente virtual ativado:

```python auditoria.py```