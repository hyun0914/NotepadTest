package com.example.notepadtest;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotepadAdapter extends RecyclerView.Adapter<NotepadAdapter.ViewHolder> {
    ArrayList<Notepad> items = new ArrayList<>();
    MainActivity context;
    Handler handler;

    public NotepadAdapter(Context context, Handler handler){
        this.context = (MainActivity)context;
        this.handler = handler;
    }

    @NonNull
    @Override
    public NotepadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.notepad_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotepadAdapter.ViewHolder holder, int position) {
        Notepad item = items.get(position);
        holder.setItems(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() { items.clear(); }

    public void addItem(Notepad item) { items.add(item); }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleView, dateView;
        DateNotepad dateNotepad;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Log.d("noteLog3", "제목" + items.get(pos).getTitle());
                        notifyItemChanged(pos);

                        Message msg = new Message();
                        msg.what = MainActivity.HANDLE_MSG_CHANGE_COUNT;
                        msg.obj = items.get(pos);
                        handler.sendMessage(msg);
                    }
                }
            });
            titleView = itemView.findViewById(R.id.ntitleView);
            dateView = itemView.findViewById(R.id.dateView);

            SimpleDateFormat sFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Date date = new Date();
            String nTime = sFormat.format(date);
            dateNotepad = new DateNotepad(nTime);
        }

        public void setItems(Notepad item){
            titleView.setText(item.getTitle());
            dateView.setText(dateNotepad.getdTime());
            Log.d("itemsSize", items.size()+"");
        }
    }
}
