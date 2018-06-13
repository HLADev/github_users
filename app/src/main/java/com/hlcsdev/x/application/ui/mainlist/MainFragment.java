package com.hlcsdev.x.application.ui.mainlist;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.hlcsdev.x.application.R;
import com.hlcsdev.x.application.datamodels.User;
import com.hlcsdev.x.application.databinding.FragmentMainBinding;
import com.hlcsdev.x.application.ui.MainActivity;
import com.hlcsdev.x.application.ui.mainlist.adapters.RvAdapter;


import java.util.List;

public class MainFragment extends MvpAppCompatFragment implements MainView {

    @InjectPresenter
    MainPresenter presenter;

    private FragmentMainBinding binding;
    private RvAdapter adapter;


    private MainFragmentCallback fragmentCallback;


    public interface MainFragmentCallback {
        void onItemClick(int pos);
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        fragmentCallback.onStartMainFragment(R.string.main);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }


    private void initAdapter() {
        adapter = new RvAdapter();
        adapter.setCallback(new RvAdapter.Callback() {
            @Override
            public void onItemClick(int pos) {
                fragmentCallback.onItemClick(pos);
            }

            @Override
            public void onEndScroll(int pos) {
                Toast.makeText(getActivity(), String.valueOf(pos), Toast.LENGTH_SHORT).show();

                presenter.setList();
            }
        });
    }


    @Override
    public void showProgress(boolean b) {
        if (b) {
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void showList(List<User> users) {
        adapter.setUsers(users);
        adapter.notifyDataSetChanged();
    }
}
