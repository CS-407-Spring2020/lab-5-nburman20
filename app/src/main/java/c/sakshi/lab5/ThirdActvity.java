package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActvity extends AppCompatActivity {
    private int noteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_actvity);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteid", -1);
        EditText myTextField = (EditText) findViewById(R.id.noteeditor);
        if(noteId != -1){
            Note note = SecondActivity.notes.get(noteId);
            String noteContent = note.getContent();
            myTextField.setText(noteContent);
        }

    }
    public void onButtonClick(View view){
        EditText myTextField = (EditText) findViewById(R.id.noteeditor);
        String noteContent = myTextField.getText().toString();

        //2.GetSQLite Db instance
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes",
                Context.MODE_PRIVATE, null);

        //3.Inititate the notes class vraible using readnotes method in DBHelper class.Use the username you got from Shared preferences
        //as a parameter to read notes method
        DBHelper helper = new DBHelper(sqLiteDatabase);
        SharedPreferences sharedPreferences =
                getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteId == -1){
            title = "NOTE_" + (SecondActivity.notes.size() +1);
            helper.saveNotes(username, title, noteContent, username);
        }
        else {
            title = "NOTE_" + (noteId + 1);
            helper.updateNote(title, date, noteContent, username);
        }

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}
