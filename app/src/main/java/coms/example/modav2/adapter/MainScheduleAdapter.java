package coms.example.modav2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import coms.example.modav2.DTO.MainScheduleDTO;
import coms.example.modav2.R;

public class MainScheduleAdapter extends RecyclerView.Adapter<MainScheduleAdapter.ViewHolder> {
    private ArrayList<MainScheduleDTO> mslists = new ArrayList<>();
    private Context mcontext;

    public MainScheduleAdapter(Context context, ArrayList<MainScheduleDTO> mslists) {
        this.mcontext = context;
        this.mslists = mslists;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView schContent;
        protected ImageView schMemo;
        protected TextView schDate;
        protected ImageView schCategory;
        protected ImageView schPin;

        public ViewHolder(View view){
            super(view);

            this.schContent = (TextView)view.findViewById(R.id.main_schedule_item_content);
            this.schMemo = (ImageView)view.findViewById(R.id.main_schedule_item_memo);
            this.schDate = (TextView)view.findViewById(R.id.main_schedule_item_date);
            this.schCategory = (ImageView)view.findViewById(R.id.main_schedule_item_img_category);
            this.schPin = (ImageView)view.findViewById(R.id.main_schedule_item_pin);
        }
    }
    @NonNull
    @Override
    public MainScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_main_schedule_item,null);

        return new MainScheduleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainScheduleAdapter.ViewHolder holder, int position) {
        holder.schContent.setText(mslists.get(position).getSheduleContent());
        holder.schDate.setText((CharSequence) mslists.get(position).getScheduleDate());
        holder.schCategory.setImageResource(R.drawable.ic_account);
        if(mslists.get(position).getSchedulePin() == 1){
            holder.schPin.setImageResource(R.drawable.ic_pin_black_48);
        }


    }


    @Override
    public int getItemCount() {
        return (null != mslists ? mslists.size() : 0);
    }
}
