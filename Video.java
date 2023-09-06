package com.campudual.RedSocial;

import java.util.Date;

public class Video extends Post {
    protected String title;
    protected String quality;
    protected int durationInSeconds;

    public Video(String title, String quality, int durationInSeconds) {
        super();
        this.title = title;
        this.quality = quality;
        this.durationInSeconds = durationInSeconds;
    }

    public String toString() {
        return "Tipo: Video \n Título: " + title + "\n Calidad: " + quality +
                "\n Duración: " + durationInSeconds;
    }
}
