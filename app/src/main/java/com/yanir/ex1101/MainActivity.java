package com.yanir.ex1101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Yanir Aton
 * @version 1.0
 * @since 2023-10-31
 * this is the main activity
 * it handles the counter and the name
 * it has 3 buttons, one for increment, one for resting the counter and one for exiting the
 * application and saving the data in the shared preferences for next use
 */
public class MainActivity extends AppCompatActivity {

    EditText NameET;
    TextView NumTV;
    Button CountBTN,ResetBTN,ExitBTN;
    int counter;
    SharedPreferences settings;
    Intent si;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NameET = (EditText) findViewById(R.id.NameET);
        NumTV = (TextView) findViewById(R.id.NumTV);
        CountBTN = (Button) findViewById(R.id.CountBTN);
        ResetBTN = (Button) findViewById(R.id.ResetBTN);
        ExitBTN = (Button) findViewById(R.id.ExitBTN);

        settings = getSharedPreferences("INFO",MODE_PRIVATE);
        counter = settings.getInt("num",0);
        NameET.setText(settings.getString("name",""));
        NumTV.setText(counter + "");
    }

    /**
     * this method increments the counter by one and updates the TextView that displays the counter
     * @param v the button
     */
    public void Count(View v){
        counter++;
        NumTV.setText(counter + "");
    }

    /**
     * this method restarts the counter to 0 and updates the TextView that displays the counter
     * @param v the restart button
     */
    public void Reset(View v){
        counter = 0;
        NumTV.setText(counter + "");
    }

    /**
     * this method saves the name and the counter and exit the app
     * @param v the exit button
     */
    public void Exit(View v){
        SharedPreferences.Editor editor=settings.edit();
        editor.putInt("num",counter);
        editor.putString("name", String.valueOf(NameET.getText()));
        editor.commit();
        finishAndRemoveTask();
    }

    /**
     * This function presents the options menu for moving between activities.
     * @param menu The options menu in which you place your items.
     * @return true in order to show the menu, otherwise false.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        System.out.println(item.getTitle().toString());
        if (item.getTitle().toString().equals("credit")){
            si = new Intent(this, credit.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}