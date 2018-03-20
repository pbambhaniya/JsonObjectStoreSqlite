package com.multipz.maindemo;

/**
 * Created by Admin on 20-03-2018.
 */

public class Note {

    public static final String TABLE_NAME = "status";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_Status = "des";


    private int id;
    private String note;
    private String timestamp;


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_Status + " TEXT,"
                    + ")";

    public Note() {
    }

    public Note(int id, String note, String timestamp) {
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
