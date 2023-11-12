package io.yiyuzhou.app;

import java.io.File;
import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;

class Env {
	private Properties env = new Properties();

	public Env() {
		initializeEnv(null);
	}

	/**
	 * Overloaded constructor which accepts a file path to load environment variables.
	 * It uses the Dotenv library to read configuration from the specified file.
	 *
	 * @param path The file path to the configuration file.
	 */
	public Env(String path) {
		Dotenv dotenv;
		File file = new File(path);
		String directoryPath = file.getParent();
		String filename = file.getName();
		dotenv = Dotenv.configure().directory(directoryPath).filename(filename).load();
		initializeEnv(dotenv);
	}

	/**
	 * Initializes environment properties. If a Dotenv object is provided, it loads
	 * properties from the given configuration; otherwise, it sets default values.
	 *
	 * @param dotenv An instance of Dotenv used to load environment variables from a file.
	 */
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

	/**
	 * Retrieves an environment variable's value using a given key. If the key is not found,
	 * a default value is returned. This method supports the scenario where dotenv is not provided.
	 *
	 * @param dotenv The Dotenv instance used to retrieve environment variables.
	 * @param key The key for the environment variable.
	 * @param defaultValue The default value to return if the key is not found.
	 * @return The value of the environment variable or the default value if the key is not present.
	 */
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
