package coms.example.modav2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import coms.example.modav2.DTO.MainCompleteDTO;

import coms.example.modav2.R;

public class MainCompleteAdapter extends RecyclerView.Adapter<MainCompleteAdapter.Holder> {
    private ArrayList<MainCompleteDTO> mslists = new ArrayList<>();
    private Context context;

    public MainCompleteAdapter(Context context, ArrayList<MainCompleteDTO> mslists) {
        this.context = context;
        this.mslists = mslists;
    }

    @NonNull
    @Override
    public MainCompleteAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_main_complete,parent,false);

        Holder holder = new Holder(view);
        return holder;
    }

    public class Holder extends RecyclerView.ViewHolder {
        protected int schId;
        protected String schContent;
        protected String schMemo;
        protected Date schDate;
        protected int schState;

        public Holder(View view){
            super(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MainCompleteAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (null != mslists ? mslists.size() : 0);
    }
}
