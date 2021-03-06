package com.example.akshay.numberapi;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    String number="http://numbersapi.com/",add="/math";
    RadioGroup radioGroup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.CityEditText);
        textView=findViewById(R.id.textView);
        radioGroup=findViewById(R.id.radioGroup);



    }

    public void pressed(View view) {
        int radioid=radioGroup.getCheckedRadioButtonId();
        switch (radioid)
        { case R.id.radio0:
            number ="http://numbersapi.com/";
            add="/math";

            break;
            case R.id.radio2:
                number ="http://numbersapi.com/";
                add ="";
               break;

            case R.id.radio3:
                number ="http://numbersapi.com/";
                add ="/year";
                break;

        }
    }

    public void getweather(View view)

    {
        Weatherinfo weatherinfo =new Weatherinfo();

        try{
            if(editText.getText().toString().isEmpty())
            {
                Toast.makeText(this, "enter the city name", Toast.LENGTH_SHORT).show();
                textView.setText("");
            }else {
                String weatherdetails = weatherinfo.execute(number + editText.getText().toString()+add).get();
                Log.i("get",weatherdetails);

                textView.setText(weatherdetails);

            }
        }catch (Exception e)
        {e.printStackTrace();

        }
    }


    public class Weatherinfo extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.connect();

                InputStream in =httpURLConnection.getInputStream();
                InputStreamReader reader =new InputStreamReader(in);

                int data = reader.read();
                String apidetils = "";
                char current;
                while (data != -1)
                {
                    current =(char) data;
                    apidetils +=current;
                    data=reader.read();

                }
                return apidetils;

            }catch (Exception e)
            {e.printStackTrace();
            }


            return null;
        }


    }
}
