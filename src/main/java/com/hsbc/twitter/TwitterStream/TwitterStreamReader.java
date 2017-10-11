package com.hsbc.twitter.TwitterStream;

import java.util.HashSet;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;

public class TwitterStreamReader {

	public static void main(String[] args) {
		
		final LinkedBlockingQueue<TwitterStreamBean> queue = new LinkedBlockingQueue<TwitterStreamBean>(1000);
		
		TwitterStream stream = new TwitterStreamUtil().getStream();
		
		StatusListener listener = new StatusListener() {
			
			public void onException(Exception ex) {
				// TODO Auto-generated method stub
				System.out.println("Exception occured"+ ex.getMessage());
				ex.printStackTrace();
			}
			
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub
				
			}
			
			public void onStatus(Status status) {
				// TODO Auto-generated method stub
				
				System.out.println("Got Tweet"+ status.getText());
				
				TwitterStreamBean bean = new TwitterStreamBean();
				String username = status.getUser().getScreenName();
				bean.setUsername(username);
				bean.setId(status.getId());
				bean.setInReplyUserName(status.getInReplyToScreenName());
				bean.setContent(status.getText());
				
				HashtagEntity hashTags[] = status.getHashtagEntities();
				HashSet<String> hashTagContainer = new HashSet<String>();
		        for(HashtagEntity hashTag: hashTags) {
		          hashTagContainer.add(hashTag.getText());
		        }
		        System.out.println("HashTags: " + hashTagContainer);
				
				if(status!=null && status.getRetweetedStatus()!=null && status.getRetweetedStatus().getUser() !=null)
				{
					
					bean.setRetweetUserName(status.getRetweetedStatus().getUser().getScreenName());
				}
				
				queue.offer(bean);	
				System.out.println("Record sending to producer");
				TwitterStreamProducer.kafkaProducer(queue);
			}
			
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
				
			}
			
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub
				
			}
			
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				// TODO Auto-generated method stub
				
			}
		};
		
		FilterQuery query = new FilterQuery();
		
		String keywords[] = {"HSBC","PuneFloMarathon"};
		
		query.track(keywords);
		
		stream.addListener(listener);
		stream.filter(query);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    //stream.shutdown();
	}
}
