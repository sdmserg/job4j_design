package ru.job4j.serialization.json;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChessPlayerJson {

    public static void main(String[] args) {
        final ChessPlayer chessPlayer = new ChessPlayer(
                "Magnus Carlsen", 34, true,
                new Contact("+7(924)111-111-11-11"),
                new String[] {"blitz", "rapid", "classical"}
        );
        JSONObject contactObject = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        List<String> list = List.of("blitz", "rapid", "classical");
        JSONArray formats = new JSONArray(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", chessPlayer.getName());
        jsonObject.put("age", chessPlayer.getAge());
        jsonObject.put("active", chessPlayer.isActive());
        jsonObject.put("contact", contactObject);
        jsonObject.put("formats", formats);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(chessPlayer).toString());
    }
}
