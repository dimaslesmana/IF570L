package id.ac.umn.dimas.week11_41281;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostDataAdapter extends RecyclerView.Adapter<PostDataAdapter.PostViewHolder> {
    private final List<PostModel> postData;
    private LayoutInflater inflater;

    PostDataAdapter(Context context, List<PostModel> postData) {
        this.inflater = LayoutInflater.from(context);
        this.postData = postData;
    }

    class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView tvTitle, tvBody;
        final PostDataAdapter adapter;

        public PostViewHolder(@NonNull View itemView, PostDataAdapter adapter) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            this.adapter = adapter;

//            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public PostDataAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.post_item, parent, false);

        return new PostViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull PostDataAdapter.PostViewHolder holder, int position) {
        PostModel current = postData.get(position);

        holder.tvTitle.setText(current.getTitle());
        holder.tvBody.setText(current.getBody());
    }

    @Override
    public int getItemCount() {
        return postData.size();
    }
}
