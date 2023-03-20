package com.rbstudio.ethiopia.pixel.view;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rbstudio.ethiopia.pixel.Note;
import com.rbstudio.ethiopia.pixel.R;
import com.rbstudio.ethiopia.pixel.object.Loan;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.MyViewHolder> {

    private Context context;
    private List<Loan> notesList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,time,kulu;
        public ImageView imageView;
        public MyViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.img);
            name = view.findViewById(R.id.name);
            time = view.findViewById(R.id.time);
            kulu = view.findViewById(R.id.nege);

        }
    }


    public LoanAdapter(Context context, List<Loan> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemloan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Loan note = notesList.get(position);
        //holder.note.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
        holder.name.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
       holder.kulu.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
        holder.time.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
        holder.imageView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
        //Displaying dot from HTML character code
        //holder.dot.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
        holder.name.setText(note.getName());
        holder.time.setText(formatDate(note.getTimestamp()));
        holder.kulu.setText(String.valueOf(note.getAmount()));

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
