package com.sfeir.tweetstorage.service;


import com.qkyrie.markdown2pdf.internal.exceptions.ConversionException;
import com.qkyrie.markdown2pdf.internal.exceptions.Markdown2PdfLogicException;
import com.sfeir.tweetstorage.model.Translation;
import com.sfeir.tweetstorage.model.Tweet;
import net.steppschuh.markdowngenerator.image.Image;
import net.steppschuh.markdowngenerator.text.Heading;
import org.markdown4j.Markdown4jProcessor;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StoreService {

    public StoreService() {
    }


    public String tweetMdGenerator(Tweet tweet) {
        StringBuilder sb = new StringBuilder()
                .append(new Heading("Tweet Picture", 1)).append("\n")
                .append(new Heading(new Image("Picture: ", tweet.getPict()), 2)).append("\n")
                .append(new Heading("Tweet Words", 1)).append("\n")
                .append(new Heading(tweet.getWords(), 2));
        System.out.println(sb.toString());
        return sb.toString();

    }

    public String translateMdGenerator(Translation translation) {
        StringBuilder sb = new StringBuilder()
                .append(new Heading("Code:", 1)).append("\n")
                .append(new Heading(translation.getCode(), 2)).append("\n")
                .append(new Heading("Langue:", 1)).append("\n")
                .append(new Heading(translation.getLang(), 2)).append("\n")
                .append(new Heading("Text", 1)).append("\n")
                .append(new Heading(translation.getText()[0], 2));
        System.out.println(sb.toString());
        return sb.toString();
    }


    public void createAndSaveMdAsPdf(String content, String path) throws Markdown2PdfLogicException, ConversionException, IOException {

        Markdown4jProcessor processor = new Markdown4jProcessor();
        String out = processor.process(content);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(out.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
