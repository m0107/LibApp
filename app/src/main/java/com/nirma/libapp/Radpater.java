package com.nirma.libapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import java.util.Collections;
import java.util.List;



public class Radpater extends RecyclerView.Adapter<Radpater.Myviewholder> {

    private LayoutInflater inflater;
    Context context;
    private int lastPosition = -1;
    private  OnItemClickListener listener;



    int previousposition = 0;

    String[] names;

    public Radpater(Context c,String[] names){
        inflater = LayoutInflater.from(c);
        context = c;
        //this.listener = (OnItemClickListener) c;
        this.names = names;
    }

    @Override
    public Myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.custom_row, parent, false);
        Myviewholder myviewholder = new Myviewholder(view);
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(Myviewholder holder, final int position) {


        holder.title.setText(names[position]);

        holder.onItemClick(position);

        /*if(position > previousposition){
            AnimUtils.animate(holder,true);
        }
        else{
            AnimUtils.animate(holder,false);
        }
        previousposition=position;*/
       AnimUtils.scaleXY(holder);
       // setAnimation(holder.v,position);


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
        return names.length;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    class Myviewholder extends RecyclerView.ViewHolder{

        TextView title;
        CardView cv;
        View v;

        public Myviewholder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            title = (TextView) itemView.findViewById(R.id.institutetextview);
            v = itemView;

        }

        public void onItemClick(final int pos){
              itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                     // listener.onItemClick(pos);
                      ((Select_institute)context).itermSelected(pos);

                  }
              });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }
}
