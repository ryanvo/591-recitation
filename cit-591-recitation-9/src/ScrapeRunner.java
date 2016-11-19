public class ScrapeRunner {

    public static void main(String[] args) {
        
        /* Implement cleanTweet method */
        TweetCleaner cleaner = new TweetCleaner();
        TwitterScraper scraper = new TwitterScraper();

        /* this will create a csv file above the src dir */
        scraper.run(cleaner);


    }

}
