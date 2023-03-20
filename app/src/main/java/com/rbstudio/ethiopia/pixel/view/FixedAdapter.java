package com.rbstudio.ethiopia.pixel.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.rbstudio.ethiopia.pixel.Note;
import com.rbstudio.ethiopia.pixel.R;
import com.rbstudio.ethiopia.pixel.object.Fixed;
import com.rbstudio.ethiopia.pixel.object.Salary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class FixedAdapter extends RecyclerView.Adapter<FixedAdapter.MyViewHolder> {

    private Context context;
    private List<Fixed> notesList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,date,price;
        public   ImageView imageView;
        public MyViewHolder(View view) {
            super(view);
            imageView=view.findViewById(R.id.img);
            name = view.findViewById(R.id.name);
             date= view.findViewById(R.id.time);
           price = view.findViewById(R.id.hbkni);

        }
    }


    public FixedAdapter(Context context, List<Fixed> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fixed, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Fixed note = notesList.get(position);
        //let's add the data

      holder.name.setText(note.getName());
      holder.date.setText(formatDate(note.getTimestamp()));
     holder.price.setText(String.valueOf(note.getPrice()) + " birr");


        holder.imageView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
        holder.price.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
        holder.name.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
        holder.date.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
       // holder.price.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));
        //Displaying dot from HTML character code
        //holder.dot.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));

        // Formatting and displaying timestamp
        //holder.timestamp.setText(formatDate(note.getTimestamp()));
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
