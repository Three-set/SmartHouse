package com.android.demon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class SampleHomeBedroomActivity extends Activity{
	private View mSampleHomeLight;
	private View mSampleHomeTvbox;
	private View mSampleHomeCondition;
	private View mSampleHomePage;
	private View mSampleHomeCommon;
	private View mSampleHomeChoose;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sample_home_bedroom);
		
		mSampleHomePage = findViewById(R.id.sample_home_page_button);
		mSampleHomeCommon = findViewById(R.id.sample_home_common_button);
		mSampleHomeChoose = findViewById(R.id.sample_home_choose_button);
		mSampleHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent intent01 = new Intent();
                intent01.setClass(getApplicationContext(), SampleHomeActivity.class);
                startActivity(intent01);
            }
        });
		mSampleHomeCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }
        });
		mSampleHomeChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SampleHomeChooseActivity.class);
                startActivity(intent);
            }
        });
		
		
		mSampleHomeLight = findViewById(R.id.sample_control_light_button);
		mSampleHomeLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }
        });
		
		mSampleHomeTvbox = findViewById(R.id.sample_control_tvbox_button);
		mSampleHomeTvbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }
        });
		
		mSampleHomeCondition = findViewById(R.id.sample_control_condition_button);
		mSampleHomeCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }
        });
		
	}
}
