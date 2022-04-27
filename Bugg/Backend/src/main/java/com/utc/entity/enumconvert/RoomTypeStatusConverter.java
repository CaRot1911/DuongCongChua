package com.utc.entity.enumconvert;

import com.utc.entity.RoomType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoomTypeStatusConverter implements AttributeConverter<RoomType.TypeRoom,String> {
    @Override
    public String convertToDatabaseColumn(RoomType.TypeRoom typeRoom) {
        if (typeRoom == null){
            return null;
        }
        return typeRoom.getStatus();
    }

    @Override
    public RoomType.TypeRoom convertToEntityAttribute(String s) {
        if(s == null){
            return null;
        }
        return RoomType.TypeRoom.toEnum(s);
    }
}
