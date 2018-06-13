package com.hlcsdev.x.application.ui.details;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.hlcsdev.x.application.R;
import com.hlcsdev.x.application.databinding.FragmentDetailsBinding;
import com.hlcsdev.x.application.ui.MainActivity;
import com.hlcsdev.x.application.ui.mainlist.MainFragment;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends MvpAppCompatFragment implements DetailsView {

    private FragmentDetailsBinding binding;

    @InjectPresenter
    DetailsPresenter presenter;

    private int pos;


    public DetailsFragment newInstance(int pos) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("pos", pos);
        fragment.setArguments(args);
        return fragment;
    }


    private DetailsFragmentCallback fragmentCallback;


    public interface DetailsFragmentCallback {
        void onStartDetailsFragment(int title);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        fragmentCallback = (MainActivity) getActivity();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pos = getArguments().getInt("pos");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);

        fragmentCallback.onStartDetailsFragment(R.string.details);

        presenter.setUi(pos);

        return binding.getRoot();
    }


    @Override
    public void showName(String name) {
        binding.name.setText(name);
    }


    @Override
    public void showImage(String url) {
        Picasso.with(getActivity())
                .load(url)
                .into(binding.image);
    }
}
