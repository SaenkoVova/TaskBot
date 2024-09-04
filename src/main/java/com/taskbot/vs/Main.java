package com.taskbot.vs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {

            DatabaseConnection.getInstance();
            SimpleEchoBot simpleEchoBot = new SimpleEchoBot();
            simpleEchoBot.init();

        } catch (TelegramApiException e) {

            logger.error("Bot initialization failed: {}", e.getMessage());

        } catch (SQLException e) {

            logger.error("DB connection failed: {}", e.getMessage());

        }
    }

    public static void startApp() {

    }
}