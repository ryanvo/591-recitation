import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classifiers;

public class TweetClassifier {

    WatsonJsonParser parser;
    NaturalLanguageClassifier service;

    public TweetClassifier(WatsonJsonParser parser) {

        service = new NaturalLanguageClassifier();
        service.setUsernameAndPassword("076cfacb-bda1-409f-9b05-1d365b68acbf", "NtWf5GK2uw01");
        this.parser = parser;
    }

    public String classify(String tweetToClassify) {
        String idOfJobAlreadyProcessedForTwitterData = "773d7ax121-nlc-26";
        Classification classification = service.classify(idOfJobAlreadyProcessedForTwitterData, tweetToClassify).execute();
        return parser.parse(classification.toString());
    }

    private void run() {

        /**
         * Create a classifying job
         */
        /*
        File trainingData = new File("cit-591-recitation-9/data_larger.csv");
        if (!trainingData.canRead()) {
            System.out.println("cant read");
            System.exit(0);
        }
        Classifier classifier = service.createClassifier("tweet-sentiment-question", "en",
        trainingData).execute();
        System.out.println(classifier);
        */

        Classifiers classifiers = service.getClassifiers().execute();
        System.out.println(classifiers);
    }

}
