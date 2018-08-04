package com.example.android.pokedr;

/**
 * Created by Siddhant Choudhary on 22-01-2017.
 */
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Microsoft on 21-01-2017.
 */
public class MedicalHistoryActivity extends AppCompatActivity{


    String dates[]={"22/oct/2015","23/oct/2015","24/oct/2015","25/oct/2015"};
    String illness[]={"headache","headache","fever","fever"};
    String prescription[]={"crocine","zupar","calpol","metacine"};

    ListView lview;
    ListViewCustomAdapter lvadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);
        // initialize all the arrays
       // getData();
        lview=(ListView)findViewById(R.id.medical_history_list);

        //somehow get three arrays for dates illness and prescriptions

        lvadapter=new ListViewCustomAdapter(this,dates,illness,prescription);

        lview.setAdapter(lvadapter);

    }
    //initialize all the arrays
    public void getData(){
        //jai ho bhole ki
        DatabaseHandler db=new DatabaseHandler(this);
        List<MedHistory> med=db.getAllHistory();


        int n=med.size();
        dates=new String[n];
        illness=new String[n];
        prescription=new String[n];
        int i=0;
        for(MedHistory mh:med){
            dates[i]=mh.getDate();
            illness[i]=mh.getIllness();
            prescription[i]=mh.getPrescription();
            i++;
        }
    }
}
