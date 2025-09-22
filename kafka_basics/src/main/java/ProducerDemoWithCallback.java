import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;
import java.util.random.RandomGenerator;

public class ProducerDemoWithCallback {
    private static final Logger log = LoggerFactory.getLogger(ProducerDemoWithCallback.class.getSimpleName());
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,  "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        RandomGenerator randomGenerator = new Random();
        randomGenerator.ints(1);

        try(Producer<String, String> producer = new KafkaProducer<>(props)) {
            ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", String.valueOf(new Random().nextInt()), "first message");
            producer.send(record, (recordMetadata, e) -> {
                if(e == null) {
                    log.info("producing the message {} : {} : {} : {}",  recordMetadata.topic(), record.key(), recordMetadata.partition(), recordMetadata.offset());
                }else {
                    log.error("error while producing the message");
                }
            });
        }
    }
}
