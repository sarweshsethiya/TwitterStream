package com.hsbc.twitter.TwitterStream;

import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.google.gson.Gson;

public class TwitterStreamProducer {

	public static void kafkaProducer(LinkedBlockingQueue queue){
		
		
		String topicName = "Sarwesh";
	    //Add Kafka producer config settings
	    Properties props = new Properties();
	    props.put("bootstrap.servers", "localhost:9092");
	    props.put("acks", "all");
	    props.put("retries", 0);
	    props.put("batch.size", 16384);
	    props.put("linger.ms", 1);
	    props.put("buffer.memory", 33554432);
	    
	    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
	    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
	    
	    TwitterStreamBean bean = (TwitterStreamBean) queue.poll();
	    String json = new Gson().toJson(bean);

	    
	    Producer<String, String> producer = new KafkaProducer<String, String>(props);
	    ProducerRecord<String,String> record=  new ProducerRecord<String,String>(topicName, json);
	    producer.send(record);
	    System.out.println("Record Sent successfully");
	   // producer.close();
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
