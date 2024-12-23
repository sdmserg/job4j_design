package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    private String name;

    private int children;

    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getCalendar() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name = '" + name + '\''
                + ", children = " + children
                + ", birthday = " + birthday
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && children == user.children
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
            Calendar birthday = Calendar.getInstance();
            birthday.set(1995, 5, 19);
            User firstUser = new User("Sergei", 2, birthday);
            User secondUser = new User("Sergei", 2, birthday);
            int firstHashCode = firstUser.hashCode();
            int firstHash = firstHashCode ^ (firstHashCode >>> 16);
            int firstBucket = firstHash & 15;
            int secondHashCode = secondUser.hashCode();
            int secondHash = secondHashCode ^ (secondHashCode >>> 16);
            int secondBucket = secondHash & 15;
            Map<User, Object> map = new HashMap();
            map.put(firstUser, new Object());
            map.put(secondUser, new Object());
            System.out.println(map);
            System.out.println(String.format("First User hash code: %s, First User hash: %s, First User bucket: %s",
                    firstHashCode, firstHash, firstBucket));
            System.out.println(String.format("Second User hash code: %s, Second User hash: %s, Second User bucket: %s",
                    secondHashCode, secondHash, secondBucket));
    }
}
