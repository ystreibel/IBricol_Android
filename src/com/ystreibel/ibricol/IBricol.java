package com.ystreibel.ibricol;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class IBricol extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ibricol);
        
        if(android.os.Build.VERSION.SDK_INT > 9){
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        	StrictMode.setThreadPolicy(policy);	
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ibricol, menu);
        return true;
    }
    
    public void showPickerDialog(View v) {
        DialogFragment newFragment = new DayPickerFragment();
        newFragment.show(getSupportFragmentManager(), "dayPicker");
    }
    
    public void validate(View v) {
    	
    	String localisation = ((EditText)this.findViewById(R.id.editText1)).getText().toString();
    	String date = ((EditText)this.findViewById(R.id.editText2)).getText().toString();
    	
    	AppelService service = new AppelService();
    	String reponse = "";
    	
    	// Appel du web service en fonction des valeurs renseignées
    	if(date.equals("Aujourd'hui")){
    		reponse = service.call("BRI", localisation, "0","d");
    	}else if(date.equals("Ce soir")){
    		reponse = service.call("BRI", localisation, "0","n");
    	}else if(date.equals("Demain")){
    		reponse = service.call("BRI", localisation, "1","d");
    	}else if(date.equals("Demain soir")){
    		reponse = service.call("BRI", localisation, "1","n");
    	}else if(date.equals("Après demain")){
    		reponse = service.call("BRI", localisation, "2","d");
    	}else if(date.equals("Après demain soir")){
    		reponse = service.call("BRI", localisation, "2","n");
    	}
    	    	
    	// Traitement du retour du web service
    	if(reponse.equals("0")){
    		((TextView)this.findViewById(R.id.textView4)).setText("OUI");
    		((ImageView) findViewById(R.id.imageView1)).setImageResource(R.raw.feu_vert);
    	}else if(reponse.equals("1")){
    		((TextView)this.findViewById(R.id.textView4)).setText("PEUT-ETRE");
    		((ImageView) findViewById(R.id.imageView1)).setImageResource(R.raw.feu_orange);    		
    	}else if(reponse.equals("2")){
    		((TextView)this.findViewById(R.id.textView4)).setText("NON");
    		((ImageView) findViewById(R.id.imageView1)).setImageResource(R.raw.feu_rouge);    		
    	}else if(reponse.equals("3")){
    		((TextView)this.findViewById(R.id.textView4)).setText("ERREUR");
    		((ImageView) findViewById(R.id.imageView1)).setImageResource(R.raw.feu_neutre);    		
    	}else{
    		((TextView)this.findViewById(R.id.textView4)).setText("VALEURS");
    		((ImageView) findViewById(R.id.imageView1)).setImageResource(R.raw.feu_neutre);    		
    	}
    	
    }
}
