package com.hsbc.twitter.TwitterStreamConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class TwitterStreamConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String topicName = "Sarwesh";
	    //Add Kafka consumer config settings
	    Properties props = new Properties();
	    props.put("bootstrap.servers", "localhost:9092");
	    props.put("group.id", "test");
	    props.put("enable.auto.commit", "true");
	    props.put("session.timeout.ms", "30000");
	    props.put("auto.commit.interval.ms", "1000");
	    
	    props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
	    props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
	    
	    KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(props);
	    
	    consumer.subscribe(Arrays.asList(topicName));
	    
	    System.out.println("consumer subscribed to topic "+topicName);
	    
	    while(true){
	    	ConsumerRecords<String, String> records = consumer.poll(5000);
	    	for(ConsumerRecord<String, String> record:records){
	    		
	    		System.out.printf("offset=%d , value=%s ", record.offset(), record.value());
	    	
	    		
	    	}
	    }
	}

}
