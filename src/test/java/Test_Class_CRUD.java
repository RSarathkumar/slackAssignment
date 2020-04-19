import api.controller.Executor;
import api.support.Channel;
import api.support.ChannelUtils;
import com.github.javafaker.Faker;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class Test_Class_CRUD {
    Executor executor = new Executor();
    ChannelUtils channelUtils = new ChannelUtils();
    private JsonPath jsonPath;
     Channel channel;
    private Faker faker;
    private String channelPayload;
    private Response response;

    @Test(priority = 3)
    public void getListOfChannels(){
        response = executor.get("https://slack.com/api/channels.list");
        ResponseBody body = response.getBody();
        System.out.println("Response body of list is " + body.asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 0)
    public void createChannel(){
        String payload="{\"name\":\"checksarathtest23\"}";
        //channelPayload = channelUtils.prepareChannelPayload(channel);
        response = executor.post("https://slack.com/api/conversations.create",payload);
        ResponseBody body = response.getBody();
        System.out.println("Response body is " + body.asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 1)
    public void checkCreatedChannelJoin(){
        String payload="{\"name\":\"checksarathtest23\"}";
        //channelPayload = channelUtils.prepareChannelPayload(channel);
        response = executor.post("https://slack.com/api/conversations.join",payload);
        ResponseBody body = response.getBody();
        System.out.println("Response body of join channel is " + body.asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 2)
    public void renameCreatedChannel(){
        String payload="{\"channel\":\"checksarathtest23\",\"name\":\"checksarathtest33\"}";
        response = executor.post("https://slack.com/api/conversations.rename",payload);
        ResponseBody body = response.getBody();
        System.out.println("Response body of join channel is " + body.asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 4)
    public void archiveCreatedChannel(){
        String payload="{\"name\":\"checksarathtest33\"}";
        response = executor.post("https://slack.com/api/conversations.archive",payload);
        ResponseBody body = response.getBody();
        System.out.println("Response body of join channel is " + body.asString());
        jsonPath = response.jsonPath();
        Assert.assertEquals(response.statusCode(), 200);
    }

}
