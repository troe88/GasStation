package com.spbstu.java;

import java.util.concurrent.TimeUnit;

public class Utill {
	static public void pause(int ms){
		try {
			TimeUnit.MILLISECONDS.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
