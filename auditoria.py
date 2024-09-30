import pika
import sys  # Adicionar a biblioteca sys para usar flush()

EXCHANGE_NAME = 'software_update_exchange'

def callback(ch, method, properties, body):
    print(f"[AUDITORIA] Notificação auditada:\n{body.decode()}")
    sys.stdout.flush()  # Força o envio da saída imediatamente

# Connection to RabbitMQ
connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

# Declare the exchange
channel.exchange_declare(exchange=EXCHANGE_NAME, exchange_type='fanout')

# Create a temporary queue for audit
result = channel.queue_declare(queue='', exclusive=True)
queue_name = result.method.queue

# Bind the queue to the exchange
channel.queue_bind(exchange=EXCHANGE_NAME, queue=queue_name)

print(' [*] Auditoria esperando notificações. Para sair, pressione CTRL+C')
sys.stdout.flush()  # Força o envio da saída imediatamente

# Start consuming messages
channel.basic_consume(queue=queue_name, on_message_callback=callback, auto_ack=True)
channel.start_consuming()
