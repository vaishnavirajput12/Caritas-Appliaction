package com.application.Caritas.HistoryActivities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.application.Caritas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    ArrayList<model> datalist;
    List<String> slideLists;
    private OnItemClickListener mListener;
    AlertDialog.Builder builder;
    Context context;
    Boolean isNGO;


    public myadapter(Context context, ArrayList<model> datalist, Boolean isNGO) {
        this.context = context;
        this.datalist = datalist;
        this.isNGO = isNGO;
    }

    public interface OnItemClickListener{
        void onDeleteClick(int position);
        void onNotifyClick(int position);
    }


    public void setOnItemClickListener(myadapter.OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder (@NonNull ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view, mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.tname.setText(datalist.get(position).getName());
        holder.ttype.setText(datalist.get(position).getType());
        holder.tdescription.setText(datalist.get(position).getDescription());
        holder.tfooditem.setText(datalist.get(position).getFooditem());
        holder.texpiry.setText("" + TimeUnit.MILLISECONDS.toDays(
                datalist.get(position).getExpiry() - System.currentTimeMillis())
                + " days remaining");
        Picasso.get().load(datalist.get(position).getLink()).into(holder.imageView);


        if (isNGO && (datalist.get(position).getExpiry() > System.currentTimeMillis()))
            holder.notify.setVisibility(View.VISIBLE);
        else
            holder.notify.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView tname,ttype,tdescription, tfooditem, texpiry;
        CardView delete, notify;
        ImageView imageView;

        public myviewholder(@NonNull View itemView, OnItemClickListener mListener) {
            super(itemView);
            tname = itemView.findViewById(R.id.name);
            ttype = itemView.findViewById(R.id.type);
            tdescription = itemView.findViewById(R.id.description);
            tfooditem = itemView.findViewById(R.id.food);
            texpiry = itemView.findViewById(R.id.expired);
            delete = itemView.findViewById(R.id.delete);
            notify = itemView.findViewById(R.id.notify);
            imageView = itemView.findViewById(R.id.imageHistory);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder = new AlertDialog.Builder(context);
                    builder.setTitle("Deleting Item");
                    builder.setMessage("Are you sure?");

                    builder.setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            int position = getAdapterPosition();
                                            if (position!=RecyclerView.NO_POSITION){
                                                mListener.onDeleteClick(position);
                                            }
                                        }
                                    }
                            )
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                    alertDialog.show();
                }
            });

            notify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder = new AlertDialog.Builder(context);
                    builder.setTitle("Sending Notification");
                    builder.setMessage("Are you sure?");

                    builder.setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            int position = getAdapterPosition();
                                            if (position!=RecyclerView.NO_POSITION){
                                                mListener.onNotifyClick(position);
                                            }
                                        }
                                    }
                            )
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                    alertDialog.show();
                }
            });


        }
    }
}
