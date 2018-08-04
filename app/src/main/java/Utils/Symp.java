package Utils;

/**
 * Created by Siddhant Choudhary on 21-01-2017.
 */
public class Symp {
    public boolean headache;
    public boolean vomiting;
    public boolean stomach;
    public boolean sour_throat;
    public boolean fever;

    public Symp(){
        fever=false;
        headache=false;
        vomiting=false;
        stomach=false;
        sour_throat=false;
    }

    public void setHeadache(boolean headache) {
        this.headache = headache;
    }

    public void setSour_throat(boolean sour_throat) {
        this.sour_throat = sour_throat;
    }

    public void setStomach(boolean stomach) {
        this.stomach = stomach;
    }

    public void setVomiting(boolean vomiting) {
        this.vomiting = vomiting;
    }

    public void setFever(boolean fever) {
        this.fever = fever;
    }

     String getB(){
        String msg="";
        if(headache==true)
            msg+="headache ";
        else if(sour_throat==true)
            msg+="sore throat ";
        else if(vomiting==true)
            msg+="vomiting ";
        else  if(stomach==true)
            msg+="stomach ache";
        else
            msg+="fever ";
        return msg;
    }
}
