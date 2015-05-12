package com.example.macbookpro.remindeme;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class sign_in extends Activity implements View.OnClickListener {
    Button fetch;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        fetch= (Button) findViewById(R.id.gofin);
        et = (EditText) findViewById(R.id.pass);

        fetch.setOnClickListener(this);
    }

    class task extends AsyncTask<String, String, Void>
    {
        private ProgressDialog progressDialog = new ProgressDialog(sign_in.this);
        InputStream is = null ;
        String result = "";
        protected void onPreExecute() {
            progressDialog.setMessage("please wait...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    task.this.cancel(true);
                }
            });
        }
        @Override
        protected Void doInBackground(String... params) {
            String url_select = "http://davitacare.byethost24.com/demo.php";

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url_select);

            ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(param));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();

                //read content
                is =  httpEntity.getContent();

            } catch (Exception e) {

                Toast.makeText(sign_in.this, "Please Try Again", Toast.LENGTH_LONG).show();
            }
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while((line=br.readLine())!=null)
                {
                    sb.append(line+"\n");
                }
                is.close();
                result=sb.toString();

            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(sign_in.this, "Error converting result", Toast.LENGTH_LONG).show();

            }

            return null;

        }
        protected void onPostExecute(Void v) {

           /* try {
                JSONArray Jarray = new JSONArray(result);
                for(int i=0;i<Jarray.length();i++)
                {
                    JSONObject Jasonobject = null;
                    //text_1 = (TextView)findViewById(R.id.txt1);
                    Jasonobject = Jarray.getJSONObject(i);

                    //get an output on the screen
                    //String id = Jasonobject.getString("id");
                    String name = Jasonobject.getString("name");
                    String db_detail="";

                    if(et.getText().toString().equalsIgnoreCase(name)) {
                        db_detail = Jasonobject.getString("detail");
                        break;
                    }
                    //text_1.append(id+"\t\t"+name+"\t\t"+password+"\t\t"+"\n");

                }
                this.progressDialog.dismiss();

            } catch (Exception e) {
                // TODO: handle exception
                Toast.makeText(sign_in.this, "Error parsing data", Toast.LENGTH_LONG).show();


            }*/
        }
    }

    @Override
    public void onClick(View v) {
        /* TODO Auto-generated method stub
        switch(v.getId()) {
            case R.id.gofin : new task().execute();break;
        }*/

    //if(et.getText().toString()=="1075000579"){
        Intent intent = new Intent(sign_in.this,set_of_alerts.class);
        startActivity(intent);
    //}

    }

}