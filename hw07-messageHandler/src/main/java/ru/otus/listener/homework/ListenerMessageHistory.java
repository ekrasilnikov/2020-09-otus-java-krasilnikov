package ru.otus.listener.homework;

import ru.otus.Message;
import ru.otus.listener.Listener;

public class ListenerMessageHistory implements Listener {
    private final MessageHistoryStorage messageHistoryStorage;

    public ListenerMessageHistory(MessageHistoryStorage storage) {
        this.messageHistoryStorage = storage;
    }

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        messageHistoryStorage.addMessageHistory(oldMsg, newMsg);
    }
}
