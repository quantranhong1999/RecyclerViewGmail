package vn.hust.edu.recyclerviewexamples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<List<ItemModel>> itemss;

    public ListItemAdapter(Context context, List<List<ItemModel>> itemss) {
        this.context = context;
        this.itemss = itemss;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_recyclerview, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListViewHolder viewHolder = (ListViewHolder) holder;
        ItemAdapter itemAdapter = new ItemAdapter(itemss.get(position));
        viewHolder.recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public int getItemCount() {
        return itemss.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.recycler_view_item);
            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
        }
    }
}
