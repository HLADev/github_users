package com.hlcsdev.x.application.ui.mainlist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hlcsdev.x.application.R;
import com.hlcsdev.x.application.datamodels.User;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    private List<User> users;
    private Callback callback;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }


    public interface Callback {
        void onItemClick(User user);
        void onEndScroll(int pos);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int pos = holder.getAdapterPosition();

        holder.name.setText(users.get(pos).getLogin());

        holder.itemView.setOnClickListener(view -> {
            callback.onItemClick(users.get(pos));
        });
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
        }
    }


    @Override
    public void onViewAttachedToWindow(final ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int layoutPosition = holder.getLayoutPosition();

        // Если позиция последняя, загрузить еще данных
        if (layoutPosition == users.size() - 1) {
            callback.onEndScroll(layoutPosition);
        }
    }

}

