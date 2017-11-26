package com.sfeir.tweetsource.event;

import com.sfeir.tweetsource.channel.TweetSourceChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import shared.Tweet;
import twitter4j.*;

import java.util.concurrent.atomic.AtomicInteger;

@EnableBinding(TweetSourceChannel.class)
public class TweetEmitter implements CommandLineRunner {

    private TweetSourceChannel tweetSourceChannel;
    private AtomicInteger counter;

    @Autowired
    public TweetEmitter(TweetSourceChannel tweetSourceChannel) {
        this.tweetSourceChannel = tweetSourceChannel;
        this.counter= new AtomicInteger();
    }


    @Override
    public void run(String... args) throws Exception {
        UserStreamListener userStreamListener = new UserStreamListener() {
            @Override
            public void onDeletionNotice(long l, long l1) {

            }

            @Override
            public void onFriendList(long[] longs) {

            }

            @Override
            public void onFavorite(User user, User user1, Status status) {

            }

            @Override
            public void onUnfavorite(User user, User user1, Status status) {

            }

            @Override
            public void onFollow(User user, User user1) {

            }

            @Override
            public void onUnfollow(User user, User user1) {

            }

            @Override
            public void onDirectMessage(DirectMessage directMessage) {

            }

            @Override
            public void onUserListMemberAddition(User user, User user1, UserList userList) {

            }

            @Override
            public void onUserListMemberDeletion(User user, User user1, UserList userList) {

            }

            @Override
            public void onUserListSubscription(User user, User user1, UserList userList) {

            }

            @Override
            public void onUserListUnsubscription(User user, User user1, UserList userList) {

            }

            @Override
            public void onUserListCreation(User user, UserList userList) {

            }

            @Override
            public void onUserListUpdate(User user, UserList userList) {

            }

            @Override
            public void onUserListDeletion(User user, UserList userList) {

            }

            @Override
            public void onUserProfileUpdate(User user) {

            }

            @Override
            public void onUserSuspension(long l) {

            }

            @Override
            public void onUserDeletion(long l) {

            }

            @Override
            public void onBlock(User user, User user1) {

            }

            @Override
            public void onUnblock(User user, User user1) {

            }

            @Override
            public void onRetweetedRetweet(User user, User user1, Status status) {

            }

            @Override
            public void onFavoritedRetweet(User user, User user1, Status status) {

            }

            @Override
            public void onQuotedTweet(User user, User user1, Status status) {

            }

            @Override
            public void onStatus(Status status) {
                Tweet tweet = new Tweet(counter.getAndIncrement(),
                        (status.getUser().getName() != null) ? status.getUser().getName() : "",
                        status.getUser().getFavouritesCount(),
                        (status.getText() != null) ? status.getText() : "",
                        status.getUser().getFollowersCount()
                );
                System.out.println(tweet);
                tweetSourceChannel.output().send(MessageBuilder.withPayload(tweet).build());

                //  repository.save(new TweetStatus(status));

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //   System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }


            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                // System.out.println("Got a track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                //  System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
                //   System.out.println("onException:" + ex.getMessage());
            }
        };
        TwitterStreamFactory fact = new TwitterStreamFactory();
        TwitterStream twitterStream = fact.getInstance();
        FilterQuery tweetFilterQuery = new FilterQuery();
        tweetFilterQuery.track(new String[]{"black friday"});
        tweetFilterQuery.language(new String[]{"fr"});
        twitterStream.addListener(userStreamListener);
        twitterStream.filter(tweetFilterQuery);
        //twitterStream.sample();
    }


    /*
    @InboundChannelAdapter(channel = TweetSourceChannel.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public TweetStatus emitTweetData() {
        Pageable pageable = new PageRequest(new Random().nextInt(toIntExact(repository.count())), 1);
        Page<TweetStatus> pt = repository.findAll(pageable);
        List<TweetStatus> list = pt.getContent();
        System.out.println(list.get(new Random().nextInt(list.size())));
        return list.get(0);
    }*/


/*
    @InboundChannelAdapter(channel = TweetSourceChannel.OUTPUT, poller = @Poller(fixedRate = "10"))
    public Tweet emitTweetData() {
        System.out.println("sended");
        return new Tweet(0,"hello",1,"blabla",10);
    }*/

}
