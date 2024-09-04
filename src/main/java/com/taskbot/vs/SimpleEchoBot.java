package com.taskbot.vs;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.List;

public class SimpleEchoBot implements LongPollingSingleThreadUpdateConsumer {

    private final Logger logger = LoggerFactory.getLogger(SimpleEchoBot.class);

    private final String BOT_TOKEN = "7157347880:AAFZzBJdQY_ISMYZV5ooFcL_qedmGwwD85s";

    private final TelegramClient telegramClient = new OkHttpTelegramClient(BOT_TOKEN);

    public void init() throws TelegramApiException {
        TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
        botsApplication.registerBot(BOT_TOKEN, new SimpleEchoBot());
        logger.info("Bot initialized");
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            logger.info("New message received: " + update.getMessage().getText());

            SendMessage message = SendMessage.builder()
                    .chatId(update
                            .getMessage()
                            .getChatId()
                    )
                    .replyMarkup(getInlineKeyboardMarkup())
                    .build();

            try {
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private static @NotNull InlineKeyboardMarkup getInlineKeyboardMarkup() {
        InlineKeyboardButton createReminderButton = new InlineKeyboardButton("Create Reminder");
        createReminderButton.setCallbackData("update_msg_text");

        List<InlineKeyboardButton> rowButtons = new ArrayList<>();
        rowButtons.add(createReminderButton);

        InlineKeyboardRow inlineKeyboardRow = new InlineKeyboardRow(rowButtons);

        List<InlineKeyboardRow> inlineKeyboardRows = new ArrayList<>();
        inlineKeyboardRows.add(inlineKeyboardRow);

        return new InlineKeyboardMarkup(inlineKeyboardRows);
    }
}
