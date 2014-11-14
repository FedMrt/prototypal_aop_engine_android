package it.mapeto2my.aopdroidtestapp.test_objects;

import android.util.Log;

public class Boz implements IBoz{

	@Override
	public String info() {
		Log.d("AOPDroid","I'm a Boz !!!!");
		return " and I return a value !!!!";
	}

}
