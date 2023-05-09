package com.example.packyourbag.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.packyourbag.Constants.MyConstants;
import com.example.packyourbag.Database.RoomDB;
import com.example.packyourbag.Models.Items;
import com.example.packyourbag.R;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.List;

public class CheckListAdapter extends RecyclerView.Adapter<CheckListViewHolder> {

    Context context;
    List<Items> itemList;
    RoomDB database;
    String show;

    public CheckListAdapter() {
    }

    public CheckListAdapter(Context context, List<Items> itemList, RoomDB database, String show) {
        this.context = context;
//        this.itemList = itemList;
        this.database = database;
        this.show = show;

        // Filter the itemList based on the 'show' parameter
        if (MyConstants.FALSE_STRING.equals(show)) {
            this.itemList = database.mainDao().getAllSelected(true);
        } else {
            this.itemList = new ArrayList<>(itemList);
        }
        if(itemList.size() == 0){
//            Toast.makeText(context.getApplicationContext(),"Nothing to show", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public CheckListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckListViewHolder(LayoutInflater.from(context).inflate(R.layout.check_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckListViewHolder holder, int position) {
        holder.checkBox.setText(itemList.get(position).getItemname());
        holder.checkBox.setChecked(itemList.get(position).getChecked());

        //In this when item checked then border is remove and set background color
        if(MyConstants.FALSE_STRING.equals(show)){
            holder.btnDelete.setVisibility(View.GONE);
            holder.layout.setBackground(context.getResources().getDrawable(R.drawable.border_one));
        }else{
            if(itemList.get(position).getChecked()){
                holder.layout.setBackgroundColor(Color.parseColor("#8e546f"));
            }else{
                holder.layout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.border_one));
            }
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean check = holder.checkBox.isChecked();
                database.mainDao().checkUncheck(itemList.get(position).getID(), check);
                if(MyConstants.FALSE_STRING.equals(show)){
                    itemList = database.mainDao().getAllSelected(true);
                    notifyDataSetChanged();
                }else{
                    itemList.get(position).setChecked(check);
                    notifyDataSetChanged();
                    Toast toastMessage = null;
                    if(toastMessage != null){
                        toastMessage.cancel();
                    }
                    if(itemList.get(position).getChecked()){
                        toastMessage = Toast.makeText(context,"("+holder.checkBox.getText()+") Packed", Toast.LENGTH_SHORT);
                    }
                    else{
                        toastMessage = Toast.makeText(context,"("+holder.checkBox.getText()+") Un-Packed", Toast.LENGTH_SHORT);
                    }
                    toastMessage.show();
                }

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete ( "+itemList.get(position).getItemname()+" )")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                database.mainDao().delete(itemList.get(position));
                                itemList.remove(itemList.get(position));
                                notifyDataSetChanged();
                            }
                        }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(context,"Canclled",Toast.LENGTH_SHORT).show();
                            }
                        }).setIcon(R.drawable.ic_delete)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

//bind checkbox, button from check_list_item.xml
class CheckListViewHolder extends RecyclerView.ViewHolder{

    LinearLayout layout;
    CheckBox checkBox;
    Button btnDelete;

    public CheckListViewHolder(@NonNull View itemView) {
        super(itemView);

        layout = itemView.findViewById(R.id.linearLayout);
        checkBox = itemView.findViewById(R.id.checkbox);
        btnDelete = itemView.findViewById(R.id.btnDelete);


    }
}