package com;
public class TestSync extends Thread {
	private static String sync = "";
	private String methodType = "";

	private static void method(String s) {
		synchronized (sync) {
			sync = s;
			System.out.println(s);
			while (true)
				;
		}
	}

	public void method1() {
		method("method1");
	}

	public static void staticMethod1() {
		method("staticMethod1");
	}

	public void run() {
		if (methodType.equals("static"))
			staticMethod1();
		else if (methodType.equals("nonstatic"))
			method1();
	}

	public TestSync(String methodType) {
		this.methodType = methodType;
	}

	public static void main(String[] args) throws Exception {
		TestSync sample1 = new TestSync("nonstatic");
		TestSync sample2 = new TestSync("static");
		sample1.start();
		sample2.start();
	}
}