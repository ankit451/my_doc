package Utils;

/**
 * Created by Siddhant Choudhary on 21-01-2017.
 */
public class Message {
    private String  message;
    private boolean isSelf;

    public Message() {
    }

    public Message(String message, boolean isSelf) {
        this.message = message;
        this.isSelf = isSelf;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSelf() {
        return isSelf;
    }

    public void setSelf(boolean isSelf) {
        this.isSelf = isSelf;
    }
}
