package ru.otus.listener.homework;

import ru.otus.Message;

public class MessageHistory {
    private final Message oldMessage;
    private final Message newMessage;

    public MessageHistory(Message oldMessage, Message newMessage) {
        this.oldMessage = oldMessage.getCopy();
        this.newMessage = newMessage.getCopy();
    }

    public Message getOldMessage() {
        return oldMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }

    @Override
    public String toString() {
        return "History Rec: " +
                "oldMessage=" + oldMessage +
                ", newMessage=" + newMessage;
    }
}
