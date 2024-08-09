package com.nhnacademy.student;

import com.nhnacademy.student.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ControllerFactory {

    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void init(Set<Class<?>> c) {
        //todo beanMap에 key = method + servletPath, value = Controller instance

        for (Class<?> clazz : c) {
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                String path = requestMapping.value();
                String method = requestMapping.method().name();

                try {
                    Object controllerInstance = clazz.getDeclaredConstructor().newInstance();
                    beanMap.put(method + path, controllerInstance);

                } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public Object getBean(String method, String path) {
        //todo beanMap 에서 method+servletPath을 key로 이용하여 Controller instance를 반환합니다.
        return beanMap.get(method + path);
    }
}
