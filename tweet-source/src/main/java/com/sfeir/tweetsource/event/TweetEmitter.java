package com.sfeir.tweetsource.event;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import com.sfeir.tweetsource.channel.TweetSourceChannel;
import com.sfeir.tweetsource.service.ScheduledFutureManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import shared.User;

import java.util.logging.Logger;

@EnableBinding(TweetSourceChannel.class)
public class TweetEmitter {

    private static final Logger LOGGER = Logger.getLogger( TweetEmitter.class.getName() );
    private ScheduledFutureManager scheduledFutureManager;


    @Autowired
    public TweetEmitter(ScheduledFutureManager scheduledFutureManager) {
        this.scheduledFutureManager = scheduledFutureManager;
    }

    @StreamListener(target = TweetSourceChannel.INPUT, condition = "headers['type']=='create-user'")
    public void createUserConnection(User user) {
        this.scheduledFutureManager.createUserThreadAndAddToList(user);
    }

    @StreamListener(target = TweetSourceChannel.INPUT, condition = "headers['type']=='stop-user'")
    public void stopTweet(User user) {
        this.scheduledFutureManager.stopUserThread(user);
    }









/*
    @InboundChannelAdapter(channel = TweetSourceChannel.OUTPUT, poller = @Poller(fixedRate = "1000"))
    public Tweet emitTweetData() {
        System.out.println("started");
        // The factory instance is re-useable and thread safe.
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query("track:black friday");
        QueryResult result = null;
        try {
            result = twitter.search(query);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        }
        System.out.println("end");
        return new Tweet(0,"hello",1,"blabla",10, new User());
    }
*/


/*
    @Override
    public void run(String... args) throws Exception {



          StatusListener statusListener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                Tweet tweet = new Tweet(counter.getAndIncrement(),
                        (status.getUser().getName() != null) ? status.getUser().getName() : "",
                        status.getUser().getFavouritesCount(),
                        (status.getText() != null) ? status.getText() : "",
                        status.getUser().getFollowersCount(),
                        new User()
                );
                System.out.println(tweet);
                tweetSourceChannel.output().send(MessageBuilder.withPayload(tweet).build());

                //  repository.save(new TweetStatus(status));

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onStallWarning(StallWarning warning) {

            }

            @Override
            public void onException(Exception ex) {

            }
        };
        TwitterStreamFactory fact = new TwitterStreamFactory();
        TwitterStream twitterStream = fact.getInstance();
        //FilterQuery tweetFilterQuery = new FilterQuery();
        //tweetFilterQuery.track(new String[]{"black friday"});
        //tweetFilterQuery.language(new String[]{"fr"});
        //twitterStream.addListener(statusListener);
        //twitterStream.filter(tweetFilterQuery);
        twitterStream.sample();


    } */



   /* @Override
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
    }*/


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


/*    @StreamListener(TweetSourceChannel.INPUTUSER)
    public void logTweetStatistics(User usr) {
        System.out.println(usr);
        // The factory instance is re-useable and thread safe.
        Query query = new Query("source:"+usr.getSearchQuery());
        QueryResult result = null;
        try {
            result = twitter.search(query);
            System.out.println("res : " + result);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        for (Status status : result.getTweets()) {
            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            Tweet tweet = new Tweet(counter.getAndIncrement(),
                    (status.getUser().getName() != null) ? status.getUser().getName() : "",
                    status.getUser().getFavouritesCount(),
                    (status.getText() != null) ? status.getText() : "",
                    status.getUser().getFollowersCount(),
                    usr
            );
            System.out.println(tweet);
            tweetSourceChannel.output().send(MessageBuilder.withPayload(tweet).build());

        }
    }*/
}

