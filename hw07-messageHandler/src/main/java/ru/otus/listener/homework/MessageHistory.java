package ru.otus.listener.homework;

import ru.otus.Message;

public class MessageHistory {
    private final Message oldMessage;
    private final Message newMessage;

    public MessageHistory(Message oldMessage, Message newMessage) {
        this.oldMessage = oldMessage;
        this.newMessage = newMessage;
    }

    public Message getOldMessage() {
        return oldMessage;
    }

    public Message getNewMessage() {
        return newMessage;
    }

    @Override
    public String toString() {
        return "MessageHistory{" +
                "oldMessage=" + oldMessage +
                ", newMessage=" + newMessage +
                '}';
    }
}
