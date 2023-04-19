package com.online.library.domain.utils;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Component
public class Utils {

    private final String LEGACY_SECURITY_TOKEN = "LEGACY_SECURITY_TOKEN";
    private final Environment environment;

    public Utils(Environment environment) {
        this.environment = environment;
    }

    public String getLegacySecurityToken () {
        return environment!=null && environment.getProperty(LEGACY_SECURITY_TOKEN)!=null ? environment.getProperty(LEGACY_SECURITY_TOKEN).toString() : EMPTY;
    }
}
