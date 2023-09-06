package com.campudual.RedSocial;

import java.util.Date;

public class Text extends Post {
    protected String text;
    protected String title;

    public Text(String title, String text) {
        super();
        this.title = title;
        this.text = text;
    }

    public String toString() {
        return "-Tipo: Texto\n  -Título de tu post:  " + title + "\n  -El contenido de tu post:\n   " +
                text;
    }
}
