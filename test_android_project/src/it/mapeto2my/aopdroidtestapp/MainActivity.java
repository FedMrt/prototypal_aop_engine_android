package it.mapeto2my.aopdroidtestapp;

import it.mapeto2my.aopdroidtestapp.test_objects.IBoz;
import it.papeto2my.aop_droid.factories.impl.build.PFactory;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvOne;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tvOne = (TextView)findViewById(R.id.textView1);
		
		try{
			PFactory dFactory =  PFactory.getSingleInstance();
			IBoz boz = (IBoz) dFactory.getTarget("demoboz");
			tvOne.setText(boz.info());
		}catch(Exception e){
			tvOne.setText("OOOObz oooobz");
		}	
	}

}
