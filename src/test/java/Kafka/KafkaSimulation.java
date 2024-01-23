package Kafka;

import io.gatling.javaapi.core.*;
import org.apache.kafka.common.header.*;
import org.apache.kafka.common.header.internals.*;
import ru.tinkoff.gatling.kafka.javaapi.protocol.*;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static ru.tinkoff.gatling.kafka.javaapi.KafkaDsl.*;
public class KafkaSimulation extends Simulation{
    public static final String IP_SERVER = System.getProperty("IP_SERVER", "");
    public static final String URL_REGISTRY = System.getProperty("URL_REGISTRY", "");
    public static final String USER_AUTH = System.getProperty("USER_AUTH", "");
    private final KafkaProtocolBuilder kafkaProtocol = kafka()
            .topic("test2")
            .properties(
                    Map.of(
                            ProducerConfig.ACKS_CONFIG, "1",

                            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",

                            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer",

                            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , "org.apache.kafka.common.serialization.StringSerializer")
            );

    private final Headers headers = new RecordHeaders(new Header[]{new RecordHeader("test-header", "value".getBytes())});

    private final ScenarioBuilder kafkaProducer = scenario("Kafka Producer")
            .exec(kafka("Simple Message")
                    .send("key","test - value", headers)
            );

    {
        setUp(

                kafkaProducer.injectOpen(incrementUsersPerSec(10)
                        .times(4).eachLevelLasting(6)
                        .separatedByRampsLasting(5)
                        .startingFrom(5.0))
        ).protocols(kafkaProtocol);
    }
}