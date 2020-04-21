package vn.hust.edu.recyclerviewexamples;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MultiLayoutItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ItemModel> items;

    public MultiLayoutItemAdapter(List<ItemModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_left, parent, false);
            return new LeftViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_right, parent, false);
            return new RightViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            LeftViewHolder viewHolder = (LeftViewHolder) holder;
            viewHolder.textView.setText(items.get(position).getTitle());
            viewHolder.imageView.setImageResource(items.get(position).getImageResource());
        } else {
            RightViewHolder viewHolder = (RightViewHolder) holder;
            viewHolder.textView.setText(items.get(position).getTitle());
            viewHolder.imageView.setImageResource(items.get(position).getImageResource());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position).isLeft())
            return 0;
        else
            return 1;
    }

    class LeftViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

    class RightViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
