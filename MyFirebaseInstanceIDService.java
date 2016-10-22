
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    @Inject
    MySharedPreferences mMyPref;
    @Inject
    Retrofit retrofit;

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */

    @Override
    public void onCreate() {
        super.onCreate();
        ((MyApp) getApplication()).getNetComponent().inject(this);
    }

    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Logwtf(MyConsta.TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     * <p>
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {

            Call<Simple_Response> UPDATE_FBT = retrofit.create(SlimApi.class).UpdateFBToken(token);
            UPDATE_FBT.enqueue(new Callback<Simple_Response>() {
                @Override
                public void onResponse(Call<Simple_Response> call, Response<Simple_Response> response) {
                    if (response.code() == 200) {
                        if (response != null) {

                            if (response.body().error.equals(false)) {

                                Log.d("sendRegistrationToServer","FBT Updated");

                            } else {
                                Log.d("sendRegistrationToServer", "FBT Updated faild");
                                
                            }
                        }

                    } else if (response.code() == 201) {

                        Logwtf(" sendRegistrationToServer", response.headers().toString() + "\n" + response.raw() + "\n Response msg" + response.body().message.toString());

                        Toast.makeText(MyFirebaseInstanceIDService.this, "Invalid Response From Server", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MyFirebaseInstanceIDService.this, "Invalid Response", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Simple_Response> call, Throwable t) {
                    Logwtf("onFailure", t.getCause().toString());
                }
            });

    }


}
