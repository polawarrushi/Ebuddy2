package gsheets1.parser;
import android.util.Log;

import com.android.volley.Response;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class JSONParser {

    private static final String MAIN_URL = "https://script.google.com/macros/s/AKfycbx-SMcJJacLPQMj-1N7d1WnICI89WSAXCNQoqmb9iCBg1iCqcp9/exec?id=1C_ij3vFJyyU5In_7EBGQY4FWHCWmr9dgGZ1eqTNgcz4&sheet=Sheet1";
    public static final String TAG = "TAG";
    private static final String KEY_USER_ID = "user_id";
    private static Response response;
    public static JSONObject getDataFromWeb() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException | JSONException e){
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }

    public static JSONObject getDataById(int userId) {

        try {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormEncodingBuilder()
                    .add(KEY_USER_ID, Integer.toString(userId))
                    .build();

            Request request = new Request.Builder()
                    .url(MAIN_URL)
                    .post(formBody)
                    .build();

            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());

        } catch (IOException | JSONException e) {

        }
        return null;
    }
}