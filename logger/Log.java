package com.martinmimiGames.util.logger;

public class Log {

    public static void Log(String tag, String information) {
        if (LoggerConfig.ON) {
            android.util.Log.w(tag, information);
        }
    }

}
