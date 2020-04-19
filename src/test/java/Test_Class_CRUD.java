import api.controller.Executor;
import api.request.SlackChannelPOJO;
import api.support.FakerUtils;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test_Class_CRUD {
    Executor executor = new Executor();
    FakerUtils fakerUtils = new FakerUtils();
    private JsonPath jsonPath;
     SlackChannelPOJO SlackChannelPOJO;
    private Faker faker;
    private String channelPayload;
    private Response response;

    @BeforeTest
    public void setUp() {
        //Initialising all the helper class to support generate data (payload for channel crud activities)
        fakerUtils =  new FakerUtils();
        SlackChannelPOJO = fakerUtils.createChannelPayload();
        faker =  new Faker();
    }

    @Test(priority = 3)
    public void getListOfChannels(){
        response = executor.get("https://slack.com/api/channels.list");
        //Printing the response body for small approval
        System.out.println("Response body is " + response.getBody().asString());
        //Getting the specific value of the response using the key with jsonPath
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("ok"),"true");
    }

    @Test(priority = 0)
    public void createChannel(){

        channelPayload = fakerUtils.prepareChannelPayload(SlackChannelPOJO);
        response = executor.post("https://slack.com/api/conversations.create",channelPayload);

        System.out.println("Response body is " + response.getBody().asString());
        jsonPath = response.jsonPath();
        //Recording the id generated for channel and replacing the payload with id for further CRUD operations
        SlackChannelPOJO.setChannel(jsonPath.getString("channel.id"));
        Assert.assertEquals(jsonPath.getString("ok"),"true");
    }

    @Test(priority = 1)
    public void joiningChannel(){

        channelPayload = fakerUtils.prepareChannelPayload(SlackChannelPOJO);
        response = executor.post("https://slack.com/api/conversations.join",channelPayload);
        System.out.println("Response body of join channel is " + response.getBody().asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("ok"), "true");
    }

    @Test(priority = 2)
    public void renameCreatedChannel(){
        //Changing the payload(name of the channel) created to rename via API
        SlackChannelPOJO.setName(faker.name().firstName().toLowerCase());
        channelPayload = fakerUtils.prepareChannelPayload(SlackChannelPOJO);
        response = executor.post("https://slack.com/api/conversations.rename",channelPayload);
        System.out.println("Response body is " + response.getBody().asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("ok"), "true");
    }

    @Test(priority = 4)
    public void archiveCreatedChannel(){
        //I am archiving the channel which I renamed
        channelPayload = fakerUtils.prepareChannelPayload(SlackChannelPOJO);
        response = executor.post("https://slack.com/api/conversations.archive",channelPayload);
        System.out.println("Response body is " + response.getBody().asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("ok"), "true");
    }

    @Test(priority = 5)
    public void verifyArchivedChannel(){
        //I am trying to join the archived channel. So it would give me error and it verifies the archiving of channel
        channelPayload = fakerUtils.prepareChannelPayload(SlackChannelPOJO);
        response = executor.post("https://slack.com/api/conversations.join",channelPayload);
        System.out.println("Response body is " + response.getBody().asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("error"),"is_archived");

    }

}
