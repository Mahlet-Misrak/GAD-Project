package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Interface.SubmitInterface;
import com.example.project.Model.Submit;
import com.example.project.Services.ServiceBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitActivity extends AppCompatActivity {
    private Submit submit;
    private View successPopupView;
    private PopupWindow successPopupWindow;
    private PopupWindow failurePopupWindow;
    private View failurePopupView;
    private PopupWindow confirmPopupWindow;
    private View confirmPopupView;
    private ProgressBar mLoading;
//    Button ok;
//    ImageButton cancel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        final ImageButton btn = (ImageButton) findViewById(R.id.btn_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmitActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        this.getSupportActionBar().setHomeButtonEnabled(true);
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        successPopupView = inflater.inflate(R.layout.success_popup_window, null);
        failurePopupView = inflater.inflate(R.layout.failure_popup_window,null);
        confirmPopupView = inflater.inflate(R.layout.confirmation_fragment,null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        successPopupWindow = new PopupWindow(successPopupView, width, height, focusable);
        failurePopupWindow = new PopupWindow(failurePopupView,width,height,focusable);
        confirmPopupWindow = new PopupWindow(confirmPopupView,width,height,focusable);
        mLoading = (ProgressBar) findViewById(R.id.progressBar);
        mLoading.setVisibility(View.INVISIBLE);
        successPopupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                successPopupWindow.dismiss();
                return true;
            }
        });
        failurePopupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                failurePopupWindow.dismiss();
                return true;
            }
        });
        final Button ok = (Button) confirmPopupView.findViewById(R.id.btnOk) ;
        final ImageButton cancel = (ImageButton) confirmPopupView.findViewById(R.id.btnClose);

        Button submitBtn = (Button) findViewById(R.id.submit_form_btn);
        final EditText firstName = (EditText) findViewById(R.id.first_name);
        final EditText lastName = (EditText) findViewById(R.id.last_name);
        final EditText email =(EditText) findViewById(R.id.email);
        final EditText github = (EditText) findViewById(R.id.github);
        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
             submit = new Submit(
                    firstName.getText().toString(),
                    lastName.getText().toString(),
                    email.getText().toString(),
                    github.getText().toString()
            );

             ok.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     sendRequest(submit);
                     confirmPopupWindow.dismiss();
                     mLoading.setVisibility(View.VISIBLE);
                 }
             });
             cancel.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     confirmPopupWindow.dismiss();
                 }
             });
                confirmPopupWindow.showAtLocation(confirmPopupView, Gravity.CENTER, 0, 0);
            }

        });

         }

    private void sendRequest(Submit pSubmit) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/")
                .addConverterFactory(GsonConverterFactory.create()).client(ServiceBuilder.okHttp.build());
        Retrofit retrofit = builder.build();
        SubmitInterface mSubmitInterface = retrofit.create(SubmitInterface.class);

        Call<Void> call =mSubmitInterface.submitForm(submit.getFirstName(),submit.getLastName(),submit.getEmail(),submit.getProjectUrl());
       call.enqueue(new Callback<Void>() {
            @Override
           public void onResponse(Call<Void> call, Response<Void> response) {
               if (response.isSuccessful()) {
                   Toast.makeText(SubmitActivity.this, "Its working", Toast.LENGTH_LONG).show();
                   mLoading.setVisibility(View.GONE);
                   successPopupWindow.showAtLocation(successPopupView, Gravity.CENTER, 0, 0);
               }
               else if(response.code()== 401){
                   Toast.makeText(SubmitActivity.this, "Your session has expired ",Toast.LENGTH_LONG).show();
                   mLoading.setVisibility(View.GONE);
                   failurePopupWindow.showAtLocation(failurePopupView, Gravity.CENTER, 0, 0);
               }
               else if(response.code()== 400){
                   Toast.makeText(SubmitActivity.this, "Incorrect Input ",Toast.LENGTH_LONG).show();
                   mLoading.setVisibility(View.GONE);
                   failurePopupWindow.showAtLocation(failurePopupView, Gravity.CENTER, 0, 0);
               }
               else if(response.code()== 500){
                   Toast.makeText(SubmitActivity.this, "There is a Problem with the Server",Toast.LENGTH_LONG).show();
                   mLoading.setVisibility(View.GONE);
                   failurePopupWindow.showAtLocation(failurePopupView, Gravity.CENTER, 0, 0);
               }
               else{
                   Toast.makeText(SubmitActivity.this,  "Something is wrong", Toast.LENGTH_LONG).show();
                mLoading.setVisibility(View.GONE);
                failurePopupWindow.showAtLocation(failurePopupView, Gravity.CENTER, 0, 0);
           }
           }


           @Override
           public void onFailure(Call<Void> call, Throwable t) {
               if(t instanceof IOException) {
                   Toast.makeText(SubmitActivity.this, "A connection error occurred", Toast.LENGTH_LONG).show();
                   failurePopupWindow.showAtLocation(failurePopupView, Gravity.CENTER, 0, 0);

               }
               else{
                   Toast.makeText(SubmitActivity.this, "Failed ", Toast.LENGTH_LONG).show();
                   failurePopupWindow.showAtLocation(failurePopupView, Gravity.CENTER, 0, 0);
               }
           }
       });
    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//        startActivityForResult(intent, 0);
//        return true;
//    }
}
