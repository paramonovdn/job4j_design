package ru.job4j.collection;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
    public static void main(String[] args) {
        User user1 = new User("Stivie", 1, new GregorianCalendar(1990, 10, 20));
        User user2 = new User("Stivie", 1, new GregorianCalendar(1990, 10, 20));
        Map<User, Object> map = new HashMap<>(16);
        map.put(user1, new Object());
        map.put(user2, new Object());
        String i = "";
        Iterator<Map.Entry<User, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<User, Object> pair = it.next();
            System.out.println(pair.getKey() + ", " + pair.getValue());
        }
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int backet1 = hash1 & 15;
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int backet2 = hash2 & 15;
        System.out.println();
        System.out.printf("user1 - хешкод: %s, хеш: %s, бакет: %s", hashCode1, hash1, backet1);
        System.out.println();
        System.out.printf("user2 - хешкод: %s, хеш: %s, бакет: %s", hashCode2, hash2, backet2);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
