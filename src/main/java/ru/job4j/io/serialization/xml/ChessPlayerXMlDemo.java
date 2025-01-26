package ru.job4j.io.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import ru.job4j.serialization.json.ChessPlayer;
import ru.job4j.serialization.json.Contact;

public class ChessPlayerXMlDemo {

    public static void main(String[] args) throws Exception {
        ChessPlayer chesPlayer = new ChessPlayer(
                "Magnus Carlsen",
                34,
                true,
                new Contact("+7(924)111-111-11-11"),
                new String[] {"blitz", "rapid", "classical"}
        );
        JAXBContext context = JAXBContext.newInstance(ChessPlayer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xmlFile = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(chesPlayer, writer);
            xmlFile = writer.getBuffer().toString();
            System.out.println(xmlFile);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xmlFile)) {
            ChessPlayer playerFromXml = (ChessPlayer) unmarshaller.unmarshal(reader);
            System.out.println(playerFromXml);
        }
    }
}
