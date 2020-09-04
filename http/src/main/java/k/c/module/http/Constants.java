package k.c.module.http;

public class Constants {
    public final static String SERVICE_LOGGER_TAG = "SERVICE_LOGGER";
    public static final class Http {
        public static final Boolean isRelease = false;
        public static final String HTTP_DISGW_DOMAIN_SERVER = (isRelease) ? "https://app-api.taiwantaxi.com.tw/": "https://app-apitest.taiwantaxi.com.tw/";

        public static final int SUCCESS = 0x0000;
    }
}

