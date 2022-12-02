package me.hsgamer.edublock.rs.samplegenerator;

import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtil {
    private static final Genson GENSON = new GensonBuilder()
            .failOnMissingProperty(true)
            .failOnNullPrimitive(true)
            .create();

    public static String toJson(Object object) {
        return GENSON.serialize(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return GENSON.deserialize(json, clazz);
    }
}