import pika

EXCHANGE_NAME = 'software_update_exchange'

def callback(ch, method, properties, body):
    print(f" [x] Recebido:\n{body.decode()}")

# Connection to RabbitMQ
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

# Declare the exchange
channel.exchange_declare(exchange=EXCHANGE_NAME, exchange_type='fanout')

# Create a temporary queue
result = channel.queue_declare(queue='', exclusive=True)
queue_name = result.method.queue

# Bind the queue to the exchange
channel.queue_bind(exchange=EXCHANGE_NAME, queue=queue_name)

print(' [*] Aguardando notificações de atualizações. Para sair, pressione CTRL+C')

# Start consuming messages
channel.basic_consume(queue=queue_name, on_message_callback=callback, auto_ack=True)
channel.start_consuming()
