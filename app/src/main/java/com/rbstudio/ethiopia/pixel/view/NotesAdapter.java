package com.rbstudio.ethiopia.pixel.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.rbstudio.ethiopia.pixel.Service;
import com.rbstudio.ethiopia.pixel.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<Service> notesList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView dot;
        public TextView alem,sreqeqe,ayni,timestamp;

        public MyViewHolder(View view) {
            super(view);
            alem = view.findViewById(R.id.note);
            sreqeqe = view.findViewById(R.id.time);
            ayni = view.findViewById(R.id.price);
            dot = view.findViewById(R.id.img);
        }
    }


    public NotesAdapter(Context context, List<Service> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_service, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Service note = notesList.get(position);
        holder.alem.setText(note.getService());
        holder.sreqeqe.setText(formatDate(note.getTimestamp()));
        holder.ayni.setText(String.valueOf(note.getPayment()));
       //holder.note.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
         holder.alem.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
         holder.sreqeqe.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
         holder.ayni.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
         //Displaying dot from HTML character code
        holder.dot.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));

        // Formatting and displaying timestamp
       // holder.timestamp.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }
}
