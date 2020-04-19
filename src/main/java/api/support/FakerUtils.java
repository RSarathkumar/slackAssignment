package api.support;


import api.request.SlackChannelPOJO;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FakerUtils {
	
	Faker faker = new Faker();
	api.request.SlackChannelPOJO SlackChannelPOJO;
	
	public String prepareChannelPayload() {
		SlackChannelPOJO SlackChannelPOJO = createChannelPayload();
		Gson gson = new GsonBuilder().create();
		String payLoadAsString = gson.toJson(SlackChannelPOJO);
        return payLoadAsString;
	}
	
	public String prepareChannelPayload(SlackChannelPOJO SlackChannelPOJO) {
		Gson gson = new GsonBuilder().create();
		String payLoadAsString = gson.toJson(SlackChannelPOJO);
        return payLoadAsString;
	}
	
	public SlackChannelPOJO createChannelPayload() {
		SlackChannelPOJO = new SlackChannelPOJO(faker.name().firstName().toLowerCase());
		return SlackChannelPOJO;
	}
}
