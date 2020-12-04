package ru.otus.listener.homework;

import ru.otus.Message;
import ru.otus.listener.Listener;

public class ListenerMessageHistory implements Listener {

    @Override
    public void onUpdated(Message oldMsg, Message newMsg) {
        MessageHistoryStorage.getInstance().addMessageHistory(oldMsg, newMsg);
    }
}
