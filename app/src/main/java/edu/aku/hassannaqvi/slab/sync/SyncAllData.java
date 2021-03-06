package edu.aku.hassannaqvi.slab.sync;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import edu.aku.hassannaqvi.slab.core.DatabaseHelper;

public class SyncAllData extends AsyncTask<Void, Void, String> {

    private String TAG = "";
    private Context mContext;
    private ProgressDialog pd;


    private String syncClass;
    private URL url;
    private String updateSyncClass;
    private Class contractClass;
    private Collection dbData;
    private TextView syncStatus;


    public SyncAllData(Context context, String syncClass, String updateSyncClass, Class contractClass, URL url, Collection dbData, View v) {
        mContext = context;
        this.syncClass = syncClass;
        this.updateSyncClass = updateSyncClass;
        this.contractClass = contractClass;
        this.url = url;
        this.dbData = dbData;
        //this.syncStatus = (TextView) syncStatus;
        TAG = "Get" + syncClass;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(mContext);
        pd.setTitle("Syncing " + syncClass);
        pd.setMessage("Getting connected to server...");
        pd.show();
        //syncStatus.setText(syncStatus.getText() + "\r\nSyncing " + syncClass);
    }


    @Override
    protected String doInBackground(Void... params) {
        Log.d(TAG, "doInBackground: URL " + url);
        return downloadUrl(contractClass);
    }

    private String downloadUrl(Class<?> contractClass) {
        String line = "No Response";

        Collection<?> DBData = dbData; // pass data that's coming from db

        Log.d(TAG, String.valueOf(DBData.size()));

        if (DBData.size() > 0) {

            HttpURLConnection connection = null;
            try {

                // Load CAs from an InputStream
                // (could be from a resource or ByteArrayInputStream or ...)
//                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                // From https://www.washington.edu/itconnect/security/ca/load-der.crt
//                InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
//                Certificate ca;
//                try {
//                    ca = cf.generateCertificate(caInput);
//                    System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
//                } finally {
//                    caInput.close();
//                }

                // Create a KeyStore containing our trusted CAs
               /* String keyStoreType = KeyStore.getDefaultType();
                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", ca);*/

                // Create a TrustManager that trusts the CAs in our KeyStore
//                String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//                TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//                tmf.init(keyStore);

//                // Create an SSLContext that uses our TrustManager
//                SSLContext context = SSLContext.getInstance("TLS");
//                context.init(null, tmf.getTrustManagers(), null);

                URL request = url;
                URL url = request;
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                int HttpResult = connection.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    JSONArray jsonSync = new JSONArray();
                    connection = (HttpURLConnection) url.openConnection();

                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setReadTimeout(100000 /* milliseconds */);
                    connection.setConnectTimeout(150000 /* milliseconds */);
                    connection.setInstanceFollowRedirects(false);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("charset", "utf-8");

                    connection.setUseCaches(false);
                    connection.connect();

                    DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

                    try {
                        while (contractClass != null) {
                            for (Method method : contractClass.getDeclaredMethods()) {
                                String methodName = method.getName();
                                if (methodName.equals("toJSONObject")) {
                                    for (Object fc : DBData) {
                                        jsonSync.put(fc.getClass().getMethod(methodName).invoke(fc));
                                    }
                                    break;
                                }
                            }
                            break;
                        }

                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                    wr.writeBytes(jsonSync.toString().replace("\uFEFF", "") + "\n");
                    wr.flush();


                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            connection.getInputStream(), StandardCharsets.UTF_8));
                    StringBuffer sb = new StringBuffer();

                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();

                    System.out.println("" + sb.toString());
                    return sb.toString();
                } else {
                    System.out.println(connection.getResponseMessage());
                    return connection.getResponseMessage();
                }
            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
            }
        } else {
            return "No new records to sync";
        }
        return line;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        int sSynced = 0;
        int sDuplicate = 0;
        String sSyncedError = "";
        JSONArray json = null;
        Log.d(TAG, "onPostExecute: " + result);
        try {
            json = new JSONArray(result);

            DatabaseHelper db = new DatabaseHelper(mContext); // Database Helper

            Method method = null;
            for (Method method1 : db.getClass().getDeclaredMethods()) {
                if (method1.getName().equals(updateSyncClass)) {
                    method = method1;
                    break;
                }
            }

            for (int i = 0; i < json.length(); i++) {
                JSONObject jsonObject = new JSONObject(json.getString(i));

                if (jsonObject.getString("status").equals("1") && jsonObject.getString("error").equals("0")) {

                    method.invoke(db, jsonObject.getString("id"));

                    sSynced++;
                } else if (jsonObject.getString("status").equals("2") && jsonObject.getString("error").equals("0")) {

                    method.invoke(db, jsonObject.getString("id"));

                    sDuplicate++;
                } else {
                    sSyncedError += "\nError: " + jsonObject.getString("message");
                }
            }
            Toast.makeText(mContext, syncClass + " synced: " + sSynced + "\r\n\r\n Errors: " + sSyncedError, Toast.LENGTH_SHORT).show();


            pd.setMessage(syncClass + " synced: " + sSynced + "\r\n\r\n Duplicates: " + sDuplicate + "\r\n\r\n Errors: " + sSyncedError);
            pd.setTitle("Done uploading +" + syncClass + " data");
            pd.show();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "Failed Sync " + result, Toast.LENGTH_SHORT).show();

            pd.setMessage(result);
            pd.setTitle(syncClass + " Sync Failed");
            pd.show();
            //syncStatus.setText(syncStatus.getText() + "\r\n" + syncClass + " Sync Failed");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}