package Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.android.pokedr.FrontActivity;
import com.example.android.pokedr.LoginActivity;

import java.util.HashMap;
/**
 * Created by Siddhant Choudhary on 21-01-2017.
 */
public class SharedPref {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "MedLoginPref";//this is actully a file name jisme hmari preferences store ho rai ahi
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "uname";
    public static final String PASSWORDD ="password";


    public SharedPref(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String uname,String pass){
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_NAME,uname);
        editor.putString(PASSWORDD,pass);
        editor.commit();

        //login karte time call karna hai is function ko

    }
    public void checkLogin(){

        // jab app start hoga to splash activity k baad ye function call krna hai
        if(!this.isLoggedIn()){
            Intent i=new Intent(_context,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
        else{
            Intent i=new Intent(_context, FrontActivity.class);
            _context.startActivity(i);
        }
    }

    public HashMap<String,String> getuserdetails(){
        HashMap<String,String> user=new HashMap<String,String>();
        user.put(KEY_NAME,pref.getString(KEY_NAME,null));
        user.put(PASSWORDD,pref.getString(PASSWORDD,null));
        return user;

    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        //after logout ..redirect to user login activity
        Intent i=new Intent(_context,LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //start login activity
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){

        return pref.getBoolean(IS_LOGIN, false);  // bagal me default value hai

    }
}
