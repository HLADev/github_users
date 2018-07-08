package com.hlcsdev.x.application.ui.mainlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.hlcsdev.x.application.R;
import com.hlcsdev.x.application.datamodels.User;
import com.hlcsdev.x.application.ui.MainActivity;
import com.hlcsdev.x.application.ui.mainlist.adapters.RvAdapter;

import java.util.List;


public class MainFragment extends MvpAppCompatFragment implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    private RvAdapter adapter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private MainFragmentCallback fragmentCallback;


    public interface MainFragmentCallback {
        void onItemClick(User user);
        void onStartMainFragment(int title);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        fragmentCallback = (MainActivity) getActivity();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAdapter();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        fragmentCallback.onStartMainFragment(R.string.main);

        findViews(view);

        initRecyclerView();

        return view;
    }


    private void initAdapter() {
        adapter = new RvAdapter();
        adapter.setCallback(new RvAdapter.Callback() {
            @Override
            public void onItemClick(User user) {
                fragmentCallback.onItemClick(user);
            }

            @Override
            public void onEndScroll(int pos) {
                Toast.makeText(getActivity(), String.valueOf(pos), Toast.LENGTH_SHORT).show();

                presenter.setList();
            }
        });
    }


    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void showProgress(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showList(List<User> users) {
        adapter.setUsers(users);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
    }
}
