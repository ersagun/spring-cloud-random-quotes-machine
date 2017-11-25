package com.sfeir.tweetsource.repository;

import com.sfeir.tweetsource.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TweetRepository extends MongoRepository<Tweet, String> {
    public Tweet findByWords(String words);

    public List<Tweet> findByPict(String pict);
}
