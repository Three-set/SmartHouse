package com.android.demon;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.content.Intent;

public class SampleHomeChooseActivity extends Activity{
	private View mSampleHomeReception;
	private View mSampleHomeWaitreception;
	private View mSampleHomeShowroom;
	private View mSampleHomeBedroom;
	private View mSampleHomeSmallgarden;
	private View mSampleHomeBathroom;
	private View mSampleHomeKitchen;
	private View mSampleHomeAttic;
	private View mSampleHomePage;
	private View mSampleHomeCommon;
	private View mSampleHomeChoose;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sample_home_choose);
		//this is control Reception
		mSampleHomeReception = findViewById(R.id.sample_home_reception_button);
		mSampleHomeReception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent01 = new Intent();
                intent01.setClass(getApplicationContext(), SampleHomeReceptionActivity.class);
                startActivity(intent01);
            }
        });
		//this is control Waitreception
		mSampleHomeWaitreception = findViewById(R.id.sample_home_waitreception_button);
		mSampleHomeWaitreception.setOnClickListener(new View.OnClickListener() {
			@Override
		    public void onClick(View v) {
				Intent intent02 = new Intent();
				intent02.setClass(getApplicationContext(), SampleHomeWaitreceptionActivity.class);
                startActivity(intent02);     
		            }
		});
		//this is control Showroom
		mSampleHomeShowroom = findViewById(R.id.sample_home_showroom_button);
		mSampleHomeShowroom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent03 = new Intent();
				intent03.setClass(getApplicationContext(), SampleHomeShowroomActivity.class);
                startActivity(intent03);         
		            }
		});
		//this is control Bedroom
		mSampleHomeBedroom = findViewById(R.id.sample_home_bedroom_button);
		mSampleHomeBedroom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent04 = new Intent();
				intent04.setClass(getApplicationContext(), SampleHomeBedroomActivity.class);
                startActivity(intent04);                
				            }
		});
		//this is control Smallgarden
		mSampleHomeSmallgarden = findViewById(R.id.sample_home_smallgarden_button);
		mSampleHomeSmallgarden.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent05 = new Intent();
				intent05.setClass(getApplicationContext(), SampleHomeSmallgardenActivity.class);
                startActivity(intent05);               
				            }
		});
		//this is control Bathroom
		mSampleHomeBathroom = findViewById(R.id.sample_home_bathroom_button);
		mSampleHomeBathroom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent06 = new Intent();
				intent06.setClass(getApplicationContext(), SampleHomeBathroomActivity.class);
                startActivity(intent06);                
				            }
		});
		//this is control Kitchen
		mSampleHomeKitchen = findViewById(R.id.sample_home_kitchen_button);
		mSampleHomeKitchen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent07 = new Intent();
				intent07.setClass(getApplicationContext(), SampleHomeKitchenActivity.class);
                startActivity(intent07);                
				            }
		});
		//this is control Attic
		mSampleHomeAttic = findViewById(R.id.sample_home_attic_button);
		mSampleHomeAttic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent08 = new Intent();
				intent08.setClass(getApplicationContext(), SampleHomeAtticActivity.class);
                startActivity(intent08);               
				            }
		});
		//this is control SampleHomePage
		mSampleHomePage = findViewById(R.id.sample_home_page_button);
		mSampleHomePage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent09 = new Intent();
                intent09.setClass(getApplicationContext(), SampleHomeActivity.class);
                startActivity(intent09);                 
				            }
		});
		//this is control SampleHomeCommon
		mSampleHomeCommon = findViewById(R.id.sample_home_common_button);
		mSampleHomeCommon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				               
				            }
		});

		//this is control SampleHomeChoose
		mSampleHomeChoose = findViewById(R.id.sample_home_choose_button);
		mSampleHomeChoose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				                
				            }
		});
	}
}
