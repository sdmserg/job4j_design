package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ChessPlayerJsonDemo {

    public static void main(String[] args) {
        final ChessPlayer chessPlayer = new ChessPlayer(
                "Magnus Carlsen", 34, true,
                new Contact("+7(924)111-111-11-11"),
                new String[] {"blitz", "rapid", "classical"}
        );
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(chessPlayer));
        String chessPlayerJson =
                "{"
                      + "\"name\":\"Magnus Carlsen\","
                      + "\"age\":34,"
                      + "\"isActive\":true,"
                      + "\"contact\":"
                      + "{"
                      + "\"phone\":\"+7(924)111-111-11-11\""
                      + "},"
                      + "\"formats\":"
                      + "[\"blitz\",\"rapid\",\"classical\"]"
                      + "}";
        ChessPlayer playerFromJson = gson.fromJson(chessPlayerJson, ChessPlayer.class);
        System.out.println(playerFromJson);
    }
}
