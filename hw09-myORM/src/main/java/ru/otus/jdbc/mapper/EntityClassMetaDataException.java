package ru.otus.jdbc.mapper;

public class EntityClassMetaDataException extends RuntimeException {
    public EntityClassMetaDataException() {
        super("Field with \"Id\" annotation not found");
    }
}
