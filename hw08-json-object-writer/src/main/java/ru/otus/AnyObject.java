package ru.otus;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class AnyObject {
    private boolean booleanField;
    private int intField;
    private String stringField;
    private Integer IntegerField;
    private int[] arrayField;
    private Collection collectionField;

    public AnyObject(Boolean booleanField, int intField, String stringField, Integer integerField, int[] arrayField, Collection collectionField) {
        this.booleanField = booleanField;
        this.intField = intField;
        this.stringField = stringField;
        IntegerField = integerField;
        this.arrayField = arrayField;
        this.collectionField = collectionField;
    }

    public boolean isBooleanField() {
        return booleanField;
    }

    public void setBooleanField(boolean booleanField) {
        this.booleanField = booleanField;
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public Integer getIntegerField() {
        return IntegerField;
    }

    public void setIntegerField(Integer integerField) {
        IntegerField = integerField;
    }

    public int[] getArrayField() {
        return arrayField;
    }

    public void setArrayField(int[] arrayField) {
        this.arrayField = arrayField;
    }

    public Collection getCollectionField() {
        return collectionField;
    }

    public void setCollectionField(Collection collectionField) {
        this.collectionField = collectionField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnyObject anyObject = (AnyObject) o;
        return booleanField == anyObject.booleanField &&
                intField == anyObject.intField &&
                Objects.equals(stringField, anyObject.stringField) &&
                Objects.equals(IntegerField, anyObject.IntegerField) &&
                Arrays.equals(arrayField, anyObject.arrayField) &&
                Objects.equals(collectionField, anyObject.collectionField);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(booleanField, intField, stringField, IntegerField, collectionField);
        result = 31 * result + Arrays.hashCode(arrayField);
        return result;
    }
}
