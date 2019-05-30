package khoapham.ptp.phamtanphat.json1903;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button btnJsonDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJsonDemo = findViewById(R.id.buttonReadJsondemo);
        btnJsonDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Xulyjson().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
            }
        });
    }
    class Xulyjson extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String dulieutrangweb = docNoiDung_Tu_URL(strings[0]);
            return dulieutrangweb;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Doc json
            //1 : Muon doc du lieu the nao ban dem thu bao nhiu the mo
//            chinh la so luong json can phai dinh nghia
            //2 : Thang dau tien phai khoi tao cho tu new Json

            try {
                JSONObject jsonObject = new JSONObject(s);
                String website = jsonObject.getString("website");
                Log.d("BBB",website);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }

}
