package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("log trace");
        LOG.debug("log debug");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
