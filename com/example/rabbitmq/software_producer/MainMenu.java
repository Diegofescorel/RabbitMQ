
package com.example.rabbitmq.software_producer;

import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== Sistema de Atualizações de Código =====");
            System.out.println("1. Produtor de Atualizações");
            System.out.println("2. Consumidor de Atualizações");
            System.out.println("3. Auditoria");
            System.out.println("4. Sair");
            System.out.print("Digite a sua escolha: \n");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    try {
                        SoftwareUpdateProducer.main(new String[]{});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println("Execute o comando a 'control + c' e o comando a seguir  para rodar o consumidor");
                    System.out.println("Comando: 'python3 consumer.py'\n");
                    break;

                case 3:
                    System.out.println("Execute o comando a 'control + c' e o comando a seguir  para rodar a auditoria");
                    System.out.println("Comando: 'python3 auditoria.py'\n");
                    break;

                case 4:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Escolha inválida. Tente novamente.");
            }
        } while (choice != 4);

        scanner.close();
    }
}