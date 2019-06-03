package kaltura.kaltura.restapi;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;


/**
 * The Common Class ConsoleLogger.
 */
public class ConsoleLogger {
	
	/** The Constant PERIOD. */
	private final static String PERIOD = ".";

	/**
	 * Warn.
	 *
	 * @param msg the msg
	 * @param params the params
	 */
	public static void warn(String msg, Object... params) {
		warn(msg, (Throwable) null, params);
	}

	/**
	 * Info.
	 *
	 * @param msg the msg
	 * @param params the params
	 */
	public static void info(String msg, Object... params) {
		info(msg, (Throwable) null, params);
	}

	/**
	 * Debug.
	 *
	 * @param msg the msg
	 * @param params the params
	 */
	public static void debug(String msg, Object... params) {
		debug(msg, (Throwable) null, params);
	}

	/**
	 * Error.
	 *
	 * @param msg the msg
	 * @param params the params
	 */
	public static void error(String msg, Object... params) {
		error(msg, (Throwable) null, params);
	}

	/**
	 * Warn.
	 *
	 * @param msg the msg
	 * @param t the t
	 * @param params the params
	 */
	public static void warn(String msg, Throwable t, Object... params) {
		Object testName = TestParamsThreadPool.get(TestParamsThreadPool.TEST_NAME);
		if (params.toString() != "" && params.length > 0) {
			testName = params[0];
		}
		log(testName == null ? "General" : testName.toString(), t, Level.WARN, msg, params);
	}

	/**
	 * Info.
	 *
	 * @param msg the msg
	 * @param t the t
	 * @param params the params
	 */
	public static void info(String msg, Throwable t, Object... params) {
		Object testName = TestParamsThreadPool.get(TestParamsThreadPool.TEST_NAME);
		if (params.toString() != "" && params.length > 0) {
			testName = params[0];
		}
		log(testName == null ? "General" : testName.toString(), t, Level.INFO, msg, params);
	}

	/**
	 * Debug.
	 *
	 * @param msg the msg
	 * @param t the t
	 * @param params the params
	 */
	public static void debug(String msg, Throwable t, Object... params) {
		Object testName = TestParamsThreadPool.get(TestParamsThreadPool.TEST_NAME);
		if (params.toString() != "" && params.length > 0) {
			testName = params[0];
		}
		log(testName == null ? "General" : testName.toString(), t, Level.DEBUG, msg, params);
	}

	/**
	 * Error.
	 *
	 * @param msg the msg
	 * @param t the t
	 * @param params the params
	 */
	public static void error(String msg, Throwable t, Object... params) {
		Object testName = TestParamsThreadPool.get(TestParamsThreadPool.TEST_NAME);
		if (params.toString() != "" && params.length > 0) {
			testName = params[0];
		}
		log(testName == null ? "General" : testName.toString(), t, Level.ERROR, msg, params);
	}

	/**
	 * Log.
	 *
	 * @param testName the test name
	 * @param t the t
	 * @param level the level
	 * @param msg the msg
	 * @param params the params
	 */
	private static void log(String testName, Throwable t, Level level, String msg, Object... params) {
		// determine category
		StackTraceElement stackTraceElement;
		final StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
		
		/*if (stackTrace[2].getClassName().equals(testName)) {
			// was called from within this class
			if (stackTrace[3].getClassName().equals(testName)) {
				stackTraceElement = stackTrace[4];
			} else {
				stackTraceElement = stackTrace[3];
			}
		} else {
			stackTraceElement = stackTrace[2];
		}*/
		stackTraceElement = stackTrace[3];
		
		final String category = stackTraceElement.getClassName() + PERIOD + stackTraceElement.getMethodName();
		msg = "[" + category + "] " + msg;

		testName = testName.replace(' ', '_');

		int slfLogLevel = getSLFLogLevel(level);

		Logger logger = Logger.getLogger(testName);
		try {
			if (logger.getAppender(testName) == null) {
				FileAppender fileAppender = new FileAppender(new PatternLayout("%-5p %d %c [%t] %m%n"), "logs/" + testName + ".log");
				fileAppender.setName(testName);
				logger.addAppender(fileAppender);
			}
		} catch (IOException e) {
			System.err.println("failed writing to log!");
			e.printStackTrace();
		}

		org.slf4j.Logger slfLogger = LoggerFactory.getLogger(testName);
		log(slfLogger, slfLogLevel, msg, null, t);
	}

	/**
	 * Gets the SLF log level.
	 *
	 * @param level the level
	 * @return the SLF log level
	 */
	private static int getSLFLogLevel(Level level) {
		switch (level.toInt()) {
		case (Level.TRACE_INT):
			return LocationAwareLogger.TRACE_INT;
		case (Level.DEBUG_INT):
			return LocationAwareLogger.DEBUG_INT;
		case (Level.INFO_INT):
			return LocationAwareLogger.INFO_INT;
		case (Level.WARN_INT):
			return LocationAwareLogger.WARN_INT;
		case (Level.ERROR_INT):
			return LocationAwareLogger.ERROR_INT;
		}
		return 0;
	}

	/**
	 * Log.
	 *
	 * @param logger the logger
	 * @param level the level
	 * @param msg the msg
	 * @param argArray the arg array
	 * @param t the t
	 * @return the string
	 */
	private static String log(org.slf4j.Logger logger, int level, String msg, Object[] argArray, Throwable t) {
		FormattingTuple ft = MessageFormatter.arrayFormat(msg, argArray);
		msg = ft.getMessage();

		switch (level) {
		case LocationAwareLogger.TRACE_INT:
			if (t != null) {
				logger.trace(msg, t);
			} else {
				logger.trace(msg);
			}
			break;
		case LocationAwareLogger.DEBUG_INT:
			if (t != null) {
				logger.debug(msg, t);
			} else {
				logger.debug(msg);
			}
			break;
		case LocationAwareLogger.INFO_INT:
			if (t != null) {
				logger.info(msg, t);
			} else {
				logger.info(msg);
			}
			break;
		case LocationAwareLogger.WARN_INT:
			if (t != null) {
				logger.warn(msg, t);
			} else {
				logger.warn(msg);
			}
			break;
		case LocationAwareLogger.ERROR_INT:
			if (t != null) {
				logger.error(msg, t);
			} else {
				logger.error(msg);
			}
			break;
		default:
			throw new IllegalStateException("Level number " + level + " is not recognized.");
		}
		return msg;
	}
}
