package com.bunniesarecute.admin.textmadness;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class ShareOptions extends Activity implements OnClickListener {

    private String messageText;
    private ImageButton mTextMessageButton;
    private ImageButton mEmailMessageButton;
    private ImageButton mFacebookButton;
    private ImageButton mTwitterButton;
    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_options);
        Intent intent = getIntent();
        messageText = intent.getStringExtra("FULL_TEXT");
        mBundle = new Bundle();
        mBundle.putString("message", messageText);


        mTextMessageButton = (ImageButton) findViewById(R.id.text_message_button);
        mTextMessageButton.setOnClickListener(this);
        mEmailMessageButton = (ImageButton) findViewById(R.id.email_button);
        mEmailMessageButton.setOnClickListener(this);
        mFacebookButton = (ImageButton) findViewById(R.id.facebook_button);
        mFacebookButton.setOnClickListener(this);
        mTwitterButton = (ImageButton) findViewById(R.id.twitter_button);
        mTwitterButton.setOnClickListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.share_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.text_message_button:

                TextMessageNextStepFragment textFrag = new TextMessageNextStepFragment();
                textFrag.setArguments(mBundle);
                getFragmentManager().beginTransaction()
                        .add(R.id.container, textFrag)
                        .commit();
                break;

            case R.id.email_button:

                EmailMessageNextStepFragment emailFrag = new EmailMessageNextStepFragment();
                emailFrag.setArguments(mBundle);
                getFragmentManager().beginTransaction()
                        .add(R.id.container, emailFrag)
                        .commit();
                break;

            case R.id.facebook_button:
                popUpVersionToast();
                break;

            case R.id.twitter_button:
                popUpVersionToast();
                break;
        }

    }

    public void popUpVersionToast(){
        Toast.makeText(this, "Coming Soon in Version 2.Dan!", Toast.LENGTH_SHORT);
    }


    public static class TextMessageNextStepFragment extends Fragment {

        private Button doneButton;
        private EditText whereToSend;
        private String phoneNumber;
        private String messageToSend;

        public TextMessageNextStepFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_share_textmessage, container, false);
            phoneNumber = "";
            Bundle bundle = this.getArguments();
            messageToSend = bundle.getString("message");

            doneButton = (Button) rootView.findViewById(R.id.done_button);
            whereToSend = (EditText) rootView.findViewById(R.id.info_enter_space);

            doneButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                   phoneNumber = whereToSend.getText().toString();
                   sendTextMessage(messageToSend, phoneNumber);

                }
            });

            return rootView;
        }

        public void sendTextMessage(String message, String phoneNumber){
            SmsManager.getDefault().sendTextMessage(phoneNumber, null, message, null, null);
        }
    }

    public static class EmailMessageNextStepFragment extends Fragment {

        private Button doneButton;
        private EditText whereToSend;
        private String emailAddress;
        private String emailSubject;
        private String messageToSend;

        public EmailMessageNextStepFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_share_emailmessage, container, false);
            emailSubject = "My MadText";
            emailAddress = "";
            Bundle bundle = this.getArguments();
            messageToSend = bundle.getString("message");

            doneButton = (Button) rootView.findViewById(R.id.done_button);
            whereToSend = (EditText) rootView.findViewById(R.id.info_enter_space);

            doneButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    emailAddress = whereToSend.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
                    intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                    intent.putExtra(Intent.EXTRA_TEXT, messageToSend);

                    startActivity(Intent.createChooser(intent, "send email"));
                }
            });

            return rootView;
        }
    }

    public static class ContinueOrNot{

        public ContinueOrNot(){

        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View rootview = inflater.inflate()
        }
    }
}