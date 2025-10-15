package com.soundinstantz.util;

public final class Const {
    public static final String[] AUTH_WHITELIST = {
            "/api/**",
    };

    public static class ResultCode {
        public static final boolean SUCCESS = true;
        public static final boolean ERROR = false;
    }

    public static class SoundEventType{
        public static final String PLAY = "PLAY";
        public static final String DOWNLOAD = "DOWNLOAD";
        public static final String LIKE = "LIKE";
    }
}
