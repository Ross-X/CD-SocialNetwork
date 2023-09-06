package com.campudual.RedSocial;
import java.util.Date;
public class Image extends Post {
    protected String title;
    protected int width;
    protected int high;

    public Image( String title, int width, int high) {
        super();
        this.title = title;
        this.width = width;
        this.high = high;
    }
    public String toString() {
        return "Tipo: Imagen \n Título: " + title + "\n Ancho: " + width +
                "\n Alto: " + high;
    }

}
