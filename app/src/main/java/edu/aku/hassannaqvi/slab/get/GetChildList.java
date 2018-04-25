package edu.aku.hassannaqvi.slab.get;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import edu.aku.hassannaqvi.slab.contracts.ChildListContract;
import edu.aku.hassannaqvi.slab.contracts.ChildListContract.ChildListTable;
import edu.aku.hassannaqvi.slab.core.DatabaseHelper;
import edu.aku.hassannaqvi.slab.core.MainApp;

/**
 * Created by ramsha.ahmed on 4/25/2018.
 */

public class GetChildList extends AsyncTask<String, String, String> {
    private final String TAG = "GetChildList()";
    HttpURLConnection urlConnection;
    private Context mContext;
    private ProgressDialog pd;

    public GetChildList(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(mContext);
        pd.setTitle("Syncing Child List");
        pd.setMessage("Getting connected to server...");
        pd.show();

    }

    @Override
    protected void onPostExecute(String s) {
        String json = s;
        Log.d(TAG, s);
        if (json.length() > 0) {
            DatabaseHelper db = new DatabaseHelper(mContext);
            try {
                JSONArray jsonArray = new JSONArray(json);
                db.syncChildList(jsonArray);
                pd.setMessage("Received: " + jsonArray.length());
                pd.show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            pd.setMessage("Received: " + json.length() + "");
            pd.show();
        }
    }

    @Override
    protected String doInBackground(String... strings) {

        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(MainApp._HOST_URL + ChildListTable._URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    Log.i(TAG, "Childlist In: " + line);
                    result.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }


        return result.toString();
    }
}
