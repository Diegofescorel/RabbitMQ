import pika

EXCHANGE_NAME = 'software_update_exchange'

def callback(ch, method, properties, body):
    print(f"[CONSUMIDOR] Notificação recebida {method.routing_key}:\n{body.decode()}")

connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

channel.exchange_declare(exchange=EXCHANGE_NAME, exchange_type='topic')

response = channel.queue_declare(queue='', exclusive=True)

print("Informe o tipo de notifição que deseja receber (exemplo: Notificação de segurança, Atualização de sistema):")
type_notification = input()


channel.queue_bind(exchange=EXCHANGE_NAME, queue=response.method.queue, routing_key=type_notification)

print(f'Consumidor esperando notificações do tipo "{type_notification}". Para sair, pressione CTRL + C')

channel.basic_consume(queue=response.method.queue, on_message_callback=callback, auto_ack=True)
channel.start_consuming()
