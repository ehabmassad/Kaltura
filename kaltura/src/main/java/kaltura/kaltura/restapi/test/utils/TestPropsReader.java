package kaltura.kaltura.restapi.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import kaltura.kaltura.restapi.ConsoleLogger;



/**
 * Properties Reader Class that reads properties from a a properties file that
 * the default TestProps.properties must be found under the specified path
 * /src/main/java/resources/TestProps.properties in order to read and work
 * properly
 * 
 * @edited Geryes Moussa
 */
public class TestPropsReader {

	private static TestPropsReader instance;
	private static String CONF_FILE = File.separator + "resources"+File.separator;
	private static final String DEFAULT_PROPS = "TestProps.properties";
	Properties properties;
	private static boolean useLocal = false;
	private static String propsFileName;

	private static TestPropsReader instance() {
		if (instance == null) {
			instance = new TestPropsReader();
		}
		return instance;
	}

	private TestPropsReader() {
		try {
			// try to load as resource from class path first
			File f = new File("");
			InputStream is = new FileInputStream(new File(f.getAbsolutePath() + CONF_FILE + File.separator + DEFAULT_PROPS));
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			Properties p = new Properties();

			ConsoleLogger.info("Loading TestProps from " + CONF_FILE + DEFAULT_PROPS + "...");
			p.load(isr);
			is.close();
			try {
				// try load local props if exists
				String hostPropsFile = "";
				if (useLocal) {
					final String hostName = InetAddress.getLocalHost().getHostName();
					hostPropsFile = File.separator + "TestProps_" + hostName + ".properties";
				} else {
					hostPropsFile = File.separator + "TestProps" + ".properties";
				}
				is = new FileInputStream(new File(f.getAbsolutePath() + CONF_FILE + hostPropsFile));

				if (is != null) {
					String message = "Loading props from " + hostPropsFile + "...";
					ConsoleLogger.info(message);
					isr = new InputStreamReader(is, "UTF-8");
					p.load(isr);
					is.close();
				}
			} catch (final Throwable t) {
				System.err.println("Failed loading local commonprops file: " + t);
				ConsoleLogger.error("Failed loading local commonprops file: ", t);
			}

			properties = p;
		} catch (final Exception e) {
			System.err.println("Failed to initialize TestProps " + e);
			ConsoleLogger.error("Failed to initialize TestProps ", e);
			e.printStackTrace();
		}
	}

	// private void loadPropsFromURL(URL fileURL, Properties p) throws
	// IOException {
	// InputStream fis = null;
	// File file = null;
	// if (fileURL != null) {
	// file = new File(URLDecoder.decode(fileURL.getFile(), "utf-8"));
	// fis = new FileInputStream(file);
	// }
	// if (fis != null && file != null) {
	// String message = "Loading props from " + file.getAbsolutePath() + "...";
	// System.out.println(message);
	// SelTestLog.info(message);
	//
	// InputStreamReader isr = new InputStreamReader(fis, "UTF8");
	// p.load(isr);
	// }
	// }

	public static String get(final String propertyName) {
		return (instance().getProperty(propertyName));
	}

	public static List<String> getArray(final String propertyName) {
		return Arrays.asList(instance().getProperty(propertyName).split(","));
	}

	public static String get(final String propertyName, final String defaultResult) {
		return instance().getProperty(propertyName, defaultResult).isEmpty() ? defaultResult : instance().getProperty(propertyName, defaultResult);
	}

	public static int getInt(final String propertyName) {
		return instance().getPropertyInt(propertyName);
	}

	public static int getInt(final String propertyName, final int defaultResult) {
		return instance().getPropertyInt(propertyName, defaultResult);
	}

	public static boolean getBoolean(final String propertyName) {
		return instance().getPropertyBoolean(propertyName);
	}

	public static boolean getBoolean(final String propertyName, final boolean defaultResult) {
		return instance().getPropertyBoolean(propertyName, defaultResult);
	}

	public String getProperty(final String propertyName) {
		return getProperty(propertyName, null);
	}

	public String getProperty(final String propertyName, final String defaultResult) {
		String result = properties.getProperty(propertyName);
		if (result == null) {
			return defaultResult;
		}
		return result;
	}

	public int getPropertyInt(final String propertyName) {
		return getPropertyInt(propertyName, -1);
	}

	public int getPropertyInt(final String propertyName, final int defaultResult) {
		String result = properties.getProperty(propertyName);
		if (result == null) {
			return defaultResult;
		}
		return Integer.parseInt(result);
	}

	/**
	 * @param cONF_FILE
	 *            the cONF_FILE to set
	 */
	public static void setCONF_FILE(String cONF_FILE) {
		CONF_FILE = cONF_FILE;
	}

	public boolean getPropertyBoolean(final String propertyName) {
		return getPropertyBoolean(propertyName, false);
	}

	public boolean getPropertyBoolean(final String propertyName, final boolean defaultResult) {
		String result = properties.getProperty(propertyName);
		if (result == null) {
			return defaultResult;
		}
		return Boolean.parseBoolean(result);
	}

	public static void setFileName(String testPropertiesFileName) {
		killInstace();
		propsFileName = testPropertiesFileName;
		useLocal = false;
		instance = new TestPropsReader();
	}

	private static void killInstace() {
		instance = null;
	}
}