import twitter.TweetCleaner;
import twitter.TwitterApiConfig;
import twitter.TwitterScraper;

public class ScrapeRunner {

    public static void main(String[] args) {
        
        /* Implement cleanTweet method */
        TweetCleaner cleaner = new TweetCleaner();
        TwitterScraper scraper = new TwitterScraper(new TwitterApiConfig());

        /* this will create a csv file above the src dir */
        scraper.run(cleaner);

    }

}
