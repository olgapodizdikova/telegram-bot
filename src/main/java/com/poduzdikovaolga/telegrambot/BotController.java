package com.poduzdikovaolga.telegrambot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.poduzdikovaolga.telegrambot.service.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BotController {

    private final UpdateHandler handler;

    private final TelegramBot telegramBot;

    @RequestMapping("/poll")
    public void poll() {

        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);
        GetUpdatesResponse getUpdatesResponse = telegramBot.execute(getUpdates);

        List<Update> updates = getUpdatesResponse.updates();
        updates.forEach(this::webhook);
    }

    @PostMapping(value = "/webhook")
    public void webhook(@RequestBody Update update) {
        handler.handleUpdate(update);
    }
}
