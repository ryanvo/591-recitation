package com.ibm.watson.language;

import com.ibm.watson.developer_cloud.natural_language_classifier.v1.NaturalLanguageClassifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classifier;
import com.ibm.watson.developer_cloud.natural_language_classifier.v1.model.Classifiers;

import java.io.File;

public class TweetClassifier {

    public static void run(File trainingData) {

        if (!trainingData.canRead()) {
            System.out.println("cant read");
            System.exit(0);
        }

        NaturalLanguageClassifier service = new NaturalLanguageClassifier();
        service.setUsernameAndPassword("076cfacb-bda1-409f-9b05-1d365b68acbf", "NtWf5GK2uw01");


        Classifier classifier = service.createClassifier("tweet-sentiment-question", "en", trainingData).execute();
        System.out.println(classifier);


        classifier = service.getClassifier(classifier.getId()).execute();
        System.out.println(classifier);

//

////        String tweetToClassify = "is the sun gonna be out?";
//        service.deleteClassifier("74100fx119-nlc-184").execute();
//        service.deleteClassifier("698bc2x117-nlc-586").execute();
//        service.deleteClassifier("698bd1x118-nlc-575").execute();
//        service.deleteClassifier("698bd1x118-nlc-601").execute();
//        service.deleteClassifier("698bd1x118-nlc-601").execute();
//        service.deleteClassifier("698bd1x118-nlc-575").execute();
//        service.deleteClassifier("698bc2x117-nlc-586").execute();

//        Classification classification = service.classify(classifier.getId(), tweetToClassify).execute();
//        Classification classification = service.classify("698bd1x118-nlc-633", tweetToClassify).execute();
//        System.out.println(classification);

        Classifiers classifiers = service.getClassifiers().execute();
        System.out.println(classifiers);

    }

    public static void main(String[] args) {

        File trainingData = new File("data_larger.csv");
        TweetClassifier.run(trainingData);

    }
}
