package data;

import services.FeedService;


public class Message {
 public String text;
    public enum Sender {USER,BOT};
    public Sender sender;
    public FeedResponse feedResponse;

}
