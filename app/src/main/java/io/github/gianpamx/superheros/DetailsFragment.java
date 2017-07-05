package io.github.gianpamx.superheros;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.github.gianpamx.superheros.servicelocator.ServiceLocator;
import io.github.gianpamx.superheros.viewmodel.CharacterViewModel;

public class DetailsFragment extends LifecycleFragment {
    public static final String CHARACTER_ID = "1011334";

    private ImageView thumbnail;
    private TextView nameTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);

        thumbnail = view.findViewById(R.id.thumbnail);
        nameTextView = view.findViewById(R.id.name);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CharacterViewModel characterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);

        characterViewModel.init(CHARACTER_ID, ServiceLocator.getInstance().getCharacterRepository());

        characterViewModel.getCharacter().observe(this, new Observer<Character>() {
            @Override
            public void onChanged(@Nullable Character character) {
                nameTextView.setText(character.name);
            }
        });
    }
}
