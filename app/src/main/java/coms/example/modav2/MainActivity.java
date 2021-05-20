package coms.example.modav2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import coms.example.modav2.DTO.MainScheduleDTO;
import coms.example.modav2.adapter.MainScheduleAdapter;

public class MainActivity extends AppCompatActivity {
    private Context context = this;

    public ImageView iv_search;
    public ImageView iv_schedule;
    public ImageView iv_complete;
    public ImageView iv_category_add;
    public ImageView iv_calendar_add;

    public RecyclerView re_schedule;
    public RecyclerView re_complete;
    public MainScheduleAdapter resAdapter;
    public MainScheduleAdapter recAdapter;

    public int state_schedule_oc = 1; // OPEN
    public int state_complete_oc = 1; // OPEN
    public int state_cate_cal_direction = 0; // category

    public ArrayList<MainScheduleDTO> mainSchs = new ArrayList<>();
    public ArrayList<MainScheduleDTO> mainCompleteSchs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        setView();
    }

    public void setView(){
        iv_search = (ImageView)findViewById(R.id.main_iv_search);
        iv_schedule = (ImageView)findViewById(R.id.main_schedule_open_close);
        iv_complete = (ImageView)findViewById(R.id.main_complete_open_close);
        re_schedule = (RecyclerView)findViewById(R.id.main_schedule_recycler);
        re_complete = (RecyclerView)findViewById(R.id.main_complete_recycler);
        iv_category_add = (ImageView)findViewById(R.id.main_category_add);
        iv_calendar_add = (ImageView)findViewById(R.id.main_calendar_add);


        MainScheduleDTO ms1 = new MainScheduleDTO(1,"코품미팅","프로토타입",
                new Date(121, 4,17),0,0,1,0);
        MainScheduleDTO ms2 = new MainScheduleDTO(2,"이케아","식탁&의자",
                new Date(121, 4,17),0,1,2,0);
        MainScheduleDTO mc1 = new MainScheduleDTO(3,"코품회의커밋정하기","커밋",
                new Date(121, 4,17),1,3,3,0);

        mainSchs.add(ms1);
        mainSchs.add(ms2);
        mainCompleteSchs.add(mc1);

        resAdapter = new MainScheduleAdapter(context, mainSchs);
        recAdapter = new MainScheduleAdapter(context, mainCompleteSchs);
        re_schedule.setAdapter(resAdapter);
        re_complete.setAdapter(recAdapter);
        re_schedule.setLayoutManager(new LinearLayoutManager(this));
        re_complete.setLayoutManager(new LinearLayoutManager(this));
//        re_schedule.setLayoutManager(new LinearLayoutManager(this){
//            @Override
//            public boolean canScrollVertically() { // 세로스크롤 막기
//                return false;
//            }
//        });
//        re_complete.setLayoutManager(new LinearLayoutManager(this){
//            @Override
//            public boolean canScrollVertically() { // 세로스크롤 막기
//                return false;
//            }
//        });

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        iv_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state_schedule_oc == 1) {
                    state_schedule_oc = 0;
                    iv_schedule.setImageResource(R.drawable.ic_arrow_down_64);
                    re_schedule.setVisibility(View.GONE);
                }else{
                    state_schedule_oc = 1;
                    iv_schedule.setImageResource(R.drawable.ic_arrow_up_64);
                    re_schedule.setVisibility(View.VISIBLE);
                }
            }
        });
        iv_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state_complete_oc == 1) {
                    state_complete_oc = 0;
                    iv_complete.setImageResource(R.drawable.ic_arrow_down_64);
                    re_complete.setVisibility(View.GONE);
                }else{
                    state_complete_oc = 1;
                    iv_complete.setImageResource(R.drawable.ic_arrow_up_64);
                    re_complete.setVisibility(View.VISIBLE);
                }
            }
        });
        iv_category_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state_cate_cal_direction == 1) {
                    iv_category_add.setImageResource(R.drawable.ic_category_mintgray_checked_96);
                    iv_calendar_add.setImageResource(R.drawable.ic_calendar_black_48);
                    state_cate_cal_direction = 0;
                }
            }
        });
        iv_calendar_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state_cate_cal_direction == 0) {
                    iv_category_add.setImageResource(R.drawable.ic_category_black_64);
                    iv_calendar_add.setImageResource(R.drawable.ic_calendar_mintgray_checked_96);
                    state_cate_cal_direction = 1;
                }
            }
        });

    }
}
