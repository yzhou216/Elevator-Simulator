package io.yiyuzhou.app;

public class App {
	public static void main(String[] args) {
		Env env = new Env(args[0]);
		System.out.println(env.getProperty("structures"));
		System.out.println(env.getProperty("floors"));
		System.out.println(env.getProperty("passengers"));
		System.out.println(env.getProperty("elevators"));
		System.out.println(env.getProperty("elevatorCapacity"));
		System.out.println(env.getProperty("duration"));
	}
}
