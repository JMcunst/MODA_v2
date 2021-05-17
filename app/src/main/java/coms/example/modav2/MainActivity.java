package coms.example.modav2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private Context context = this;

    public ImageView iv_search;
    public ImageView iv_schedule;
    public ImageView iv_complete;

    RecyclerView re_schedule;
    RecyclerView re_complete;

    public int state_schedule_oc = 1; // OPEN
    public int state_complete_oc = 1; // OPEN

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
