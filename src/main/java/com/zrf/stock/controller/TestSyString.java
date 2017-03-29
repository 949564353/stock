package com.zrf.stock.controller;

public class TestSyString {
	public static Object lock = new Object();
	public void test(String str) throws InterruptedException {
		String name = str;
		synchronized (name) {
			System.out.println(name);
		}
	}
}