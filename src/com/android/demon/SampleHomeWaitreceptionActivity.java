package com.android.demon;

import com.example.zhihuijiaju.MapActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class SampleHomeWaitreceptionActivity extends Activity{
	private View mSampleHomeLight;
	private View mSampleHomeCurtain;
	private View mSampleHomeMusic;
	private View mSampleHomeTv;
	private View mSampleHomeTvbox;
	private View mSampleHomeCondition;
	private View mSampleHomeSafe;
	private View mSampleHomeBlue;
	private View mSampleHomePage;
	private View mSampleHomeCommon;
	private View mSampleHomeChoose;
	private View mSampleHomeGuide;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sample_home_waitreception);
		
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
		
		mSampleHomeCurtain = findViewById(R.id.sample_control_curtain_button);
		mSampleHomeCurtain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }
        });
		
		mSampleHomeMusic = findViewById(R.id.sample_control_music_button);
		mSampleHomeMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }
        });
		
		mSampleHomeTv = findViewById(R.id.sample_control_tv_button);
		mSampleHomeTv.setOnClickListener(new View.OnClickListener() {
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
		
		mSampleHomeSafe = findViewById(R.id.sample_control_safe_button);
		mSampleHomeSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }
        });
		
		mSampleHomeBlue = findViewById(R.id.sample_control_blue_button);
		mSampleHomeBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }
        });
		mSampleHomeGuide=findViewById(R.id.sample_image_guide);
		mSampleHomeGuide.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(SampleHomeWaitreceptionActivity.this,MapActivity.class);
				startActivity(intent);
			}
		});
	}
}
