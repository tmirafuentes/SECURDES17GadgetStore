package edu.dlsu.securdeproject.security.escapeInput;
import org.springframework.web.util.HtmlUtils;


public class InputEscaper {

    public InputEscaper()
    {
    }

    public String inputToBeEscaped(String text)
    {
        return HtmlUtils.htmlEscape(text);
    }

    public String inputToBeReverted(String text)
    {
        return HtmlUtils.htmlUnescape(text);
    }
}
