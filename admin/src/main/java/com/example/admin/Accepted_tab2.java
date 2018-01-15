package com.example.admin;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Hrishikesh on 16-12-2017.
 **/

public class Accepted_tab2 extends Fragment {

    ListView lv;
    public Accepted_tab2() {};

    private static final String TAG_RESULTS = "result";
    JSONArray peoples = null;
    public static int cnt, cnt1;

    public static String myJSON,patientNameSend_server="11223344";
    /*tab 1*/  public static String FirstName[],AadharNo[],LastName[],
            Address[],City[],State[],ContactNo[],MiddleName[], Email[],
            Gender[], DOB[], RegTimestamp[], UserID[], Document[],
            VerificationStatus[], VerifierID[], Type[],Description[], ID[];
      private ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_accepted_tab2, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ListView)view.findViewById(R.id.accepted_listview);
        Toast.makeText(getActivity(), "in onViewCreated", Toast.LENGTH_SHORT).show();
        getData();
    }




    //####################################################################################### get data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            HttpPost httppost;
            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

                    //httppost = new HttpPost(State_and_City.url + "pending.php?p_id=" + patientNameSend_server.replace(" ", "%20"));
                httppost = new HttpPost(State_and_City.url + "all_users.php");

                // Depends on your web service
                httppost.setHeader("Content-MiddleName", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                    Toast.makeText(getActivity(), "dd", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // Oops

                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                try {


                    myJSON=result;
                    Toast.makeText(getActivity(), "MyJson: "+myJSON.toString(), Toast.LENGTH_SHORT).show();
                    showList();

                }catch (Exception f){

                    Toast.makeText(getActivity(), "Error Receive: "+f, Toast.LENGTH_SHORT).show();
                }
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            cnt=0;
            cnt1=0;


            for(int i=0;i<peoples.length();i++){
                cnt++;
            }

                AadharNo = new String[cnt];
                FirstName = new String[cnt];
                MiddleName = new String[cnt];
                LastName = new String[cnt];
                Address = new String[cnt];
                City = new String[cnt];
                State = new String[cnt];
                ContactNo = new String[cnt];



            Email = new String[cnt];
            Gender = new String[cnt];
            DOB = new String[cnt];
            RegTimestamp = new String[cnt];
            UserID = new String[cnt];
            Document = new String[cnt];
            VerificationStatus = new String[cnt];
            VerifierID = new String[cnt];
            Type = new String[cnt];
            Description = new String[cnt];
            ID = new String[cnt];


            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                //FirstName[cnt1]=c.getString("FirstName");

                    AadharNo[cnt1] = c.getString("AadharNo");
                    FirstName[cnt1] = c.getString("FirstName");
                    MiddleName[cnt1] = c.getString("MiddleName");
                    LastName[cnt1] = c.getString("LastName");
                    Address[cnt1] = c.getString("Address");
                    City[cnt1] = c.getString("City");
                    State[cnt1] = c.getString("State");
                ContactNo[cnt1] = c.getString("ContactNo");

                Email[cnt1] = c.getString("Email");
                Gender[cnt1] = c.getString("Gender");
                DOB[cnt1] = c.getString("DOB");
                RegTimestamp[cnt1] = c.getString("RegTimestamp");
                UserID[cnt1] = c.getString("UserID");
                Document[cnt1] = c.getString("Document");
                VerificationStatus[cnt1] = c.getString("VerificationStatus");
                VerifierID[cnt1] = c.getString("VerifierID");
                Type[cnt1] = c.getString("Type");
                Description[cnt1] = c.getString("Description");
                ID[cnt1] = c.getString("ID");

                Toast.makeText(getActivity(), "ID "+ID[i], Toast.LENGTH_SHORT).show();

                cnt1++;
            }
            // setTitle(patientNameF[0]+" "+patientNameL[0]);
          //  setTitle("sfse1");
//aa

                layout_accepted a = new layout_accepted(getActivity(), FirstName, LastName, Address, City, State, ContactNo, MiddleName);
                lv.setAdapter(a);


            if(cnt==0) {
                Toast.makeText(getActivity(), "Nothing to show in Cityed", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
