package com.nhnacademy.student;

import com.nhnacademy.student.domain.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try {
//            Class<?> userClass = Class.forName(User.class.getName());
//            Constructor<?> constructor = userClass.getConstructor();
//
//            User user = (User) constructor.newInstance();
//            System.out.println(user);

//            Constructor cUser = Class.forName(User.class.getName()).getConstructor(String.class, Integer.TYPE);
//            User user1 = (User) cUser.newInstance("marco", 20);
//            System.out.println(user1);


            Class clazz = Class.forName(User.class.getName());
            Object user = clazz.getDeclaredConstructor().newInstance();
//            Method setUserNameMethod = clazz.getDeclaredMethod("setUserName", String.class);
//            setUserNameMethod.invoke(user, "NHN 아카데미");
//
//            Method getUserNameMethod = clazz.getDeclaredMethod("getUserName");
//            String userName = (String) getUserNameMethod.invoke(user);
//
//            Method setUserAgeMethod = clazz.getDeclaredMethod("setUserAge", Integer.TYPE);
//            setUserAgeMethod.invoke(user, 30);
//
//            Method getUserAgeMethod = clazz.getDeclaredMethod("getUserAge");
//            int userAge = (int) getUserAgeMethod.invoke(user);
//
//            System.out.println("userName:" + userName);
//            System.out.println("userAge:" + userAge);

            Field userNameField = clazz.getDeclaredField("userName");
            userNameField.setAccessible(true);
            userNameField.set(user, "marco");

            String userName = (String) userNameField.get(user);

            Field userAgeField = clazz.getDeclaredField("userAge");
            userAgeField.setAccessible(true);
            userAgeField.set(user, 30);

            int userAge = userAgeField.getInt(user);
            System.out.println("userName:" + userName);
            System.out.println("userAge:" + userAge);



        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
