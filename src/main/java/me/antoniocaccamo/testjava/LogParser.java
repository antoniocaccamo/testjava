package me.antoniocaccamo.testjava;

/**
 * @author antoniocaccamo  on 19/10/2020
 */
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.StringReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class LogParser {

    private static final Logger logger = Logger.getLogger(LogParser.class.getName());

    public static final String ENTRY   = "entry";
    public static final String MESSAGE = "message";
    public static final String ID   = "id";

    public static Collection<Integer> getIdsByMessage(String xml, String message) throws Exception {

        LogEntryMessageHandler entryMessage = new LogEntryMessageHandler(message);

        SAXParserFactory.newInstance().newSAXParser()
                .parse( new InputSource(new StringReader(xml)) , entryMessage);

        return entryMessage.getList();
    }

    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<log>\n" +
                "    <entry id=\"1\">\n" +
                "        <message>Application started</message>\n" +
                "    </entry>\n" +
                "    <entry id=\"2\">\n" +
                "        <message>Application ended</message>\n" +
                "    </entry>\n" +
                "</log>"
        ;

        Collection<Integer> ids = getIdsByMessage(xml, "Application ended");
        for(int id: ids)
            logger.info( String.format("%d",id));
    }

    private static class LogEntryMessageHandler extends DefaultHandler {

        List<Integer> list = new LinkedList<Integer>();

        private final String message;
        private Integer entryId = null;

        private String characters;
        private Boolean isMessage = Boolean.FALSE;

        public LogEntryMessageHandler(String message) {
            this.message = message;
        }

        @Override
        public void startDocument() throws SAXException {
            logger.fine("startDocument");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if ( ENTRY.equals(qName) ) {
                entryId = Integer.parseInt(attributes.getValue("id"));
            }
            if ( MESSAGE.equals(qName)) {
                this.isMessage = Boolean.TRUE;
            }
            logger.fine("startElement -> " + qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            logger.fine("characters");
            characters = null;
            if ( isMessage ) {
                characters = new String(ch, start, length);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            logger.fine("endElement " + qName);
            if ( MESSAGE.equals(qName) ) {
                this.isMessage = Boolean.FALSE;
                logger.fine(String.format("### message : %s", characters));
                if ( this.message.equals(characters))
                    list.add(this.entryId);
            }
        }

        public List<Integer> getList() {
            return list;
        }


    }
}