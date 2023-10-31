package io.yiyuzhou.app;

import io.github.cdimascio.dotenv.Dotenv;
// import io.github.cdimascio.dotenv.DotenvException;

public class App {
	public static void main(String[] args) {
		/* env tests */
		Dotenv dotenv = Dotenv.load();
		System.out.println(dotenv.get("NAME"));
	}
}
