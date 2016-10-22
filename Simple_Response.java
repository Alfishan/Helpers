
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 28/6/16.
 */
public class Simple_Response {

    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("error")
    @Expose
    public Boolean error;

}
