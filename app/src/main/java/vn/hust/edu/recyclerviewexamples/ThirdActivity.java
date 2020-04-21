package vn.hust.edu.recyclerviewexamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.FileObserver;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Toast;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

import vn.hust.edu.recyclerviewexamples.adapters.EmailItemAdapter;
import vn.hust.edu.recyclerviewexamples.models.EmailItemModel;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    boolean state = false;
    List<EmailItemModel> items;
    List<EmailItemModel> items_favorite;
    List<EmailItemModel> items_result;
    Faker faker = new Faker();
    Button btn_favorite;
    EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        items = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            items.add(new EmailItemModel(faker.name().name(), faker.lorem().sentence(), faker.lorem().paragraph(), "12:00 PM"));
        //Khai bao recycleview full List
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final EmailItemAdapter adapter = new EmailItemAdapter(items);
        recyclerView.setAdapter(adapter);
        initWidget();
        setEvenClickView();

        //Khai bao recycleview tim kiem
        final RecyclerView recyclerView_result = findViewById(R.id.recycler_view);
        recyclerView_result.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutResultManager = new LinearLayoutManager(this);
        recyclerView_result.setLayoutManager(layoutResultManager);


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Chi tim kiem voi s co 3 ky tu tro len
                if (s.length()>=3){
                    items_result = new ArrayList<>();
                    for (int i=0;i<10;i++){
                        if (items.get(i).getName().toLowerCase().contains(s.toString().toLowerCase()) ||
                                items.get(i).getContent().toLowerCase().contains(s.toString().toLowerCase()) ||
                                items.get(i).getSubject().toLowerCase().contains(s.toString().toLowerCase())){
                            items_result.add(items.get(i));
                        }
                    }

                    EmailItemAdapter resultAdapter = new EmailItemAdapter(items_result);
                    recyclerView_result.setAdapter(resultAdapter);
                } else {
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(ThirdActivity.this, "Nhap 3 ky tu tro len neu tim kiem", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void initWidget(){
        btn_favorite = findViewById(R.id.btn_favorite);
        search = findViewById(R.id.edt_search);
    }
    public void setEvenClickView(){
        btn_favorite.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_favorite:
                if (!state){
                    items_favorite = new ArrayList<>();
                    for (int i=0;i<10;i++){
                        if (items.get(i).isFavorite()){
                            items_favorite.add(items.get(i));
                        }
                    }
                    //Tao recycleview Co sao favorite
                    RecyclerView recyclerView_favorite = findViewById(R.id.recycler_view);
                    recyclerView_favorite.setHasFixedSize(true);

                    RecyclerView.LayoutManager layoutFavoriteManager = new LinearLayoutManager(this);
                    recyclerView_favorite.setLayoutManager(layoutFavoriteManager);

                    EmailItemAdapter favoriteAdapter = new EmailItemAdapter(items_favorite);
                    recyclerView_favorite.setAdapter(favoriteAdapter);

                    state = true;
                    break;
                } else {
                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    recyclerView.setHasFixedSize(true);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                    recyclerView.setLayoutManager(layoutManager);

                    EmailItemAdapter adapter = new EmailItemAdapter(items);
                    recyclerView.setAdapter(adapter);
                    state = false;
                    break;
                }

        }
    }

}
