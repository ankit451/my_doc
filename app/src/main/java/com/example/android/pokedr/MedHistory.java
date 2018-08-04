package com.example.android.pokedr;

/**
 * Created by Siddhant Choudhary on 22-01-2017.
 */
public class MedHistory {

    int _id;
    String _date;
    String _illness;
    String _prescription;
    //Empty constructer
    public MedHistory(){

    }
    //Constructer
    public MedHistory(int id,String date,String illness,String prescription){
        this._id=id;
        this._date=date;
        this._illness=illness;
        this._prescription=prescription;

    }
    //constructer
    public  MedHistory(String date,String illness,String prescription){

        this._date=date;
        this._illness=illness;
        this._prescription=prescription;

    }
    //getting ID
    public int getID(){
        return this._id;
    }
    //setting id
    public void setId(int id){
        this._id=id;
    }
    //getting name
    public String getDate(){
        return this._date;
    }
    public void setDate(String date){
        this._date=date;
    }

    //illness
    public String getIllness(){
        return this._illness;
    }
    public void setIllness(String illness){
        this._illness=illness;
    }

    //prescriptions
    public String getPrescription(){
        return this._prescription;
    }
    public void setPrescription(String prescription){
        this._prescription=prescription;
    }
    //  Jai Bhole


}









