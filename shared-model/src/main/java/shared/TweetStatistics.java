package shared;

public class TweetStatistics {

    private int numberOfTweet;
    private int averageNumberOfCharacterByTweet;
    private int averageAge;

    public TweetStatistics() {
    }

    public TweetStatistics(int numberOfTweet, int averageNumberOfCharacterByTweet, int averageAge) {
        this.numberOfTweet = numberOfTweet;
        this.averageNumberOfCharacterByTweet = averageNumberOfCharacterByTweet;
        this.averageAge = averageAge;
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

    @Override
    public String toString() {
        return "TweetStatistics{" +
                "numberOfTweet=" + numberOfTweet +
                ", averageNumberOfCharacterByTweet=" + averageNumberOfCharacterByTweet +
                ", averageAge=" + averageAge +
                '}';
    }
}
