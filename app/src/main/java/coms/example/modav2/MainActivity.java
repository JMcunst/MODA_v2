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

import coms.example.modav2.DTO.MainCompleteDTO;
import coms.example.modav2.DTO.MainScheduleDTO;
import coms.example.modav2.adapter.MainCompleteAdapter;
import coms.example.modav2.adapter.MainScheduleAdapter;

public class MainActivity extends AppCompatActivity {
    private Context context = this;

    public ImageView iv_search;
    public ImageView iv_schedule;
    public ImageView iv_complete;

    public RecyclerView re_schedule;
    public RecyclerView re_complete;
    public MainCompleteAdapter recAdapter;
    public MainScheduleAdapter resAdapter;

    public int state_schedule_oc = 1; // OPEN
    public int state_complete_oc = 1; // OPEN

    public ArrayList<MainScheduleDTO> mainScheduleDTOs = new ArrayList<>();
    public ArrayList<MainCompleteDTO> mainCompleteDTOs = new ArrayList<>();

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


        MainScheduleDTO ms1 = new MainScheduleDTO(1,"코품미팅","프로토타입",
                new Date(121,04,17),0,0,1);
        MainScheduleDTO ms2 = new MainScheduleDTO(2,"이케아","식탁&의자",
                new Date(121,04,17),0,1,2);
        MainCompleteDTO mc1 = new MainCompleteDTO(3,"코품회의커밋정하기","커밋",
                new Date(121,04,17),1,3);

        mainScheduleDTOs.add(ms1);
        mainScheduleDTOs.add(ms2);
        mainCompleteDTOs.add(mc1);

        resAdapter = new MainScheduleAdapter(context, mainScheduleDTOs);
        recAdapter = new MainCompleteAdapter(context, mainCompleteDTOs);
        re_schedule.setAdapter(resAdapter);
        re_complete.setAdapter(recAdapter);
        re_schedule.setLayoutManager(new LinearLayoutManager(this));
        re_complete.setLayoutManager(new LinearLayoutManager(this));

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

    }
}
