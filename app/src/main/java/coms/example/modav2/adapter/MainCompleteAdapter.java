package coms.example.modav2.adapter;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import coms.example.modav2.DTO.MainCompleteDTO;

import coms.example.modav2.R;

public class MainCompleteAdapter extends RecyclerView.Adapter<MainCompleteAdapter.ViewHolder> {
    private ArrayList<MainCompleteDTO> mclists = new ArrayList<>();
    private Context mcontext;

    public MainCompleteAdapter(Context context, ArrayList<MainCompleteDTO> mslists) {
        this.mcontext = context;
        this.mclists = mslists;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView cmpContent;
        protected ImageView cmpMemo;
        protected TextView cmpDate;
        protected ImageView cmpCategory;

        public ViewHolder(View view){
            super(view);

            this.cmpContent = (TextView)view.findViewById(R.id.main_complete_item_content);
            this.cmpMemo = (ImageView)view.findViewById(R.id.main_complete_item_memo);
            this.cmpDate = (TextView)view.findViewById(R.id.main_complete_item_date);
            this.cmpCategory = (ImageView)view.findViewById(R.id.main_complete_item_img_category);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_main_complete_item,null);

        return new MainCompleteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cmpContent.setText(mclists.get(position).getCompleteContent());
        holder.cmpDate.setText((CharSequence) mclists.get(position).getCompleteDate());
        holder.cmpCategory.setImageResource(R.drawable.ic_account);
    }


    @Override
    public int getItemCount() {
        return (null != mclists ? mclists.size() : 0);
    }
}
