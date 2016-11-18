import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TwitterScraper {

    final static String CONSUMER_KEY = "qyC2A2qwQAbHLkIDfZok8YrdY";
    final static String CONSUMER_SECRET = "YsDr7Wol8Ou21CEedMi1UEBtkjDZtkgo1A88TVoGAgVNlLNU9D";
    final static String ACCESS_TOKEN = "37344764-niU57qKXKNHcrI5gt5kVytRlAC0Uk0i0uswiMY60L";
    final static String ACCESS_TOKEN_SECRET = "n4y0jt7p9mKHNScBNgbj97cdnlQYk56OzQZz4IubckzWs";


    public void run(TweetCleaner cleaner) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        /**
         * Change search phrase here
         */
        String SEARCH_PHRASE_HAPPY = 	":)";
        String SEARCH_PHRASE_SAD = 	":)";
        String SEARCH_PHRASE_QUESTION = "?";

        int NUM_OF_RESULTS = 30;

        ArrayList<String> cleanedTweets = new ArrayList<>();
        try {

            Query query = new Query(SEARCH_PHRASE_HAPPY + " lang:en");
            query.setCount(NUM_OF_RESULTS);
            QueryResult result = twitter.search(query);
            List<Status> tweets_happy = result.getTweets();

            for (Status tweet : tweets_happy) {
                String tweetText = tweet.getText();
                cleanedTweets.add(cleaner.cleanTweet(tweetText) + ",happy");
            }

            query = new Query(SEARCH_PHRASE_SAD + " lang:en");
            query.setCount(NUM_OF_RESULTS);
            result = twitter.search(query);
            List<Status> tweets_sad = result.getTweets();

            for (Status tweet : tweets_sad) {
                String tweetText = tweet.getText();
                cleanedTweets.add(cleaner.cleanTweet(tweetText) + ",sad");
            }


            query = new Query(SEARCH_PHRASE_QUESTION + " lang:en");
            query.setCount(NUM_OF_RESULTS);
            result = twitter.search(query);
            List<Status> tweets_question = result.getTweets();

            for (Status tweet : tweets_question) {
                String tweetText = tweet.getText();
                cleanedTweets.add(cleaner.cleanTweet(tweetText) + ",question");
            }

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }


        /* write to CSV */
        File file = new File("cit-591-recitation-9/output_data1.csv");
        try(PrintWriter writer = new PrintWriter(file)) {
            for (String cleanTweet : cleanedTweets) {
                writer.write(cleanTweet);
                writer.write('\n');
            }
        } catch (Throwable e) {
            System.exit(-1);
        }

    }

}
