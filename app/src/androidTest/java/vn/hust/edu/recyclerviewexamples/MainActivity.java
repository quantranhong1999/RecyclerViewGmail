package vn.hust.edu.recyclerviewexamples;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.hust.edu.recyclerviewexamples.adapters.ItemAdapter;
import vn.hust.edu.recyclerviewexamples.models.ItemModel;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    List<ItemModel> items;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        items.add(new ItemModel("Item 1", R.drawable.thumb1));
        items.add(new ItemModel("Item 2", R.drawable.thumb2, false));
        items.add(new ItemModel("Item 3", R.drawable.thumb3, false));
        items.add(new ItemModel("Item 4", R.drawable.thumb4));
        items.add(new ItemModel("Item 5", R.drawable.thumb5));
        items.add(new ItemModel("Item 6", R.drawable.thumb6, false));
        items.add(new ItemModel("Item 7", R.drawable.thumb7));
        items.add(new ItemModel("Item 8", R.drawable.thumb8, false));
        items.add(new ItemModel("Item 9", R.drawable.thumb9));
        items.add(new ItemModel("Item 10", R.drawable.thumb10));
        items.add(new ItemModel("Item 11", R.drawable.thumb11, false));
        items.add(new ItemModel("Item 12", R.drawable.thumb12, false));
        items.add(new ItemModel("Item 13", R.drawable.thumb13));
        items.add(new ItemModel("Item 14", R.drawable.thumb14));
        items.add(new ItemModel("Item 15", R.drawable.thumb15, false));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        // RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        // RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ItemAdapter(items, this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                items.remove(pos);
                adapter.notifyItemRemoved(pos);
            }
        };
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.add(new ItemModel("Item 16", R.drawable.thumb16));
                adapter.notifyItemInserted(items.size() - 1);
            }
        });

        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove(0);
                adapter.notifyItemRemoved(0);
            }
        });

        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.get(0).setTitle("Changed Item 1");
                adapter.notifyItemChanged(0);
            }
        });
    }

    @Override
    public void OnItemClick(int position) {
        Log.v("TAG", items.get(position).getTitle() + " is selected");
    }
}
