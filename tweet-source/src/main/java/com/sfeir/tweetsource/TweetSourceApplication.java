package com.sfeir.tweetsource;

import com.sfeir.tweetsource.model.Tweet;
import com.sfeir.tweetsource.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TweetSourceApplication implements CommandLineRunner {


    @Autowired
    private TweetRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TweetSourceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of tweet
        repository.save(new Tweet("I believe that there is a subtle magnetism in Nature, which, if we unconsciously yield to it, will direct us aright. – Henry David Thoreau", "http://channelx94.com/wp-content/uploads/2014/02/great-outdoors.jpg"));

        repository.save(new Tweet("In the presence of nature, a wild delight runs through the man, in spite of real sorrows. – Ralph Waldo Emerson", "https://upload.wikimedia.org/wikipedia/commons/0/05/Biskeri-_Camping_I_IMG_7238.jpg"));

        repository.save(new Tweet("Forget not that the earth delights to feel your bare feet and winds long to play with your hair. – Kahli Gibran ", "https://upload.wikimedia.org/wikipedia/commons/7/75/Abandoned_Outdoor_Theater_(5216459217).jpg"));

        repository.save(new Tweet("Keep close to Nature’s heart… and break clear away, once in a while, and climb a mountain or spend a week in the woods. Wash your spirit clean. – John Muir ", "http://cdn1.matadornetwork.com/blogs/1/2014/05/railay-thailand.jpg"));

        repository.save(new Tweet("I go to nature every day for inspiration in the day’s work. – Frank Lloyd Wright", "http://www.iwallhd.com/stock/sunset-forest-mountain-path-ray-shelter-sky.jpg"));

        repository.save(new Tweet("Nature gives to every time and season some beauties of its own. – Charles Dickens", "http://cdn.wall-pix.net/albums/landscapes-natural/00025195.jpg"));

        repository.save(new Tweet("Wilderness is not a luxury but necessity of the human spirit. – Edward Abbey", "http://www.urbansamurai.com/wp-content/uploads/2013/12/sunset1.jpg"));

        repository.save(new Tweet("Choose only one master – Nature – Rembrandt", "https://www.nrcc.org/wp-content/uploads/2015/02/Sunset.jpg"));

        repository.save(new Tweet("The richness I achieve comes from Nature, the source of my inspiration. – Claude Monet", "http://bwalles.com/wp-content/uploads/2013/08/Rapids-and-Waterfall-Image.jpg"));

        repository.save(new Tweet("The sun does not shine for a few trees and flowers, but for the wide world’s joy. – Henry Ward Beecher", "http://pcwallart.com/images/sunset-forest-hd-wallpaper-2.jpg"));

        repository.save(new Tweet("We do not see nature with our eyes, but with our understandings and our hearts. – William Hazlett", "https://www.amc.edu.au/sites/default/files/Ocean%20wave%20stock%20image_WEB.jpg"));

        repository.save(new Tweet("A foot and lighthearted I take to the open road, healthy, free, the world before me. – Walt Whitman", "http://www.boem.gov/uploadedImages/BOEM/Environmental_Stewardship/NOP/CliffBEACH.jpg?n=1249"));

        repository.save(new Tweet("I go to Nature to be soothed and healed, and to have my senses put together. – John Burroughs", "http://helicalus.com/images/wallpapers/Rapids_mountain_river_-_images_desktop-wallpaper-1440x900.jpg"));

        repository.save(new Tweet("You don’t have to sit outside in the dark. If, however, you want to look at the stars, you will find that darkness is necessary. But the stars neither require nor demand it. – Annie Dillard", "http://www.mrwallpaper.com/wallpapers/mountains-azalea-sunset-2880x1800.jpg"));

        repository.save(new Tweet("The earth has music for those who listen. – William Shakespeare", "https://amazingpict.com/wp-content/uploads/2015/03/desert-mountains-sunset.jpg"));

        repository.save(new Tweet("Look deep into nature, and then you will understand everything better. – Albert Einstein", "https://upload.wikimedia.org/wikipedia/commons/d/d1/Mount_Everest_as_seen_from_Drukair2_PLW_edit.jpg"));

        repository.save(new Tweet("Like music and art, love of nature is a common language that can transcend political or social boundaries. – Jimmy Carter", "http://www.outdoorchanneloutfitters.com/wp-content/uploads/2014/10/camping-outdoors.jpg"));

        repository.save(new Tweet("Earth and sky, woods and fields, lakes and rivers, the mountain and the sea, are excellent schoolmasters, and teach of us more than we can ever learn from books. – John Lubbock", "http://onehdwallpaper.com/wp-content/uploads/2015/07/Free-Download-Mountain-Hd-Wallpapers.jpg"));

    }
}

