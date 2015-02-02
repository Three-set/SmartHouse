package com.example.zhihuijiaju;

import android.R.integer;

public class Info {
	private int Rid;
	private int minor;
	private int threshold_mix;
	private int threshold_max;
	private String text;
	private float x;
	private float y;
	private double orientation;
	public Info(int id,int mi,int mix,int max,String t,float x,float y) {
		// TODO Auto-generated constructor stub
		this.Rid = id;
		this.minor = mi;
		this.threshold_mix = mix;
		this.threshold_max = max;
		this.text = t;
		this.x=x;
		this.y=y;
	}
	
	public Info(int rid, int minor, int threshold_mix, int threshold_max, String text) {
		super();
		Rid = rid;
		this.minor = minor;
		this.threshold_mix = threshold_mix;
		this.threshold_max = threshold_max;
		this.text = text;
	}

	public int getThreshold_mix() {
		return threshold_mix;
	}
	public void setThreshold_mix(int threshold_mix) {
		this.threshold_mix = threshold_mix;
	}
	public int getThreshold_max() {
		return threshold_max;
	}
	public void setThreshold_max(int threshold_max) {
		this.threshold_max = threshold_max;
	}
	public int getRid() {
		return Rid;
	}
	public void setRid(int rid) {
		Rid = rid;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}
	
}
