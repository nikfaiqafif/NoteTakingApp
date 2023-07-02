package com.example.notetakerapp;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Note {
    private String id;
    private String title;
    private String subtitle;
    private String imageBase64;

    public Note() {
        // Default constructor required for calls to DataSnapshot.getValue(Note.class)
    }

    public Note(String id, String title, String subtitle, String imageBase64) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.imageBase64 = imageBase64;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public BitmapDrawable getImageDrawable() {
        if (imageBase64 != null && !imageBase64.isEmpty()) {
            byte[] decodedString = Base64.decode(imageBase64, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return new BitmapDrawable(bitmap);
        } else {
            return null;
        }
    }
}
