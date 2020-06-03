package com.poduzdikovaolga.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateHandlerImpl implements UpdateHandler {

    private final TelegramBot telegramBot;
    @Override
    public void handleUpdate(Update update) {
        Message message = update.message();

        Long chatId = message.chat().id();
        String text = message.text();

        log.info("Chat id:" + chatId);
        log.info("Text : " + text);

        int indexOf = text.indexOf(' ');

        if (indexOf > -1) {
            String queryString = text.substring(indexOf);

            SendMessage sendTranslation = new SendMessage(chatId, "kek");
            telegramBot.execute(sendTranslation);

        }
    }
}
