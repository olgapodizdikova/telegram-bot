package com.poduzdikovaolga.telegrambot.service;

import com.pengrad.telegrambot.model.Update;

public interface UpdateHandler {

    void handleUpdate(Update update);
}
