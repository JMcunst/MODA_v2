package coms.example.modav2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
    private Context context = this;

    ImageView iv_back;
    ImageView iv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setView();
    }

    public void setView(){
        iv_back = (ImageView)findViewById(R.id.search_iv_back);
        iv_search = (ImageView)findViewById(R.id.search_iv_search);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO_: edittext의 값을 읽어서 DB검색
                Toast.makeText(getApplicationContext(),"검색기능입니다", Toast.LENGTH_LONG).show();
            }
        });


    }
}
