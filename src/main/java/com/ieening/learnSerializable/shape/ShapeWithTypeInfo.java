package com.ieening.learnSerializable.shape;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CircleWithTypeInfo.class, name = "circle"),
        @JsonSubTypes.Type(value = SquareWithTypeInfo.class, name = "square"),
})
public class ShapeWithTypeInfo {

}