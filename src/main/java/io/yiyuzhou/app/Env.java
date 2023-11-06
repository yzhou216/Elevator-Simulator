package io.yiyuzhou.app;

import java.io.File;
import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;
// import io.github.cdimascio.dotenv.DotenvException;

class Env {
	private Properties env = new Properties();

	public Env() {
		initializeEnv(null);
	}

	/* overloaded constructor accepts file path */
	public Env(String path) {
		Dotenv dotenv;
		File file = new File(path);
		String directoryPath = file.getParent();
		String filename = file.getName();
		dotenv = Dotenv.configure().directory(directoryPath).filename(filename).load();
		initializeEnv(dotenv);
	}

	private void initializeEnv(Dotenv dotenv) {
		if (dotenv != null) {
			env.setProperty("structures", getEnvWithDefault(dotenv, "structures", "linked"));
			env.setProperty("floors", getEnvWithDefault(dotenv, "floors", "32"));
			env.setProperty("passengers", getEnvWithDefault(dotenv, "passengers", "0.03"));
			env.setProperty("elevators", getEnvWithDefault(dotenv, "elevators", "1"));
			env.setProperty("elevatorCapacity", getEnvWithDefault(dotenv, "elevatorCapacity", "10"));
			env.setProperty("duration", getEnvWithDefault(dotenv, "duration", "500"));
		} else {
			env.setProperty("structures", "linked");
			env.setProperty("floors", "32");
			env.setProperty("passengers", "0.03");
			env.setProperty("elevators", "1");
			env.setProperty("elevatorCapacity", "10");
			env.setProperty("duration", "500");
		}
	}

	private String getEnvWithDefault(Dotenv dotenv, String key, String defaultValue) {
		if (dotenv == null)
			return defaultValue;

		String value = dotenv.get(key);
		return value != null ? value : defaultValue;
	}

	public String getProperty(String key) {
		return this.env.getProperty(key);
	}
}
