package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropReader {

        private static Utility.PropReader instance;
        private static final Object lock = new Object();

        private static String runOn;
        private static String gridHubAddress;
        private static String browser;
        private static String environment;
        private static String extentReportPath;
        private static String retry;

        private PropReader() {
        }

        public static Utility.PropReader GetInstance() {
            if (instance == null) {
                synchronized (lock) {
                    instance = new Utility.PropReader();
                    instance.LoadData();
                }
            }
            return instance;
        }

        private void LoadData() {
            Properties prop = new Properties();
            try {
                prop.load(new FileInputStream("configuration.properties"));
            } catch (IOException e) {
                System.out.println("Configuration properties file cannot be found");
            }
            runOn = prop.getProperty("RunOn");
            gridHubAddress = prop.getProperty("GridHubAddress");
            browser = prop.getProperty("Browser");
            environment = prop.getProperty("Environment");
            extentReportPath = prop.getProperty("ExtentReportPath");
            retry = prop.getProperty("Retry");
        }

        public static String getRunOn(){
            return runOn;
        }

        public static String getBrowser(){
            return browser;
        }

        public static String getEnvironment(){
            return environment;
        }

        public static String getExtentReportPath() {return extentReportPath;}

        public static boolean isRetryEnabled(){ return retry.equals("enable") ? true : false; }

        public static String getGridHubAddress(){ return gridHubAddress; }

}
