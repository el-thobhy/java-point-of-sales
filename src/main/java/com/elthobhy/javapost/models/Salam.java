package com.elthobhy.javapost.models;

public class Salam {
    private final long id;
    private final String content;
    
    public Salam(long _id, String _content) {
        id = _id;
        content = _content;
    }

    public long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
}
