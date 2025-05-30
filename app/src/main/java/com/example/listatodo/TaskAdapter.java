package com.example.listatodo;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_list_item, parent, false);

        TextView title = convertView.findViewById(R.id.task_title);
        TextView dueDate = convertView.findViewById(R.id.task_due_date);
        ImageView icon = convertView.findViewById(R.id.task_status_icon);

        title.setText(task.getTitle());
        dueDate.setText(sdf.format(task.getDueDate()));

        int iconRes;
        boolean isEffectivelyOverdue = task.getStatus() == Task.Status.NOT_DONE && task.getDueDate().before(new java.util.Date());

        if (isEffectivelyOverdue) {
            iconRes = R.drawable.ic_overdue;
        } else if (task.getStatus() == Task.Status.DONE) {
            iconRes = R.drawable.ic_done;
        } else {
            iconRes = R.drawable.ic_not_done;
        }

        icon.setImageResource(iconRes);

        return convertView;
    }
}
