package me.antoniocaccamo.testjava;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author antoniocaccamo  on 19/10/2020
 */
public class Paragraph {

    private static final Logger logger = Logger.getLogger(Paragraph.class.getName());

    public static String changeFormat(String paragraph) {

        Pattern pattern =Pattern.compile("\\d{3}-\\d{2}-\\d{4}");
        Matcher matcher = pattern.matcher(paragraph);

        StringBuffer sb = new StringBuffer();

        int previous = 0;

        while ( matcher.find() ) {
            logger.fine(String.format("start [%d] end [%d]", matcher.start(),  matcher.end()));
             String[] ss = paragraph.substring( matcher.start() , matcher.end() ).split("-");
             sb.append(paragraph.substring(previous, matcher.start()));
             sb.append(ss[0]).append("/")
               .append(ss[2]).append("/")
               .append(ss[1]);
             previous = matcher.end();
        }
        sb.append(paragraph.substring(previous));
        return sb.toString();

    }

    public static void main(String[] args) {

        String paragraph =
                "Please quote your policy number: 112-39-8552." +
                "Please quote your policy number: 115-39-8555." ;

        logger.info( String.format("before: %s ", paragraph));
        logger.info( String.format("after : %s ", changeFormat(paragraph)));
    }
}
