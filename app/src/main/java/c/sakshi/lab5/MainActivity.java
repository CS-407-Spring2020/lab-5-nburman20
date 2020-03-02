package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5" , Context.MODE_PRIVATE);
        if(!sharedPreferences.getString(usernameKey, "").equals("")){
            Intent intent = new Intent(this, SecondActivity.class);


            intent.putExtra("username", sharedPreferences.getString(usernameKey, ""));

            startActivity(intent);

        }else{
            setContentView(R.layout.activity_main);
        }
    }
    public void onButtonClick(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        EditText myTextField = (EditText) findViewById(R.id.editText);
        String str = myTextField.getText().toString();
        intent.putExtra("username", str);
        SharedPreferences sharedPreferences =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str).apply();
        startActivity(intent);
    }
}
