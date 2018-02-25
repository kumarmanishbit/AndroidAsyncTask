package com.example.android.restful;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.output);

    }

    public void runClickHandler(View view) {
//        output.append("Button clicked\n");
        // Each time we click the button we create a new instance of the asynctask object.
        MyAsyncTask task = new MyAsyncTask();
        task.execute("String 1", "String 2", "String 3");

    }

    public void clearClickHandler(View view) {
        output.setText("");
    }

    // This architecture is a wrapper around background thread and its tightly bound to an activites life cycle.
    private class MyAsyncTask extends AsyncTask<String, String, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            for (String string: strings) {
                // This will call onProgressUpdate on foreground thread. As it runs on foreground it will have access to all the widget on UI, and it can update anything there.
                publishProgress(string);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            output.append(values[0] + "\n");
        }

        // This method does not receive any arguments and it can setup the app before running the task.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // This recevies the value for the last generic in asynctask extension declaration. it will be like receving response from network call.
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
