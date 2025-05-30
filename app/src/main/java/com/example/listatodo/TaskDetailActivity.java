package com.example.listatodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TaskDetailActivity extends Activity {
    private Task task;
    private int position;
    private Button doneButton, notDoneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        // Inicjalizacja widoków
        TextView title = findViewById(R.id.detail_title);
        TextView description = findViewById(R.id.detail_description);
        TextView dueDate = findViewById(R.id.detail_due_date);
        doneButton = findViewById(R.id.button_done);
        notDoneButton = findViewById(R.id.button_not_done);

        // Pobranie danych z Intent
        Intent intent = getIntent();
        task = (Task) intent.getSerializableExtra("task");
        position = intent.getIntExtra("position", -1);

        // Ustawienie danych zadania
        title.setText(task.getTitle());
        description.setText(task.getDescription());

        // Formatowanie daty
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
        dueDate.setText(sdf.format(task.getDueDate()));

        // Sprawdzenie czy zadanie jest przeterminowane
        if (task.isOverdue()) {
            task.setStatus(Task.Status.OVERDUE);
        }

        updateButtonState();

        // Obsługa przycisków
        doneButton.setOnClickListener(v -> {
            task.setStatus(Task.Status.DONE);
            returnResult();
        });

        notDoneButton.setOnClickListener(v -> {
            task.setStatus(Task.Status.NOT_DONE);
            returnResult();
        });
    }

    private void updateButtonState() {
        boolean isOverdue = task.getStatus() == Task.Status.OVERDUE;
        doneButton.setEnabled(!isOverdue);
        notDoneButton.setEnabled(!isOverdue);

        // Wizualna informacja o przeterminowaniu
        if (isOverdue) {
            TextView dueDate = findViewById(R.id.detail_due_date);
            dueDate.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void returnResult() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("task", task);
        resultIntent.putExtra("position", position);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}