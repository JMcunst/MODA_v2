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
    private int cur_pin = 1;

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
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        Log.v("ddddddddddddd",mslists.get(position).getScheduleDate().toString());
        String dateToString = df.format(mslists.get(position).getScheduleDate());
        holder.schDate.setText(dateToString);
        // 스케줄 메모
        if(mslists.get(position).getSheduleMemo() != null){
            holder.schMemo.setImageResource(R.drawable.ic_memonote_52);
        }else{
            holder.schMemo.setVisibility(View.GONE);
        }
        // 스케줄 카테고리
        switch (mslists.get(position).getScheduleCategory()){
            case 0 :        // 카테고리 x
                holder.schCategory.setImageResource(R.drawable.cate_no);
                break;
            case 1 :        // 전체
                holder.schCategory.setImageResource(R.drawable.cate_all);
                break;
            case 2 :        // 집
                holder.schCategory.setImageResource(R.drawable.cate_home);
                break;
            case 3 :        // 회사
                holder.schCategory.setImageResource(R.drawable.cate_company);
                break;
            case 4 :        // 공부
                holder.schCategory.setImageResource(R.drawable.cate_study);
                break;
            case 5 :        // 쇼핑
                holder.schCategory.setImageResource(R.drawable.cate_shopping);
                break;
        }

        // 스케줄 고정
        if(mslists.get(position).getSchedulePin() == 1){
            holder.schPin.setImageResource(R.drawable.ic_pin_black_48);
            onItemMoveByPin(position);
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

    public void onItemMoveByPin(int from_position) {
        //이동할 객체 저장
        MainScheduleDTO msch = mslists.get(from_position);
        //이동할 객체 삭제
        mslists.remove(from_position);
        //이동하고 싶은 position에 추가
        mslists.add(cur_pin,msch);
        //Adapter에 데이터 이동알림
        notifyItemMoved(from_position,cur_pin);
        cur_pin++;
    }




    @Override
    public int getItemCount() {
        return (null != mslists ? mslists.size() : 0);
    }
}
