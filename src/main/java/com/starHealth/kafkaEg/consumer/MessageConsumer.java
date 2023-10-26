package com.starHealth.kafkaEg.consumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import org.influxdb.InfluxDB;
//import org.influxdb.InfluxDBFactory;
//import org.influxdb.dto.Point;
//import org.influxdb.dto.WriteOptions;
//import org.influxdb.dto.Point.Measurement;
//import org.influxdb.dto.Point.Builder;


@Service
public class MessageConsumer {
	
	private final InfluxDB influxDB;
    
    public MessageConsumer() {
        // Initialize InfluxDB connection here
        influxDB = InfluxDBFactory.connect("http://localhost:8086", "username", "password");
        influxDB.setDatabase("your-database-name"); // Replace with your InfluxDB database name
    }
    

    @KafkaListener(topics = "my-topic", groupId = "my-group-id")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
    
    
    
    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void consumeTrackingData(String trackingData) {
        // Process the tracking data, e.g., deserialize and prepare for InfluxDB
        // Insert the data into InfluxDB using the InfluxDB Java client
    	
    	 // Deserialize the trackingData if it's in JSON format
        // For example, you can use a JSON deserializer such as Jackson

        // Process and prepare the data for InfluxDB
        // Replace these fields with the actual data you want to store in InfluxDB
        String measurement = "tracking_metrics"; // Replace with your desired measurement name
        String tag = "example_tag"; // Replace with your desired tag key
        String tagValue = "example_value"; // Replace with your desired tag value
        String fieldKey = "example_field"; // Replace with your desired field key
        String fieldValue = "example_field_value"; // Replace with your desired field value

        // Build a Point for InfluxDB
        Point point = Point.measurement(measurement)
            .tag(tag, tagValue)
            .addField(fieldKey, fieldValue)
            .build();

        // Write the data to InfluxDB
        influxDB.write(WriteOptions.builder().batchSize(1).build(), point);
    }

}	