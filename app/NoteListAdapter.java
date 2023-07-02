package com.example.notetakerapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NoteListAdapter extends ArrayAdapter<Note> {

    private Context context;
    private List<Note> noteList;

    public NoteListAdapter(Context context, List<Note> noteList) {
        super(context, 0, noteList);
        this.context = context;
        this.noteList = noteList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item_note, parent, false);
        }

        Note note = noteList.get(position);

        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView subtitleTextView = view.findViewById(R.id.subtitleTextView);

        titleTextView.setText(note.getTitle());
        subtitleTextView.setText(note.getSubtitle());

        return view;
    }
}
