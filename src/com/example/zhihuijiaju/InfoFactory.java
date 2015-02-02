package com.example.zhihuijiaju;

import java.util.ArrayList;

public class InfoFactory {
	public static ArrayList<Info> getInfoslist() {
		ArrayList<Info> infolist = new ArrayList<Info>();
		infolist.add(new Info(4, 4, -65, -20, "鍗敓闂�", 331, 97));
		infolist.add(new Info(6, 6, -80, -20, "鍗у", 352, 213));
		infolist.add(new Info(1, 1, -75, -20, "澶ч棬", 254, 589));
		infolist.add(new Info(2, 2, -80, -20, "鑺卞洯", 146, 570));
		infolist.add(new Info(9, 9, -75, -20, "澶у巺", 353, 416));
		infolist.add(new Info(8, 8, -75, -20, "鍘ㄦ埧", 421, 258));
		infolist.add(new Info(3, 3, -80, -20, "褰遍煶瀹�", 417, 594));
		infolist.add(new Info(7, 7, -80, -20, "宸ヤ綔闂�", 329, 262));
		return infolist;
	}
	public static int getminRssi(ArrayList<Info> lists, int minor){
		int minrssi=-80;
		for (int i = 0; i < lists.size(); i++) {
			if(lists.get(i).getMinor()==minor){
				minrssi=lists.get(i).getThreshold_mix();
				return minrssi;
			}
		}
		return minrssi;
	}
	public static Info getinfo(ArrayList<Info> infos2, int minor) {
		// TODO Auto-generated method stub
		for (Info info : infos2) {
			if (info.getMinor() == minor)
				return info;
		}
		return null;
	}
}
