package t4j;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreaming {

    private TwitterStream getInstance() {
        //  twitter 계정이 있다면 앱을 만들어서 등록
        //  그렇지 않을 시에는 데이터베이스에 있는 정보 등록
        String consumerKey = "xx";
        String consumerSecret = "xx";
        String acessToken = "xx";
        String acessTokenSecret = "xx";

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(acessToken)
                .setOAuthAccessTokenSecret(acessTokenSecret);
        TwitterStreamFactory tf = new TwitterStreamFactory(configurationBuilder.build());
        TwitterStream twitterStream = tf.getInstance();
        return twitterStream;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TwitterStreaming twitter = new TwitterStreaming();
        StatusListener listener = new StatusListener(){
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };
        TwitterStream twitterStream = twitter.getInstance();
        twitterStream.addListener(listener);
        // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
        twitterStream.sample("ko");
    }
}
