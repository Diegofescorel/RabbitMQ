
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
            System.out.print("Digite a sua escolha: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Chamar o código do produtor
                    try {
                        SoftwareUpdateProducer.main(new String[]{});
                    } catch (Exception e) {
                        e.printStackTrace();  // Tratar exceção do produtor
                    }
                    break;
                case 2:
                    // Rodar o consumidor
                    System.out.println("Execute o comando a seguir para rodar o consumidor:");
                    System.out.println("Comando: python3 consumer.py\n");
                    // runPythonScript("./consumer.py");
                    break;
                case 3:
                    // Rodar a auditoria
                    // System.out.println("Iniciando a auditoria...");
                    // runPythonScript("./auditoria.py");
                    System.out.println("Execute o comando a seguir para rodar a auditoria:");
                    System.out.println("Comando: python3 auditoria.py\n");
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

    // private static void runPythonScript(String scriptPath) {
    // try {
    //     // Executa o comando para rodar o script Python
    //     Process process = Runtime.getRuntime().exec("python3 " + scriptPath);
    //     System.out.println("Script Python iniciado: " + scriptPath);

    //     // Captura a saída padrão (stdout)
    //     StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
    //     // Captura a saída de erro (stderr)
    //     StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), System.err::println);

    //     Executors.newSingleThreadExecutor().submit(outputGobbler);
    //     Executors.newSingleThreadExecutor().submit(errorGobbler);

    //     // Não usamos process.waitFor(), pois o script pode rodar indefinidamente
    //     System.out.println("O script está sendo executado em segundo plano.");
        
    //     // Pausar até o usuário pressionar Enter
    //     System.out.println("Pressione Enter para voltar ao menu.");
    //     new Scanner(System.in).nextLine();

    // } catch (Exception e) {
    //     e.printStackTrace();
    // }
}

// }

// class StreamGobbler implements Runnable {
//     private final BufferedReader reader;
//     private final Consumer<String> consumer;

//     public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
//         this.reader = new BufferedReader(new InputStreamReader(inputStream));
//         this.consumer = consumer;
//     }

//     @Override
//     public void run() {
//         try {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 consumer.accept(line);
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// }
