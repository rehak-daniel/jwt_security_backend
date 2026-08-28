package com.securityproject.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EndpointPath {

    private static final String BASE_PATH = "/api";

    public static class User {

        public static final String USER__REGISTER = BASE_PATH + "/user/register";
        public static final String USER__LOGIN = BASE_PATH + "/user/login";
    }
}
