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
        System.out.println("Logged in successfully...");

        /**
         * Change search phrase here
         */
        String SEARCH_PHRASE_HAPPY = 	":)";
        String SEARCH_PHRASE_SAD = 	":)";
        String SEARCH_PHRASE_QUESTION = "?";

        int NUM_OF_RESULTS = 50;

        ArrayList<String> currentDataSet = new ArrayList<>();
        try {

            System.out.println("Scraping tweets with :)...");

            for (Status tweet : queryTweets(SEARCH_PHRASE_HAPPY, "happy", 100, currentDataSet, twitter)) {
                String tweetText = tweet.getText();
                currentDataSet.add(cleaner.cleanTweet(tweetText) + ",happy");
                System.out.println("Happy: " + currentDataSet.get(currentDataSet.size()-1));

            }

            System.out.println("Scraping tweets with :(...");
            for (Status tweet : queryTweets(SEARCH_PHRASE_SAD, "sad", 100, currentDataSet,
                    twitter)) {
                String tweetText = tweet.getText();
                currentDataSet.add(cleaner.cleanTweet(tweetText) + ",sad");
                System.out.println("Sad: " + currentDataSet.get(currentDataSet.size()-1));

            }

            System.out.println("Scraping tweets with ?...");
            for (Status tweet : queryTweets(SEARCH_PHRASE_QUESTION, "question", 100, currentDataSet,
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

        System.out.println("Finshied scraiping wteets, now right to fill?");

        writeToCsv(currentDataSet);
        for (String dataEntry : currentDataSet) {
            System.out.println(dataEntry);
        }
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

        String fileName = "OUTPUT_DATA" + dateFormat.format(date).toString() + ".csv";
        System.out.println(fileName);
//        File file = new File("OUTPUT_DATA" + dateFormat.format(date).toString() + ".csv");
        File file = new File("OUTPUT_DATA" + dateFormat.format(date) + ".csv");
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
