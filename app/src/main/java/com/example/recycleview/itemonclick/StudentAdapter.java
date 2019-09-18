package com.example.recycleview.itemonclick;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andy.recycleview.R;

import java.util.List;
import java.util.Random;

class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private View.OnClickListener onItemClickListener;
    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }
    List<studentData> studentDataList;
    Context context;
    private int lastPosition = -1;
    public StudentAdapter(List<studentData> studentDataList, Context context) {
        this.studentDataList=studentDataList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.student_list_row, viewGroup, false);

        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        studentData data=studentDataList.get(i);
        Random rnd = new Random();
        int currentColor = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        viewHolder.parent.setBackgroundColor(currentColor);

        viewHolder.name.setText(data.name);
        viewHolder.age.setText(String.valueOf(data.age));
        setAnimation(viewHolder.parent, i);

    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
    @Override
    public int getItemCount() {
        return studentDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,age;
        LinearLayout parent;
        public MyViewHolder(View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            name=itemView.findViewById(R.id.name);
            age=itemView.findViewById(R.id.age);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }
}
