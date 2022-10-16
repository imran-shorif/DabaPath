package com.example.dhuro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    Button btnEmailIntent;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        btnEmailIntent = btnEmailIntent.findViewById(R.id.button25);
        btnEmailIntent.setOnClickListener(view -> {
            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setData(Uri.parse("mailto:"));
            Email.putExtra(Intent.EXTRA_EMAIL, new String[]{"dabapath@gmail.com"});
            Email.putExtra(Intent.EXTRA_SUBJECT, "Suggestion");
            Email.setType("plain/text");
            Email.putExtra(Intent.EXTRA_TEXT, "I love DabaPath, moreover I'd like to suggest that: ");

            if (Email.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(Email);
            } else {
                Toast.makeText(getActivity(), "Redirect Error", Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}