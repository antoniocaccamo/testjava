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

public class Folders {

    private static final String ATR = "name";

    public static Collection<String> folderNames(String xml, char startingLetter) throws Exception {

        FolderHandler folderHandler = new FolderHandler(startingLetter);

        SAXParserFactory.newInstance().newSAXParser()
                .parse( new InputSource(new StringReader(xml)) , folderHandler);

        return folderHandler.getList();
    }




    public static void main(String[] args) throws Exception {
        String xml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<folder name=\"c\">" +
                        "<folder name=\"program files\">" +
                        "<folder name=\"uninstall information\" />" +
                        "</folder>" +
                        "<folder name=\"users\" />" +
                        "</folder>";

        Collection<String> names = folderNames(xml, 'u');
        for(String name: names)
            System.out.println(name);
    }

    private static class FolderHandler extends DefaultHandler {

        List<String> list = new LinkedList<String>();

        private final String startingLetter;

        public FolderHandler(char startingLetter) {
            this.startingLetter = Character.toString(startingLetter);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            String atr = attributes.getValue(ATR);
            if (atr != null && !"".equals(atr) && atr.startsWith(startingLetter))
                list.add(atr);
        }

        public List<String> getList() {
            return list;
        }
    }
}
