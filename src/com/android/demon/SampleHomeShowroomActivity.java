package com.android.demon;

import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

public class SampleHomeShowroomActivity extends Activity implements SeekBar.OnSeekBarChangeListener{
	private View mSampleHomeLight;
	private View mSampleHomeCurtain;
	private View mSampleHomeProjector;
	private View mSampleHomePage;
	private View mSampleHomeCommon;
	private View mSampleHomeChoose;
	private SeekBar mSeekBar;
	private TextView mProgressText;
//	private Dialog malerDialog;
	private BrightnessDialog mBrightnessDialog;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_sample_home_showroom);
		
		mSampleHomePage = findViewById(R.id.sample_home_page_button);
		mSampleHomeCommon = findViewById(R.id.sample_home_common_button);
		mSampleHomeChoose = findViewById(R.id.sample_home_choose_button);
		
		mSeekBar = (SeekBar)findViewById(R.id.home_light_seekbar);
		mProgressText = (TextView) findViewById(R.id.home_light_progress);
		mSeekBar.setOnSeekBarChangeListener(this);
		mBrightnessDialog = new BrightnessDialog(this);
	//	malerDialog = new AlertDialog.Builder(this). 
      //          setTitle("对话框的标题").  
      //          setIcon(R.drawable.ic_launcher).
                //setView(mView).
        //        create();
		
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
            	//malerDialog.show();
            	mBrightnessDialog.show();
            }
        });
		
		mSampleHomeCurtain = findViewById(R.id.sample_control_curtain_button);
		mSampleHomeCurtain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }
        });
		
		mSampleHomeProjector = findViewById(R.id.sample_control_projector_button);
		mSampleHomeProjector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            }
        });
		
		
		 
        
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO 自动生成的方法存根
		 mProgressText.setText("当前值:" + progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO 自动生成的方法存根
		
	}
}
