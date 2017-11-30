package shared;

public class TweetStatistics {

    private int numberOfTweet;
    private int averageNumberOfCharacterByTweet;
    private int averageAge;
    private User user;


    public TweetStatistics() {
    }

    public TweetStatistics(int numberOfTweet, int averageNumberOfCharacterByTweet, int averageAge, User user) {
        this.numberOfTweet = numberOfTweet;
        this.averageNumberOfCharacterByTweet = averageNumberOfCharacterByTweet;
        this.averageAge = averageAge;
        this.user = user;
    }

    public int getNumberOfTweet() {
        return numberOfTweet;
    }

    public void setNumberOfTweet(int numberOfTweet) {
        this.numberOfTweet = numberOfTweet;
    }

    public int getAverageNumberOfCharacterByTweet() {
        return averageNumberOfCharacterByTweet;
    }

    public void setAverageNumberOfCharacterByTweet(int averageNumberOfCharacterByTweet) {
        this.averageNumberOfCharacterByTweet = averageNumberOfCharacterByTweet;
    }

    public int getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(int averageAge) {
        this.averageAge = averageAge;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TweetStatistics{" +
                "numberOfTweet=" + numberOfTweet +
                ", averageNumberOfCharacterByTweet=" + averageNumberOfCharacterByTweet +
                ", averageAge=" + averageAge +
                ", user=" + user +
                '}';
    }
}
