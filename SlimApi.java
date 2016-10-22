
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface SlimApi {


    @FormUrlEncoded
    @POST(MyConsta.URL_UPDATE_FBT)
    Call<Simple_Response> UpdateFBToken(@Field("token") String token);


}
