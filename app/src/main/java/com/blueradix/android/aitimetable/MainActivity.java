package com.blueradix.android.aitimetable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.blueradix.android.aitimetable.Constants.RC_SIGN_IN;
import static com.blueradix.android.aitimetable.Constants.TAG;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://jivi.ait.nsw.edu.au/jivi/payload/";
    private static final String URL_TIMETABLE = "https://jivi.ait.nsw.edu.au/mobile/timetable.php";
    private String userId = "6808";
    private String password = "forMyAlex";

    GoogleSignInClient client;
    private WebView webView;
//    public static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 5 Build/LMY48B; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.65 Mobile Safari/537.36";
    public static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        client = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signIn();

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });


        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setUserAgentString(USER_AGENT);
        webView.loadUrl(URL);

        webView.setWebViewClient(new WebViewClient(){
             /*
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());

                return false;
            }





            @Override
            public void onPageFinished(WebView view, String url) {

                String script = "javascript:var studentId = document.getElementById('id');";
                script+= "var password = document.getElementById('pw');";
                script+= "var login = document.getElementById('hawinputsubmit');";
                script+= "studentId.value='" + userId + "';";
                script+= "password.value='" + password + "';";
                script+= "login.click();";

                view.loadUrl(script);
            }
            */

        });


    }

    public void scrape(View view) {



    }

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);


    }

    public void signIn() {
        Intent signInIntent = client.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            updateUI(account);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        if(account != null) {
            Log.i(TAG, account.getEmail());
            Log.i(TAG, account.getDisplayName());
            Log.i(TAG, account.getId());
//            Log.i(TAG, account.getIdToken());
            Log.i(TAG, account.isExpired()? "true":"false");
//            Log.i(TAG, account.getPhotoUrl().toString());
        }
    }

    public void gototimetable(View view) {
        webView.loadUrl(URL_TIMETABLE);
    }
}
