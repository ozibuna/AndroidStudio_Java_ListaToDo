package com.example.listatodo;
import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class Task implements Serializable {
    public enum Status {
        NOT_DONE, DONE, OVERDUE
    }

    private String title;
    private String description;
    private Date dueDate;
    private Status status;

    public Task(String title, String description, String dueDateString, Status status) {
        this.title = title;
        this.description = description;
        this.dueDate = parseDate(dueDateString);
        this.status = status;
    }

    @SuppressLint("NewApi")
    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
            return sdf.parse(dateString);
        } catch (ParseException e) {
            // Jeśli data jest niepoprawna, używamy bieżącej daty
            return new Date();
        }
    }

    // Metoda sprawdzająca czy zadanie jest przeterminowane
    public boolean isOverdue() {
        return status == Status.NOT_DONE && dueDate.before(new Date());
    }

    // Gettery i settery
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Date getDueDate() { return dueDate; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}