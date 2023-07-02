package com.example.notetakingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edit text
    private EditText AddNoteTitle, AddNoteSubtitle, AddPhoto;

    // creating variable for button
    private Button BtnCreateNote;

    // creating a strings for storing our values from edittext fields.
    private String NoteTitle, NoteSubtitle, NotePhoto;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our edittext and buttons
        AddNoteTitle = findViewById(R.id.idNoteTitle);
        AddNoteSubtitle = findViewById(R.id.idNoteSubtitle);
        AddPhoto = findViewById(R.id.idNotePhoto);
        BtnCreateNote = findViewById(R.id.idBtnCreateNote);
        BtnCreateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting data from edittext fields.
                String title = AddNoteTitle.getText().toString();
                String subtitle = AddNoteSubtitle.getText().toString();
                String photo = AddPhoto.getText().toString();


                // validating the text fields if empty or not.
                if (TextUtils.isEmpty(NoteTitle)) {
                    AddNoteTitle.setError("Please enter Note Title");
                } else if (TextUtils.isEmpty(NoteSubtitle)) {
                    AddNoteSubtitle.setError("Please enter Note Subtitle");
                } else if (TextUtils.isEmpty(NotePhoto)) {
                    AddPhoto.setError("Please enter Course Duration");
                } else {
                    // calling method to add data to Firebase.
                    addDataToDatabase(NoteTitle, NoteSubtitle, NotePhoto);
                }
            }
        });
    }

    private void addDataToDatabase(String NoteTitle, String NoteSubtitle, String NotePhoto) {

        // url to post our data
        String url = "http://143.198.86.252/createnote.php";

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(MainActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                AddNoteTitle.setText("");
                AddNoteSubtitle.setText("");
                AddPhoto.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(MainActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("NoteTitle", NoteTitle);
                params.put("NoteSubtitle", NoteSubtitle);
                params.put("courseDescription", NotePhoto);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}
