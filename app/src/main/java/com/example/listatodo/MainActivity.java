package com.example.listatodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import java.util.*;

public class MainActivity extends Activity {

    private ArrayList<Task> taskList;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.task_list_view);
        taskList = getTasks();
        adapter = new TaskAdapter(this, taskList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Task task = taskList.get(position);
            Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
            intent.putExtra("task", task);
            intent.putExtra("position", position);
            startActivityForResult(intent, 1);
        });
    }

    // Odbiór zmodyfikowanego zadania
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            Task updatedTask = (Task) data.getSerializableExtra("task");
            int position = data.getIntExtra("position", -1);
            if (position >= 0) {
                taskList.set(position, updatedTask);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private ArrayList<Task> getTasks() {
        return new ArrayList<>(Arrays.asList(
                new Task("Zakupy", "Mleko, chleb, jajka", "13.05.2025 09:00", Task.Status.NOT_DONE),
                new Task("Wizyta u dentysty", "Przegląd kontrolny", "14.05.2025 14:00", Task.Status.NOT_DONE),
                new Task("Opłata rachunków", "Prąd i internet", "15.05.2025 12:00", Task.Status.NOT_DONE),
                new Task("Trening", "Bieganie 5km", "15.05.2025 18:30", Task.Status.NOT_DONE),
                new Task("Oddanie książek", "Biblioteka miejska", "16.05.2025 16:00", Task.Status.DONE),
                new Task("Spotkanie rodzinne", "Urodziny mamy", "15.05.2025 21:35", Task.Status.NOT_DONE),
                new Task("Nauka języka", "Lekcja angielskiego", "18.05.2025 10:00", Task.Status.DONE),
                new Task("Wizyta u weterynarza", "Szczepienie psa", "20.05.2025 13:30", Task.Status.NOT_DONE),
                new Task("Zakupy ogrodowe", "Nasiona i nawozy", "21.05.2025 10:00", Task.Status.DONE),
                new Task("Wyjazd weekendowy", "Pakowanie walizek", "23.05.2025 19:00", Task.Status.NOT_DONE),
                new Task("Kino", "Nowy film Marvela", "24.05.2025 20:00", Task.Status.NOT_DONE),
                new Task("Sprzątanie", "Generalne porządki", "25.05.2025 11:00", Task.Status.NOT_DONE),
                new Task("Badanie krwi", "Morfologia na czczo", "27.05.2025 08:00", Task.Status.NOT_DONE),
                new Task("Spotkanie z przyjaciółmi", "Grill w parku", "28.05.2025 16:00", Task.Status.NOT_DONE),
                new Task("Koncert", "Ulubiony zespół", "01.06.2025 19:30", Task.Status.NOT_DONE),
                new Task("Piknik", "Park miejski", "02.06.2025 12:00", Task.Status.NOT_DONE),
                new Task("Warsztaty kulinarne", "Kuchnia włoska", "03.06.2025 18:00", Task.Status.NOT_DONE),
                new Task("Prezent dla siostry", "Urodziny", "04.06.2025 09:00", Task.Status.NOT_DONE),
                new Task("Naprawa roweru", "Wymiana opon", "05.06.2025 16:30", Task.Status.NOT_DONE),
                new Task("Wizyta u rodziców", "Weekend u rodziny", "06.06.2025 17:00", Task.Status.NOT_DONE),
                new Task("Maraton filmowy", "Trylogia Władca Pierścieni", "07.06.2025 14:00", Task.Status.NOT_DONE),
                new Task("Zakupy letnie", "Nowe ubrania", "08.06.2025 11:00", Task.Status.NOT_DONE)
        ));
    }
}
