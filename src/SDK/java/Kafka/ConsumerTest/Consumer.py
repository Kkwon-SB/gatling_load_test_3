# 세부 결과(vscode, python)
# pip install kafka-python

#
# kafka_2.13-2.8.0> bin\windows\zookeeper-server-start.bat config\zookeeper.properties
# kafka_2.13-2.8.0> bin\windows\kafka-server-start.bat config\server.properties


from kafka import KafkaConsumer

consumer = KafkaConsumer(
    'test2',
    bootstrap_servers=['localhost:9092'],
    auto_offset_reset='earliest',
    enable_auto_commit=True,
    group_id='test-group',
    consumer_timeout_ms=1000
)

print('[Start] Consumer Test')

try:
    for message in consumer:
        print(f'test : {message.topic}, Partition : {message.partition}, Offset : {message.offset}, Key : {message.key}, value : {message.value}')
except Exception as e:
    print(f'Error: {e}')

print('[End] Consumer Test ')
