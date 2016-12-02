package watson;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classification;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classifiers;

import java.io.File;

public class TweetClassifier {

    WatsonJsonParser parser;
    NaturalLanguageClassifier service;

    public TweetClassifier(WatsonApiConfig config, WatsonJsonParser parser) {

        service = new NaturalLanguageClassifier();
        service.setUsernameAndPassword(config.username, config.password);
        this.parser = parser;
    }

    /**
     * Create a classifying job
     */
    public void createJob(String pathToScrapedDataCsv) {

        File trainingData = new File(pathToScrapedDataCsv);
        if (!trainingData.canRead()) {
            System.out.println("cant read");
            System.exit(0);
        }
        Classifier classifier = service.createClassifier("tweet-sentiment-question", "en",
                trainingData).execute();
        System.out.println(classifier);
    }

    /**
     * Once classifying job is complete (several hours), use printRunningJobs below to get the "classifier_id" field
     * and use that to ask Watson for classifications based on your training data.
     */
    public String classify(String tweetToClassify, String idOfJobAlreadyTrainedWithTwitterData) {

        Classification classification = service.classify(idOfJobAlreadyTrainedWithTwitterData, tweetToClassify).execute();
        return parser.parse(classification.toString());
    }

    public void printRunningJobs() {
        Classifiers classifiers = service.getClassifiers().execute();
        System.out.println(classifiers);
    }



}
