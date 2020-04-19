package api.request;


public class slackChannel {

    private String name;
    private boolean validate;
    private String channel;

    public slackChannel(String channelName) {
        this.name = channelName;
        this.validate = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
