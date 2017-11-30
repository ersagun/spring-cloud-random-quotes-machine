package com.sfeir.tweetstorage.service;

@Service
public class GoogleSentimentAnalyzer {

    public Float sentimentAnalyzer(String text){
        // Instantiates a client
        Float sentimentScore=0f;
        Float sentimentMagnitude=0f;
        try {
            try (LanguageServiceClient language = LanguageServiceClient.create()) {
                Document doc = Document.newBuilder()
                        .setContent(text).setType(Document.Type.PLAIN_TEXT).build();
                // Detects the sentiment of the text
                Sentiment sentiment = language.analyzeSentiment(doc).getDocumentSentiment();
                System.out.println("created language "+ sentiment);
                if(sentiment==null)
                    System.out.println("sentiment not found");

                System.out.printf("Text: %s%n", text);
                System.out.printf("Sentiment: %s, %s%n", sentiment.getScore(), sentiment.getMagnitude());
                sentimentScore = sentiment.getScore();
                sentimentMagnitude = sentiment.getMagnitude();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sentimentScore;
    }

}
