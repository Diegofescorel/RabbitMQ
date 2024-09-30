package com.example.rabbitmq.software_producer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SoftwareUpdateProducer {

    private final static String EXCHANGE_NAME = "software_update_exchange";

    public static void main(String[] argv) throws Exception {
        // Connection to RabbitMQ
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            // Declare a fanout exchange
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                // Collect information about the software update
                System.out.println("Informe o nome do produtor:");
                String producer = scanner.nextLine();
                System.out.println("Informe o tipo de atualização (Correção de Segurança, Atualização de Funcionalidades, Manutenção de Sistema):");
                String updateType = scanner.nextLine();
                System.out.println("Informe a descrição da atualização:");
                String description = scanner.nextLine();

                // Format the message
                String message = String.format("[%s] %s : %s : %s",
                    java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")),
                    producer, updateType, description);

                // Send the message to the exchange
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Enviado: '" + message + "'");

                // Verificação da resposta do usuário
                String answer;
                do {
                    System.out.println("Deseja enviar outra mensagem? (s/n)");
                    answer = scanner.nextLine().toLowerCase();
                    if (!answer.equals("s") && !answer.equals("n")) {
                        System.out.println("Entrada inválida. Digite 's' para sim ou 'n' para não.");
                    }
                } while (!answer.equals("s") && !answer.equals("n"));

                // Se a resposta for "n", interrompe o loop
                if (answer.equals("n")) {
                    break;
                }
            }
        }
    }
}
