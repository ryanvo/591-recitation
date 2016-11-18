public class ClassifierRunner {

    public static void main(String[] args) {

        final String phraseToClassify = "is this a number";

        WatsonJsonParser jsonParser = new WatsonJsonParser();
        TweetClassifier classifier = new TweetClassifier(jsonParser);
        System.out.println(classifier.classify(phraseToClassify));

    }

}
