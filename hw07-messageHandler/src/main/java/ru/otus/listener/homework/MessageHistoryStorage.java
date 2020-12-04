package ru.otus.listener.homework;

import ru.otus.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageHistoryStorage {
    private static MessageHistoryStorage instance;
    private static List<MessageHistory> messageHistoryList;

    private MessageHistoryStorage() {
        messageHistoryList = new ArrayList<>();
    }

    public static MessageHistoryStorage getInstance() {
        if (instance == null) {
            instance = new MessageHistoryStorage();
        }
        return instance;
    }

    public void addMessageHistory(Message oldMsg, Message newMsg) {
        messageHistoryList.add(new MessageHistory(oldMsg, newMsg));
    }

    public ArrayList<MessageHistory> getMessageHistoryList() {
        return new ArrayList<>(messageHistoryList);
    }
}
