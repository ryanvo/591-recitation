package twitter;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TwitterScraper {

    private final TwitterApiConfig config;

    public TwitterScraper(TwitterApiConfig config) {
        this.config = config;
    }


    public void run(TweetCleaner cleaner) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(config.CONSUMER_KEY)
                .setOAuthConsumerSecret(config.CONSUMER_SECRET)
                .setOAuthAccessToken(config.ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(config.ACCESS_TOKEN_SECRET);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        System.out.println("Logged in successfully...");

        /**
         * Change search phrase here
         */
        String SEARCH_PHRASE_HAPPY = ":)";
        String SEARCH_PHRASE_SAD = 	":)";
        String SEARCH_PHRASE_QUESTION = "?";

        int NUM_OF_RESULTS = 50;

        ArrayList<String> currentDataSet = new ArrayList<>();
        try {

            System.out.println("Scraping tweets with :)...");

            for (Status tweet : queryTweets(SEARCH_PHRASE_HAPPY, "happy", NUM_OF_RESULTS, currentDataSet, twitter)) {
                String tweetText = tweet.getText();
                currentDataSet.add(cleaner.cleanTweet(tweetText) + ",happy");
                System.out.println("Happy: " + currentDataSet.get(currentDataSet.size()-1));

            }

            System.out.println("Scraping tweets with :(...");
            for (Status tweet : queryTweets(SEARCH_PHRASE_SAD, "sad", NUM_OF_RESULTS, currentDataSet,
                    twitter)) {
                String tweetText = tweet.getText();
                currentDataSet.add(cleaner.cleanTweet(tweetText) + ",sad");
                System.out.println("Sad: " + currentDataSet.get(currentDataSet.size()-1));

            }

            System.out.println("Scraping tweets with ?...");
            for (Status tweet : queryTweets(SEARCH_PHRASE_QUESTION, "question", NUM_OF_RESULTS, currentDataSet,
                    twitter)) {
                String tweetText = tweet.getText();
                currentDataSet.add(cleaner.cleanTweet(tweetText) + ",question");
                System.out.println("Question: " + currentDataSet.get(currentDataSet.size()-1));
            }

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }

        System.out.printf("Finished scraping tweets... ");
        writeToCsv(currentDataSet);
    }

    private List<Status> queryTweets(String searchPhrase, String sentiment, int numResults,
                                     List<String>
                                             currentDataSet, Twitter twitter) throws TwitterException{
        Query query = new Query(searchPhrase + " lang:en");
        query.setCount(numResults);
        QueryResult result = twitter.search(query);
        return result.getTweets();
    }

    private void writeToCsv(List<String> cleanedTweets) {
        DateFormat dateFormat = new SimpleDateFormat("-HH:mm:ss-MM-dd-yyyy");
        Date date = new Date();

        String fileName = "OUTPUT_DATA" + dateFormat.format(date) + ".csv";
        System.out.println(fileName);

        File file = new File(fileName);
        try(PrintWriter writer = new PrintWriter(file)) {
            for (String cleanTweet : cleanedTweets) {
                writer.println(cleanTweet);
            }
        } catch (Throwable e) {
            System.out.println("Failed to create new file");
            System.exit(-1);
        }

    }

}
