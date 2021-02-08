package ru.otus.jdbc.mapper;

import ru.otus.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final Class<T> clazz;
    private final List<Field> allFields;
    private final Field idField;

    public EntityClassMetaDataImpl(Class<T> clazz) {
        this.clazz = clazz;
        this.allFields = Arrays.asList(clazz.getDeclaredFields().clone());
        this.idField = allFields.stream().filter(field -> field.isAnnotationPresent(Id.class)).findAny().orElseThrow(EntityClassMetaDataException::new);
    }

    @Override
    public String getName() {
        return clazz.getSimpleName().toLowerCase();
    }

    @Override
    public Constructor<T> getConstructor() {
        return ((Constructor<T>) clazz.getDeclaredConstructors()[0]);
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return allFields.stream().filter(field -> !(field.equals(idField))).collect(Collectors.toList());
    }
}