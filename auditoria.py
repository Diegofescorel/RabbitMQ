import pika

EXCHANGE_NAME = 'software_update_exchange'

def callback(ch, method, properties, body):
    print(f"[AUDITORIA] Notificação auditada '{method.routing_key}':\n{body.decode()}")

connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

channel.exchange_declare(exchange=EXCHANGE_NAME, exchange_type='topic')

response = channel.queue_declare(queue='', exclusive=True)

channel.queue_bind(exchange=EXCHANGE_NAME, queue=response.method.queue, routing_key='#')

print(' [*] Auditoria esperando todas as notificações. Para sair, pressione CTRL + C')

channel.basic_consume(queue=response.method.queue, on_message_callback=callback, auto_ack=True)
channel.start_consuming()