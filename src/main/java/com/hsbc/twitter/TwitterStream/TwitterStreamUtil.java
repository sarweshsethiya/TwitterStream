package com.hsbc.twitter.TwitterStream;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamUtil {
	
	public TwitterStream getStream(){

	ConfigurationBuilder cb= new ConfigurationBuilder();
	
	cb.setDebugEnabled(true);
	cb.setOAuthConsumerKey(TwitterConstants.consumerKey);
	cb.setOAuthConsumerSecret(TwitterConstants.consumerSecret);
	cb.setOAuthAccessToken(TwitterConstants.accessToken);
	cb.setOAuthAccessTokenSecret(TwitterConstants.accessTokenSecret);
	
	return new TwitterStreamFactory(cb.build()).getInstance();
	}
}
