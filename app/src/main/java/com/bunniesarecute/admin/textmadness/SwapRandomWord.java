package com.bunniesarecute.admin.textmadness;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SwapRandomWord extends Fragment {

    private OnFragmentInteractionListener mListener;


    public SwapRandomWord() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_swap_random_word, container, false);
    }




}