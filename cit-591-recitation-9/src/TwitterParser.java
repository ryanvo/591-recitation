import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TwitterParser {

    /* Fill these fields in! */
    final static String CONSUMER_KEY = "qyC2A2qwQAbHLkIDfZok8YrdY";
    final static String CONSUMER_SECRET = "YsDr7Wol8Ou21CEedMi1UEBtkjDZtkgo1A88TVoGAgVNlLNU9D";
    final static String ACCESS_TOKEN = "37344764-niU57qKXKNHcrI5gt5kVytRlAC0Uk0i0uswiMY60L";
    final static String ACCESS_TOKEN_SECRET = "n4y0jt7p9mKHNScBNgbj97cdnlQYk56OzQZz4IubckzWs";


    public static void main(String[] args) {

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
        String SEARCH_PHRASE = 	":)";
        int NUM_OF_RESULTS = 5;

        ArrayList<String> cleanedTweets = new ArrayList<>();

        try {
            Query query = new Query(SEARCH_PHRASE + " lang:en");
            query.setCount(NUM_OF_RESULTS);
            QueryResult result;

//            do {

                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {

//                    System.out.println(tweet.getText());



                    String tweetText = tweet.getText();

                    System.out.println(tweetText);

                    tweetText = tweetText.replaceAll("@[^ ]+", "");
                    tweetText = tweetText.replaceAll("#[^ ]+$?", "");
                    tweetText = tweetText.replaceAll("http[^ ]+$?", "");
                    tweetText = tweetText.replaceAll(":\\(", "");
                    tweetText = tweetText.replaceAll("RT[ ]+", "");
                    tweetText = tweetText.replaceAll("[^a-zA-Z ]", "");

                    tweetText = tweetText.trim();

                    System.out.println(tweetText);

                    cleanedTweets.add(tweetText.toLowerCase() + ",happy");
                }

//            } while ((query = result.nextQuery()) != null);


//            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }

        for (String t : cleanedTweets) {
            System.out.println(t);
        }

    }
}
