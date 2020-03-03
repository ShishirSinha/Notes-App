package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<NotesClass> notesList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView headingTv;
        public TextView dateTimeTv;
        public CardView cardView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            headingTv = itemView.findViewById(R.id.note_item_headingTV);
            dateTimeTv = itemView.findViewById(R.id.note_item_dateTV);
            cardView = itemView.findViewById(R.id.note_item_CV);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("cccc", "onClick: Yes");

                    Intent intent = new Intent(context, NoteContentActivity.class);
                    intent.putExtra("heading", notesList.get(getAdapterPosition()).getmNoteHeading());
                    intent.putExtra("datetime", notesList.get(getAdapterPosition()).getmNoteDateTime());
                    intent.putExtra("content", notesList.get(getAdapterPosition()).getmNoteContent());
                    context.startActivity(intent);
                }
            });
        }
    }

    public NotesAdapter(List<NotesClass> notesList, Context context) {
        this.notesList = notesList;
        this.context = context;
        Log.e("constructor","haha");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_item, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.headingTv.setText(notesList.get(position).getmNoteHeading());
        holder.dateTimeTv.setText(notesList.get(position).getmNoteDateTime());
    }


    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void removeAt(int position) {
        notesList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, notesList.size());
//        mRefresh.update(mMenuItems);
    }
}
