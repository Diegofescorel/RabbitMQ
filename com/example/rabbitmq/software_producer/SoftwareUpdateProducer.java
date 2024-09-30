package com.example.rabbitmq.software_producer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SoftwareUpdateProducer {

    private final static String EXCHANGE_NAME = "software_update_exchange";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                
                System.out.println("Informe o nome do produtor:");
                String producer = scanner.nextLine();
                System.out.println("Informe o tipo de atualização (Correção de Segurança, Atualização de Funcionalidades, Manutenção de Sistema):");
                String updateType = scanner.nextLine();
                System.out.println("Informe a descrição da atualização:");
                String description = scanner.nextLine();
                System.out.println("Informe o tipo de notifição (ex: Notificação de segurança, Atualização de sistema):");
                String routingKey = scanner.nextLine();
                
                String content = "[" + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")) + "]" + " " + producer + " : " + updateType + " : " + description; 

                channel.basicPublish(EXCHANGE_NAME, routingKey, null, content.getBytes(StandardCharsets.UTF_8));
                System.out.println(" [x] Mensagem: '" + content + "'" + " enviada com sucesso no tópico " + routingKey);

                String answer;
                do {
                    System.out.println("Quer enviar outra mensagem? (S/N)");
                    answer = scanner.nextLine().toLowerCase();
                    if (!answer.equals("s") && !answer.equals("n")) {
                        System.out.println("Entrada inválida. Digite 's' para sim ou 'n' para não.");
                    }
                } while (!answer.equals("s") && !answer.equals("n"));

                if (answer.equals("n")) {
                    break;
                }
            }
        }
    }
}
