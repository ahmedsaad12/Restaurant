package com.creativeshare.restaurant.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.creativeshare.restaurant.Actifities.MainActivity;
import com.creativeshare.restaurant.Language.Language;
import com.creativeshare.restaurant.R;
import com.creativeshare.restaurant.preferences.Preferences;


public class Fragment_Home extends Fragment {
    private AHBottomNavigation bottomNavigationView;
    private MainActivity activity;
    private TextView tvtxt, lang;
    private Preferences preferences;
    private String current_language;

    public static Fragment_Home newInstance() {
        return new Fragment_Home();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        intitview(view);
        setUpBottomNav();
        return view;
    }

    private void intitview(View view) {
        bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        tvtxt = view.findViewById(R.id.tv_title);
        lang = view.findViewById(R.id.lang);
        activity = (MainActivity) getActivity();
        preferences = Preferences.getInstance();
        current_language = preferences.getlang(activity);
        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_language.equals("ar")) {
                    Language.setNewLocale(activity, "en");
                } else {
                    Language.setNewLocale(activity, "ar");
                }
                activity.finish();
                Intent intent = activity.getIntent();
                startActivity(intent);
            }
        });
    }

    private void setUpBottomNav() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.home, R.drawable.ic_home, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.Cart, R.drawable.ic_cart, R.color.colorPrimary);
        bottomNavigationView.addItem(item1);
        bottomNavigationView.addItem(item2);
        bottomNavigationView.setAccentColor(ContextCompat.getColor(activity, R.color.white));
        bottomNavigationView.setDefaultBackgroundColor(ContextCompat.getColor(activity, R.color.colorPrimary));
        bottomNavigationView.setInactiveColor(ContextCompat.getColor(activity, R.color.white));
        bottomNavigationView.setForceTint(true);
        bottomNavigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigationView.setColored(false);
        UpdateAHBottomNavigationPosition(0);

        bottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
                        activity.DisplayFragmentCatogry();
                        break;
                    case 1:
                        activity.DisplayFragmentCart();
                        break;


                }
                return false;
            }
        });
    }

    public void UpdateAHBottomNavigationPosition(int pos) {

        if (pos == 0) {
            tvtxt.setText(getString(R.string.home));
        } else if (pos == 1) {
            tvtxt.setText(getString(R.string.Cart));

        }
        bottomNavigationView.setSelectedBackgroundVisible(true);

        bottomNavigationView.setCurrentItem(pos, false);
    }
}
