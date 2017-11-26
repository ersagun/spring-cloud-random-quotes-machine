package com.sfeir.tweetstorage.event;

import com.qkyrie.markdown2pdf.internal.exceptions.ConversionException;
import com.qkyrie.markdown2pdf.internal.exceptions.Markdown2PdfLogicException;
import com.sfeir.tweetstorage.channel.TweetStorageChannel;
import com.sfeir.tweetstorage.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import shared.Translation;
import shared.Tweet;
import shared.TweetStatistics;

import java.io.IOException;
import java.util.UUID;

@EnableBinding(TweetStorageChannel.class)
public class TweetStore {


    private StoreService storeService;

    @Autowired
    public TweetStore(StoreService storeService) {
        this.storeService = storeService;
    }


    @StreamListener(TweetStorageChannel.INPUT)
    public void logTweetStatistics(TweetStatistics tweetStatistics) {
        System.out.println(tweetStatistics);
       /* String content = storeService.tweetMdGenerator(tweet);
        UUID name = UUID.randomUUID();
        try {
            try {
                storeService.createAndSaveMdAsPdf(content, "/Users/Ersagun/Documents/Doc_Storage/" + name + ".html");
            } catch (Markdown2PdfLogicException | ConversionException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }



/*
    @StreamListener(target = TweetStorageChannel.INPUT, condition = "headers['type']!='translation' && headers['type']!='wikipedia'")
    public void logTweet(Tweet tweet) {
        String content = storeService.tweetMdGenerator(tweet);
        UUID name = UUID.randomUUID();
        try {
            try {
                storeService.createAndSaveMdAsPdf(content, "/Users/Ersagun/Documents/Doc_Storage/" + name + ".html");
            } catch (Markdown2PdfLogicException | ConversionException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/


/*    @StreamListener(target=TweetStorageChannel.INPUT,condition="headers['type']=='wikipedia'")
    public void logWiki(Wiki wiki) {
        String content =storeService.tweetMdGenerator(tweet);
        UUID name = UUID.randomUUID();
        try {
            try {
                storeService.createAndSaveMdAsPdf(content,"/Users/Ersagun/Documents/Doc_Storage/"+name+".html");
            } catch (Markdown2PdfLogicException e) {
                e.printStackTrace();
            } catch (ConversionException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


/*    @StreamListener(target = TweetStorageChannel.INPUT, condition = "headers['type']=='translation'")
    public void logTranslation(Translation translate) {
        System.out.println("Translate :" + translate);
        String content = storeService.translateMdGenerator(translate);
        UUID name = UUID.randomUUID();
        try {
            try {
                storeService.createAndSaveMdAsPdf(content, "/Users/Ersagun/Documents/Doc_Storage/" + name + "-" + translate.getLang() + ".html");
            } catch (Markdown2PdfLogicException | ConversionException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
