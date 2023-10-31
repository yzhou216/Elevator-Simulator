package io.yiyuzhou.app;

import java.io.File;
import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;
// import io.github.cdimascio.dotenv.DotenvException;

class Env {
	private Properties env = new Properties();

	public Env() {
		this(null);
	}

	/* overloaded constructor accepts file path */
	public Env(String path) {
		Dotenv dotenv;
		if (path != null && !path.isEmpty()) {
			File file = new File(path);
			String directoryPath = file.getParent();
			String filename = file.getName();
			dotenv = Dotenv.configure().directory(directoryPath).filename(filename).load();
		} else {
			dotenv = Dotenv.load();
		}
		initializeEnv(dotenv);
	}

	private void initializeEnv(Dotenv dotenv) {
		env.setProperty("structures", getEnvWithDefault(dotenv, "structures", "linked"));
		env.setProperty("floors", getEnvWithDefault(dotenv, "floors", "32"));
		env.setProperty("passengers", getEnvWithDefault(dotenv, "passengers", "0.03"));
		env.setProperty("elevators", getEnvWithDefault(dotenv, "elevators", "1"));
		env.setProperty("elevatorCapacity", getEnvWithDefault(dotenv, "elevatorCapacity", "10"));
		env.setProperty("duration", getEnvWithDefault(dotenv, "duration", "500"));
	}

	private String getEnvWithDefault(Dotenv dotenv, String key, String defaultValue) {
		String value = dotenv.get(key);
		return value != null ? value : defaultValue;
	}

	public String getProperty(String key) {
		return this.env.getProperty(key);
	}
}
