package com.example.coder.jiandan_md.model;

/**
 * Created by coder on 16/9/14.
 */
public class HTMLElement {

    public enum ElementType {

        image,text,link,heade;
    }

    private StringBuffer textBuffer = new StringBuffer();

    private String link;

    private ElementType type;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public StringBuffer getTextBuffer() {
        return textBuffer;
    }

    public void setTextBuffer(StringBuffer textBuffer) {
        this.textBuffer = textBuffer;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }
}
