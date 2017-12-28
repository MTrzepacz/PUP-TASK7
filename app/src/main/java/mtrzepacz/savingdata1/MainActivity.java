package mtrzepacz.savingdata1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences appData;
    Integer Counter = 0;
    TextView displayData,displayCounter;
    String displayDataString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayData = (TextView) findViewById(R.id.textView2);
        displayCounter = (TextView) findViewById(R.id.textView);
        //Setting scroll for textView
        displayData.setMovementMethod(new ScrollingMovementMethod());

        //Creating sharedPreferences
        SharedPreferences appData = getSharedPreferences("Data",0);

        //setting initial values
        displayDataString = "";
        displayData.setText("");
        displayCounter.setText(Counter.toString());

    }

    public void addData(View v)
    {
        //rise counter
        Counter++;
        //get SharepdPreferences and update them
        SharedPreferences appData = getSharedPreferences("Data",0);
        SharedPreferences.Editor editor = appData.edit();
        //get current date
        String date = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date());
        //Put new values in SharedPreferences
        editor.putString(Counter.toString(),date);
        //Commit changes
        editor.commit();
        //Create List and insert SharedPreferences to List
        List<String> dataList = new ArrayList<String>();
        String temp = appData.getString(Counter.toString(),null);
        dataList.add(temp);
        //Create one String to display all collected data
        for(int y = 0 ; y < dataList.size() ; y++)
        {
            displayDataString += (dataList.get(y) + "\n");
        }
        //update textViews
        displayData.setText(displayDataString.toString());
        displayCounter.setText(Counter.toString());
    }

    public void removeData(View v)
    {
        //Get data
        SharedPreferences appData = getSharedPreferences("Data",0);
        //Clear all data and commit
        appData.edit().clear().commit();
        //Set Counter, displayString and TextViews to initials
        Counter = 0;
        displayCounter.setText(Counter.toString());
        displayData.setText("");
        displayDataString= "";
    }
}
