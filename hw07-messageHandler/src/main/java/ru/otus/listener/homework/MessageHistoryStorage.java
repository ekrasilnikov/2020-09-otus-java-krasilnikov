package ru.otus.listener.homework;

import ru.otus.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageHistoryStorage {
    private final List<MessageHistory> messageHistoryList;

    public MessageHistoryStorage() {
        messageHistoryList = new ArrayList<>();
    }

    public void addMessageHistory(Message oldMsg, Message newMsg) {
        messageHistoryList.add(new MessageHistory(oldMsg, newMsg));
    }

    public ArrayList<MessageHistory> getMessageHistoryList() {
        return new ArrayList<>(messageHistoryList);
    }
}
