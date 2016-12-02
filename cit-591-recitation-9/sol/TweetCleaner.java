package twitter;
public class TweetCleaner {
    
    String cleanTweet(String dirtyTweet) {

        dirtyTweet = dirtyTweet.replaceAll("@[^ ]+", "");
        dirtyTweet = dirtyTweet.replaceAll("#[^ ]+$?", "");
        dirtyTweet = dirtyTweet.replaceAll("http[^ ]+$?", "");
        dirtyTweet = dirtyTweet.replaceAll("RT[ ]+", "");
        dirtyTweet = dirtyTweet.replaceAll(":\\)", "");
        dirtyTweet = dirtyTweet.replaceAll(":\\(", "");

        dirtyTweet = dirtyTweet.replaceAll("[^a-zA-Z ]", "");
        dirtyTweet = dirtyTweet.toLowerCase();

        String cleanTweet = dirtyTweet.trim();
        return cleanTweet;
    }

}
