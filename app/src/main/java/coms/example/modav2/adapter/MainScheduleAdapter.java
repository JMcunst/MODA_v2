package coms.example.modav2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import coms.example.modav2.DTO.MainScheduleDTO;
import coms.example.modav2.R;

public class MainScheduleAdapter extends RecyclerView.Adapter<MainScheduleAdapter.ViewHolder> {
    private ArrayList<MainScheduleDTO> mslists = new ArrayList<>();
    private Context mcontext;
    private int scc = 0; // 삭제 요망

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
        protected ImageView schCheck;

        public ViewHolder(View view){
            super(view);

            this.schContent = (TextView)view.findViewById(R.id.main_schedule_item_content);
            this.schMemo = (ImageView)view.findViewById(R.id.main_schedule_item_memo);
            this.schDate = (TextView)view.findViewById(R.id.main_schedule_item_date);
            this.schCategory = (ImageView)view.findViewById(R.id.main_schedule_item_img_category);
            this.schPin = (ImageView)view.findViewById(R.id.main_schedule_item_pin);
            this.schCheck = (ImageView)view.findViewById(R.id.main_re_sch_check);
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
        // 스케줄 내용
        holder.schContent.setText(mslists.get(position).getSheduleContent());
        // 스케줄 날짜
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy.mm.dd");
        String dateToString = df.format(mslists.get(position).getScheduleDate());
        holder.schDate.setText(dateToString);
        // 스케줄 메모
        if(mslists.get(position).getSheduleMemo() != null){
            holder.schMemo.setImageResource(R.drawable.ic_memonote_52);
        }else{
            holder.schMemo.setVisibility(View.GONE);
        }
        // 스케줄 카테고리
        holder.schCategory.setImageResource(R.drawable.ic_account);
        // 스케줄 고정
        if(mslists.get(position).getSchedulePin() == 1){
            holder.schPin.setImageResource(R.drawable.ic_pin_black_48);
        }else{
            holder.schPin.setVisibility(View.GONE);
        }
        //스케줄 상태
        if(mslists.get(position).getScheduleState() == 0) {
            holder.schCheck.setImageResource(R.drawable.ic_check_50);
        }else{
            holder.schCheck.setImageResource(R.drawable.ic_checked_50);
        }

        /*** 클릭 리스너 ***/
        // 스케줄 상태
        holder.schCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(scc == 0) {
                    holder.schCheck.setImageResource(R.drawable.ic_checked_50);
                    mslists.get(position).setScheduleState(1);
                    scc = 1; // 삭제 요망
                    Log.v("cccccccccc","cccccccccccc");
                }else{
                    holder.schCheck.setImageResource(R.drawable.ic_check_50);
                    mslists.get(position).setScheduleState(0);
                    scc = 0; // 삭제 요망
                    Log.v("kkkkkkkkkkkkkkk","kkkkkkkkkkk");
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return (null != mslists ? mslists.size() : 0);
    }
}
