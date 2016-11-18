public class ScrapeRunner {

    public static void main(String[] args) {
        
        /* Implement cleanTweet method */
        TweetCleaner cleaner = new TweetCleaner();
        TwitterScraper parser = new TwitterScraper();

        /* this will create a csv file above the src dir */
        parser.run(cleaner);

    }

}
