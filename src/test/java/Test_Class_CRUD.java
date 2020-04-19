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
        fakerUtils =  new FakerUtils();
        SlackChannelPOJO = fakerUtils.createChannelPayload();
        faker =  new Faker();
    }

    @Test(priority = 3)
    public void getListOfChannels(){
        response = executor.get("https://slack.com/api/channels.list");
        System.out.println("Response body is " + response.getBody().asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("ok"),"true");
    }

    @Test(priority = 0)
    public void createChannel(){

        channelPayload = fakerUtils.prepareChannelPayload(SlackChannelPOJO);
        response = executor.post("https://slack.com/api/conversations.create",channelPayload);

        System.out.println("Response body is " + response.getBody().asString());
        jsonPath = response.jsonPath();
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
        SlackChannelPOJO.setName(faker.name().firstName().toLowerCase());
        channelPayload = fakerUtils.prepareChannelPayload(SlackChannelPOJO);
        response = executor.post("https://slack.com/api/conversations.rename",channelPayload);
        System.out.println("Response body is " + response.getBody().asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("ok"), "true");
    }

    @Test(priority = 4)
    public void archiveCreatedChannel(){

        channelPayload = fakerUtils.prepareChannelPayload(SlackChannelPOJO);
        response = executor.post("https://slack.com/api/conversations.archive",channelPayload);
        System.out.println("Response body is " + response.getBody().asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("ok"), "true");
    }

    @Test(priority = 5)
    public void verifyArchivedChannel(){

        channelPayload = fakerUtils.prepareChannelPayload(SlackChannelPOJO);
        response = executor.post("https://slack.com/api/conversations.join",channelPayload);
        System.out.println("Response body is " + response.getBody().asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getString("error"),"is_archived");

    }

}
