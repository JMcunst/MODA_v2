package coms.example.modav2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.util.ChineseCalendar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

    public TextView tv_date;
    public TextView tv_day;
    public TextView tv_lunar;

    public RecyclerView re_schedule;
    public RecyclerView re_complete;
    public MainScheduleAdapter resAdapter;
    public MainScheduleAdapter recAdapter;

    public CardView crd_category;
    public CardView crd_calendar;
    public CardView crd_plus;

    public Date date;
    public long nowtime;

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
        /*** binding ***/
        iv_search = (ImageView)findViewById(R.id.main_iv_search);
        iv_schedule = (ImageView)findViewById(R.id.main_schedule_open_close);
        iv_complete = (ImageView)findViewById(R.id.main_complete_open_close);
        re_schedule = (RecyclerView)findViewById(R.id.main_schedule_recycler);
        re_complete = (RecyclerView)findViewById(R.id.main_complete_recycler);
        iv_category_add = (ImageView)findViewById(R.id.main_category_add);
        iv_calendar_add = (ImageView)findViewById(R.id.main_calendar_add);

        tv_date = (TextView)findViewById(R.id.main_date);
        tv_day = (TextView)findViewById(R.id.main_day);
        tv_lunar = (TextView)findViewById(R.id.main_lunar);

        crd_category = (CardView)findViewById(R.id.main_add_category_card);
        crd_calendar = (CardView)findViewById(R.id.main_add_calendar_card);
        crd_plus = (CardView)findViewById(R.id.main_add_plus_card);

        /*** recycler items ***/
        MainScheduleDTO ms1 = new MainScheduleDTO(1,"????????????","???????????????",
                new Date(121, 4,17),0,0,1,0);
        MainScheduleDTO ms2 = new MainScheduleDTO(2,"?????????","??????&??????",
                new Date(121, 4,17),0,1,2,0);
        MainScheduleDTO mc1 = new MainScheduleDTO(3,"???????????????????????????","??????",
                new Date(121, 4,17),1,3,3,0);


        mainSchs.add(ms1);
        mainSchs.add(ms2);
        mainCompleteSchs.add(mc1);

        sortPin(mainSchs);

        resAdapter = new MainScheduleAdapter(context, mainSchs);
        recAdapter = new MainScheduleAdapter(context, mainCompleteSchs);
        re_schedule.setAdapter(resAdapter);
        re_complete.setAdapter(recAdapter);
        re_schedule.setLayoutManager(new LinearLayoutManager(this));
        re_complete.setLayoutManager(new LinearLayoutManager(this));

        /*** nowdate, day, lunar ***/
        String nowdate = getCurrentDate();
        String nowday = getCurrentDay();
        String lunardate = getSolarToLunar(getCurrentYearMonthDate());

        tv_date.setText(nowdate);
        tv_day.setText(nowday);
        tv_lunar.setText(lunardate);

        /*** listener ***/
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
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(state_cate_cal_direction == 1) {
                    state_cate_cal_direction = 0;
                    iv_category_add.setImageResource(R.drawable.ic_category_mintgray_checked_96);
                    crd_category.setCardBackgroundColor(R.color.AppLightMintGray);
                    iv_calendar_add.setImageResource(R.drawable.ic_calendar_black_48);
                    crd_calendar.setCardBackgroundColor(R.color.white);
                }
            }
        });
        iv_calendar_add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if(state_cate_cal_direction == 0) {
                    state_cate_cal_direction = 1;
                    iv_category_add.setImageResource(R.drawable.ic_category_black_64);
                    crd_category.setCardBackgroundColor(R.color.white);
                    iv_calendar_add.setImageResource(R.drawable.ic_calendar_mintgray_checked_96);
                    crd_calendar.setCardBackgroundColor(R.color.AppLightMintGray);
                }
            }
        });
        crd_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("addaddaddadd","addaddaddadd");
                mainSchs.add(new MainScheduleDTO(4,"????????????","???????????????",
                        new Date(121, 5,2),0,1,1,0));

                resAdapter.notifyDataSetChanged();
                //Toast.makeText(context, "????????? ?????????????????????", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.main_complete_sort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.schedule_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.action_sort_reg){
                            Toast.makeText(MainActivity.this, "????????? ??????", Toast.LENGTH_SHORT).show();
                        }else if (menuItem.getItemId() == R.id.action_sort_name){
                            Toast.makeText(MainActivity.this, "????????? ??????", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "??????????????? ??????", Toast.LENGTH_SHORT).show();
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        findViewById(R.id.main_schedule_sort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.schedule_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.action_sort_reg){
                            Toast.makeText(MainActivity.this, "????????? ??????", Toast.LENGTH_SHORT).show();
                        }else if (menuItem.getItemId() == R.id.action_sort_name){
                            Toast.makeText(MainActivity.this, "????????? ??????", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "??????????????? ??????", Toast.LENGTH_SHORT).show();
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }
    /*** MainSchedule list ?????? (Pin ???) ***/
    private void sortPin(ArrayList<MainScheduleDTO> mainSchs) {
        Collections.sort(mainSchs);
    }

    /*** ?????? ?????? (???) ***/
    public String getCurrentDate(){
        nowtime = System.currentTimeMillis();
        date = new Date(nowtime);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("d???");
        String nowdate = sdf.format(date);

        return nowdate;
    }
    /*** ?????? ?????? (??????) ***/
    public String getCurrentDay(){
        nowtime = System.currentTimeMillis();
        date = new Date(nowtime);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("E??????");
        String nowday = sdf.format(date);

        return nowday;
    }
    /*** ?????? ?????? (??? ??? ???) ?????? ***/
    public String getCurrentYearMonthDate(){
        nowtime = System.currentTimeMillis();
        date = new Date(nowtime);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
        String nowymd = sdf.format(date);

        return nowymd;
    }
    /*** ?????? ?????? (??? ??? ???) ?????? ***/
    public static String getSolarToLunar(String yyyymmdd) {
        ChineseCalendar cc = new ChineseCalendar();
        Calendar cal = Calendar.getInstance();

        if (yyyymmdd == null)
            return "";

        /*** ?????? ***/
        String date = yyyymmdd.trim() ;
        if( date.length() != 8 ) {
            if( date.length() == 4 )
                date = date + "0101" ;
            else if( date.length() == 6 )
                date = date + "01" ;
            else if( date.length() > 8 )
                date = date.substring(0,8) ;
            else
                return "" ;
        }

        cal.set( Calendar.YEAR, Integer.parseInt(date.substring(0,4)) ) ;
        cal.set( Calendar.MONTH, Integer.parseInt(date.substring(4,6))-1 ) ;
        cal.set( Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)) ) ;

        cc.setTimeInMillis( cal.getTimeInMillis() ) ;
        /*** ?????? ***/
        // ChinessCalendar.YEAR ??? 1~60 ????????? ?????? ????????? ,
        // ChinessCalendar.EXTENDED_YEAR ??? Calendar.YEAR ?????? 2639 ????????? ????????? ?????????.
        //int y = cc.get(ChineseCalendar.EXTENDED_YEAR)-2639 ;
        int m = cc.get(ChineseCalendar.MONTH)-2 ;
        int d = cc.get(ChineseCalendar.DAY_OF_MONTH)-8 ;

        StringBuffer ret = new StringBuffer() ;
//        if( y < 1000 )          ret.append( "0" ) ;
//        else if( y < 100 )      ret.append( "00" ) ;
//        else if( y < 10 )       ret.append( "000" ) ;
//        ret.append( y ) ;
        ret.append("?????? ");
        if( m < 10 ) ret.append( "0" ) ;
        ret.append( m ) ;
        ret.append(". ");
        if( d < 10 ) ret.append( "0" ) ;
        ret.append( d ) ;

        String nowlunar = ret.toString() ;
        return nowlunar;
    }

}
