package ru.job4j.serialization.json;

import java.util.Arrays;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "chessplayer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChessPlayer {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int age;

    private boolean isActive;

    private Contact contact;

    @XmlElementWrapper(name = "formats")
    private String[] formats;

    public ChessPlayer() {

    }

    public ChessPlayer(String name, int age, boolean isActive,
                       Contact contact, String[] formats) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.contact = contact;
        this.formats = formats;
    }

    @Override
    public String toString() {
        return "ChessPlayer{"
                + "name=" + name
                + ", age=" + age
                + ", isActive=" + isActive
                + ", contact=" + contact
                + ", formats=" + Arrays.toString(formats)
                + "}";
    }
}
