package m2.ila.fr.istic.ila.vv.mutation.loader;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

	private Properties properties;
	private static PropertiesLoader instance;

	private PropertiesLoader() {

		Properties prop = new Properties();
		try {
			prop.load(PropertiesLoader.class.getResourceAsStream("/application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.properties = prop;
	}

	public static PropertiesLoader getInstance() throws IOException {
		if (instance == null) {
			instance = new PropertiesLoader();
		}
		return instance;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
