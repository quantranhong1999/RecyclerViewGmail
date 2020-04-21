package vn.hust.edu.recyclerviewexamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import vn.hust.edu.recyclerviewexamples.adapters.ListItemAdapter;
import vn.hust.edu.recyclerviewexamples.models.ItemModel;

public class SecondActivity extends AppCompatActivity {

    List<List<ItemModel>> itemss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        itemss = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            List<ItemModel> items = new ArrayList<>();
            for (int j = 0; j < 10; j++)
                items.add(new ItemModel("Item " + i + "-" + j, R.drawable.thumb1));
            itemss.add(items);
        }

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ListItemAdapter adapter = new ListItemAdapter(this, itemss);
        recyclerView.setAdapter(adapter);
    }
}
