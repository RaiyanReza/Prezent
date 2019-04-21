package com.company.prezent;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class HomeFrag extends Fragment {

    Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DAY_OF_MONTH);

    ArrayList<String> list;

/*    @Override
    public void onResume() {
        super.onResume();
        switch (day) {
            case 0:
                break;
        }
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {// create frag
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) { // for  contents of frags
        super.onActivityCreated(savedInstanceState);
        Resources res = getResources();
        String[] strlist = res.getStringArray(R.array.quotes);
        TextView reading = (TextView) getActivity().findViewById(R.id.quotes_textView);
        switch (day) {
            case 1:
                reading.setText(strlist[0]);
                break;


            case 2:
                reading.setText(strlist[1]);
                break;

            default:
                Toast.makeText(getActivity(), "Failedgit ", Toast.LENGTH_SHORT).show();
                break;


        }




    }

//    private void read() {
//        String line;
//        String line2;
//        try {
//            InputStreamReader sr = new InputStreamReader(getActivity().getAssets().open("quotes.txt")); // get actual file
//            BufferedReader br = new BufferedReader(sr);
//            list = new ArrayList<String>(); //Initialises list as empty Arraylist
//            while (((line = br.readLine()) != null) && ((line2 = br.readLine()) != null)) { //If file is not empty
//                list.add((line + "\n" + line2));//Add the two lines read as one long string into ArrayList list
//            }
//        } catch (NullPointerException e) {
//
//            Toast.makeText(getActivity(), "Dead", Toast.LENGTH_LONG).show();
//        } catch (IOException e){
//
//            Toast.makeText(getActivity(), e.getStackTrace().toString(), Toast.LENGTH_LONG).show();
//        }
//
//    }


}
