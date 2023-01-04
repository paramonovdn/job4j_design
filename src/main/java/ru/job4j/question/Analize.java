package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        Map<Integer, User> previosMap = new HashMap<Integer, User>();
        Map<Integer, User> currentMap = new HashMap<Integer, User>();

        for (User user : previous) {
            previosMap.put(user.getId(), user);
        }

        for (User user : current) {
            currentMap.put(user.getId(), user);
        }

        for (Map.Entry<Integer, User> entry : currentMap.entrySet()) {
            Integer key = entry.getKey();
            User value = entry.getValue();
            if (!previosMap.containsKey(key)) {
                added++;
            }
            if (previosMap.containsKey(key) && !previosMap.get(key).equals(value)) {
                changed++;
            }
        }

        for (Map.Entry<Integer, User> entry : previosMap.entrySet()) {
            Integer key = entry.getKey();
            if (!currentMap.containsKey(key)) {
                deleted++;
            }
        }

        return new Info(added, changed, deleted);
    }

}