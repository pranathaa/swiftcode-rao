package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.FeedResponse;
import data.Message;
import data.NewsAgentResponse;
import services.FeedService;
import services.NewsAgentService;

import java.util.UUID;

import static data.Message.Sender.BOT;
import static data.Message.Sender.USER;

public class MessageActor extends UntypedActor {
    public static Props props(ActorRef out) {
        return Props.create(MessageActor.class, out);
    }

    public MessageActor(ActorRef out) {
        this.out = out;
    }

    private final ActorRef out;
    private FeedService feedService = new FeedService();
    private NewsAgentService newsAgentService = new NewsAgentService();
    private FeedResponse feedResponse=new FeedResponse();
    public ObjectMapper objectMapper = new ObjectMapper();
    private NewsAgentResponse newsAgentResponse=new NewsAgentResponse();
    @Override
    public void onReceive(Object message) throws Throwable {
        Message messageObject = new Message();

        if (message instanceof String) {

            messageObject.text = message.toString();
            messageObject.sender = USER;
            out.tell(objectMapper.writeValueAsString(messageObject), self());
            newsAgentResponse=newsAgentService.getNewsAgentResponse(messageObject.text,UUID.randomUUID());
            feedResponse=feedService.getFeedByQuery(newsAgentResponse.query);
            messageObject.text=(feedResponse.title == null) ? "No results found" : "Showing results for: " + newsAgentResponse.query;
            messageObject.feedResponse=feedResponse;
            messageObject.sender=BOT;
            out.tell(objectMapper.writeValueAsString(messageObject), self());
        }
    }
}