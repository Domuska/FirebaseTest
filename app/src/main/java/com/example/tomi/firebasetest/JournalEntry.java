package com.example.tomi.firebasetest;

/**
 * POJO representing a single entry in the journal
 * Class used in conjunction with Firebase, Firebase can
 * write the POJO to the database if the class is supplied with
 * an empty constructor and the proper getters for variables
 * to be saved to Firebase
 */
public class JournalEntry {

    private String author;
    private String bodyText;
    private String timeStamp;
    private String title;
    private String uid;

    //empty constructor required by Firebase
    public JournalEntry(){}

    public JournalEntry(String author, String bodyText, String timeStamp, String title, String uid) {
        this.author = author;
        this.bodyText = bodyText;
        this.timeStamp = timeStamp;
        this.title = title;
        this.uid = uid;
    }


    public String getAuthor() {
        return author;
    }

    public String getBodyText() {
        return bodyText;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public String getUid() {
        return uid;
    }
}
