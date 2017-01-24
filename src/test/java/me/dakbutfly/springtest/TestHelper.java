package me.dakbutfly.springtest;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

import static org.junit.Assert.fail;

/**
 * Created by khk on 2017-01-24.
 */
public class TestHelper {
    public static void injectionField(Object taget, Class obj, Object injectObj) throws IllegalAccessException {
        Class<?> tagetClass = taget.getClass();
        Field[] fields = tagetClass.getDeclaredFields();
        for (Field field : fields) {
            //anntation check
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation == null) continue;
            //field class check
            if (!field.getType().equals(obj)) continue;
            field.setAccessible(true);
            field.set(taget, injectObj);
        }
    }
}
