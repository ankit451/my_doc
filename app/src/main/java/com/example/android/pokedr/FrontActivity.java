package com.example.android.pokedr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonElement;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import Adapter.MessagesListAdapter;
import Utils.Message;
import Utils.Pref;
import Utils.SharedPref;
import ai.api.AIListener;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Fulfillment;
import ai.api.model.Result;

/**
 * Created by Siddhant Choudhary on 21-01-2017.
 */
public class FrontActivity extends AppCompatActivity implements AIListener {

    private ImageButton btnSend;
    private ImageButton recSend;
    private EditText inputMsg;
   String st;
    Set<String> set;
    HashMap<String,String> hmap=new HashMap<String,String>();
    private MessagesListAdapter adapter;
    private List<Message> listMessages;
    private ListView listViewMessages;
    private AIService aiService;

    private Pref utils;
    //private AIResponse aiResponse;
    private String query;

    private String name = null;
    private static final String TAG_SELF = "self", TAG_NEW = "new",
            TAG_MESSAGE = "message", TAG_EXIT = "exit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set=new HashSet<String>();
      //  hm = new HashMap();
        btnSend = (ImageButton) findViewById(R.id.btnSend);
        recSend = (ImageButton) findViewById(R.id.btnrec);
        inputMsg = (EditText) findViewById(R.id.inputMsg);
        listViewMessages = (ListView) findViewById(R.id.list_view_messages);

        utils = new Pref(getApplicationContext());

        final AIConfiguration config = new AIConfiguration("ae188edcfa3248d195bbe6140feb1875",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        final AIDataService aiDataService = new AIDataService(config);

        aiService = AIService.getService(this, config);
        aiService.setListener((AIListener) this);

        // Getting the person name from previous screen
        //Intent i = getIntent();
        //name = i.getStringExtra("name");
        //Log.v("name----",name);

        recSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"SPEAK NOW!!",Toast.LENGTH_SHORT).show();
                aiService.startListening();
            }
        });


        btnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                query = inputMsg.getText().toString();
                if(query.isEmpty()!=true) {
                    Message m1 = new Message(query, true);
                    appendMessage(m1);
                    final AIRequest aiRequest = new AIRequest();
                    aiRequest.setQuery(query);
                    new AsyncTask<AIRequest, Void, AIResponse>() {
                        @Override
                        protected AIResponse doInBackground(AIRequest... requests) {
                            final AIRequest request = requests[0];
                            try {
                                final AIResponse response = aiDataService.request(request);
                                return response;
                            } catch (AIServiceException e) {
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(AIResponse aiResponse) {
                            if (aiResponse != null) {
                                Result result = aiResponse.getResult();
                                setTrue(aiResponse);
                                Fulfillment fulfillment = result.getFulfillment();
                                String speech = fulfillment.getSpeech();
                                Message m = new Message(speech, false);
                                appendMessage(m);
                            }
                        }
                    }.execute(aiRequest);
                    inputMsg.setText("");
                }
                else
                    Toast.makeText(getApplicationContext(), "ENTER VALID INPUT", Toast.LENGTH_LONG).show();
            }
        });

        listMessages = new ArrayList<Message>();

        adapter = new MessagesListAdapter(this, listMessages);
        listViewMessages.setAdapter(adapter);
    }

    public void onResult(final AIResponse response) {
        Result result = response.getResult();
        setTrue(response);
        String resolved=result.getResolvedQuery();
        Message m2=new Message(resolved,true);
        appendMessage(m2);

        Fulfillment fulfillment;

        fulfillment=result.getFulfillment();
        String speech=fulfillment.getSpeech();
        Message m = new Message(speech,false);
        appendMessage(m);
    }

    @Override
    public void onError(AIError error) {
        Message m = new Message(error.toString(),false);
        appendMessage(m);
    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }

    private void appendMessage(final Message m) {
        String msg="Please wait. Your report is being generated.";
        if(m.getMessage().equals(msg)){
            String reply="";
            reply+="symptoms are: ";
            if(set.contains("headache"))
                reply+="headache ";
            if(set.contains("fever"))
                reply+="fever ";
            if(set.contains("stomach ache"))
                reply+="stomach ache ";
            if(set.contains("vomiting"))
                reply+="vomiting ";
            if(set.contains("sour"))
                reply+="sore throat ";
            Log.v("qqqqq", reply);
            sendMessage(reply);
        }

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                listMessages.add(m);
                adapter.notifyDataSetChanged();

                // Playing device's notification
                playBeep();
            }
        });
    }

    private void sendMessage(String reply){
        final AIConfiguration config = new AIConfiguration("ae188edcfa3248d195bbe6140feb1875",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        final AIDataService aiDataService = new AIDataService(config);

        if(reply.isEmpty()!=true) {
            final AIRequest aiRequest = new AIRequest();
            aiRequest.setQuery(reply);
            new AsyncTask<AIRequest, Void, AIResponse>() {
                @Override
                protected AIResponse doInBackground(AIRequest... requests) {
                    final AIRequest request = requests[0];
                    try {
                        final AIResponse response = aiDataService.request(request);
                        return response;
                    } catch (AIServiceException e) {
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(AIResponse aiResponse) {
                    if (aiResponse != null) {
                        Result result = aiResponse.getResult();
                        Fulfillment fulfillment = result.getFulfillment();
                        String speech = fulfillment.getSpeech();
                        Message m = new Message(speech, false);
                        appendMessage(m);
                    }
                }
            }.execute(aiRequest);
        }
    }


    public void playBeep() {

        try {
            Uri notification = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),
                    notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menu_history:{
                Intent i=new Intent(FrontActivity.this,MedicalHistoryActivity.class);
                startActivity(i);
                Toast.makeText(FrontActivity.this, "User History", Toast.LENGTH_SHORT).show();
                return true;
            }

            case R.id.action_settings:
                Toast.makeText(FrontActivity.this, "setting", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.profile:{
                Intent i=new Intent(FrontActivity.this,ProfileActivity.class);
                startActivity(i);
                Toast.makeText(FrontActivity.this, "Your Profile", Toast.LENGTH_SHORT).show();
                return true;
            }

            case R.id.sos:
                Toast.makeText(FrontActivity.this, "sos", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.logout: {
                Intent i=new Intent(FrontActivity.this,LoginActivity.class);
                SharedPref sp=new SharedPref(getApplicationContext());
                sp.logoutUser();
                finish();
                Toast.makeText(FrontActivity.this, "LogOut successfully", Toast.LENGTH_SHORT).show();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setTrue(AIResponse aiResponse){
        Result result = aiResponse.getResult();

        st ="";

        if (result.getParameters() != null && !result.getParameters().isEmpty()) {
            for (final Map.Entry<String, JsonElement> entry : result.getParameters().entrySet()) {
                if(entry.getKey().equals("symptoms")){
                   st=entry.getValue().getAsString();
                    Log.v("holaa ",st);
                    if(st.equals("headache"))
                        set.add("headache");
                    if(st.equals("vomiting"))
                        set.add("vomiting");
                    if(st.equals("fever"))
                        set.add("fever");
                    if(st.equals("stomach ache"))
                        set.add("stomach ache");
                    if(st.equals("sore throat"))
                        set.add("sour");
                    Log.v("nigs",set.size()+"");
                }
            }
        }
    }

    public boolean compString(String a,String b){
        if(a.equalsIgnoreCase(b))
            return true;
        else
            return false;
    }
}
