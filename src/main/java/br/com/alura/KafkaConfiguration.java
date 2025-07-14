package br.com.alura;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eclipse.microprofile.config.Config;

import java.util.Properties;

@ApplicationScoped
public class KafkaConfiguration {

    private final Config config;

    public KafkaConfiguration(Config config) {
        this.config = config;
    }

    @Produces
    public KafkaProducer<String, String> createKafkaProducer() {
        String kafkaHost = config.getValue("producer.kafka.host", String.class);
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    public void enviarMensagem(String topico, String mensagem) {
        KafkaProducer<String, String> producer = createKafkaProducer();
        ProducerRecord<String, String> record = new ProducerRecord<>(topico, mensagem);
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                Log.info("Mensagem enviada: " + metadata.offset());
            } else {
                Log.error("Erro: " + exception.getMessage());
                exception.getStackTrace();
            }
        });
    }
}
