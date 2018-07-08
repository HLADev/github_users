package com.hlcsdev.x.application.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.hlcsdev.x.application.R;
import com.hlcsdev.x.application.datamodels.User;
import com.hlcsdev.x.application.ui.MainActivity;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    private TextView tvName;
    private ImageView ivImage;

    private User user;


    public DetailsFragment newInstance(User user) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", user);
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

        user = (User) getArguments().getSerializable("user");
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        fragmentCallback.onStartDetailsFragment(R.string.details);

        findViews(view);

        setUi();

        return view;
    }


    private void findViews(View view) {
        tvName = view.findViewById(R.id.tvName);
        ivImage = view.findViewById(R.id.ivImage);
    }


    public void setUi() {
        tvName.setText(user.getLogin());

        Picasso.with(getActivity())
                .load(user.getAvatarUrl())
                .into(ivImage);
    }

}
