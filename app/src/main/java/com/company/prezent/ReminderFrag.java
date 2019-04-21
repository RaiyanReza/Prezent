package com.company.prezent;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class ReminderFrag extends Fragment {
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter; //To display our ArrayList into ListView
    private ListView ilist;
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    DatePickerDialog dp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_reminder, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ilist = (ListView)getView().findViewById(R.id.itemlist);
        list = new ArrayList<String>();
        read(); //Fill ArrayList with whatever previous reminder added
        ListViewListener();
        final Button button = getActivity().findViewById(R.id.addBut);
        final Button button2 = getActivity().findViewById(R.id.dateBut);
        final EditText etext = (EditText)getView().findViewById(R.id.edit);
        final TextView ttext = (TextView)getView().findViewById(R.id.tv);
        switch (hour){
            case 1: case 2: case 3: case 4: case 18: case 19: case 20: case 21: case 22: case 23: case 0:
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list){
                    @Override
                    public View getView(int pos, View view, ViewGroup parent){
                        View v = super.getView(pos, view, parent);
                        TextView textView = (TextView) v.findViewById(android.R.id.text1);//Convert listView into textView
                        textView.setTextColor(Color.WHITE);
                        button.setTextColor(Color.WHITE);
                        button2.setTextColor(Color.WHITE);
                        etext.setTextColor(Color.WHITE);
                        etext.setHintTextColor(Color.WHITE);
                        ttext.setTextColor(Color.WHITE);
                        ttext.setHintTextColor(Color.WHITE);
                        return v;
                    }
                };
                ilist.setAdapter(adapter);
                break;
            case 5: case 6: case 7: case 8: case 9: case 10: case 11: case 12:
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list){
                    @Override
                    public View getView(int pos, View view, ViewGroup parent){
                        View v = super.getView(pos, view, parent);
                        TextView textView = (TextView) v.findViewById(android.R.id.text1);//Convert listView into textView
                        textView.setTextColor(Color.BLACK);
                        button.setTextColor(Color.BLACK);
                        button2.setTextColor(Color.BLACK);
                        etext.setTextColor(Color.BLACK);
                        etext.setHintTextColor(Color.BLACK);
                        ttext.setTextColor(Color.BLACK);
                        ttext.setHintTextColor(Color.BLACK);
                        return v;
                    }
                };
                ilist.setAdapter(adapter);
                break;
            case 13: case 14: case 15: case 16: case 17:
                adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list){
                    @Override
                    public View getView(int pos, View view, ViewGroup parent){
                        View v = super.getView(pos, view, parent);
                        TextView textView = (TextView) v.findViewById(android.R.id.text1);//Convert listView into textView
                        textView.setTextColor(Color.BLACK);
                        button.setTextColor(Color.BLACK);
                        button2.setTextColor(Color.BLACK);
                        etext.setTextColor(Color.BLACK);
                        etext.setHintTextColor(Color.BLACK);
                        ttext.setTextColor(Color.BLACK);
                        ttext.setHintTextColor(Color.BLACK);
                        return v;
                    }
                };
                ilist.setAdapter(adapter);
                break;
            default:
                break;
        }
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dp = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker dp, int cyear, int cmonth, int cday){
                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DAY_OF_MONTH, day);
                        ttext.setText(cday + "/" + cmonth + "/" + cyear);
                    }
                }, year, month, year);
                dp.show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((etext.getText().toString()).equals("")) && !((ttext.getText().toString()).equals(""))) {
                    String text = ttext.getText().toString() + "\n" + etext.getText().toString(); //Convert Text into String
                    adapter.add(text); //Add String into ArrayList
                    etext.setText(""); //Reset Input text for new input
                    ttext.setText("");
                    write();
                }else if((ttext.getText().toString().equals(""))){
                    Toast.makeText(getActivity(), "Pick a Date", Toast.LENGTH_LONG).show();
                }//Update txt file
            }
        });

    }

    private void ListViewListener(){
        ilist.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener(){ //When Click and Hold action is detected
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter2, View view, int pos, long id) {
                        final int dpos = pos;
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Delete")
                                .setMessage("Delete Reminder?")
                                .setPositiveButton(android.R.string.yes,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                list.remove(dpos); //removes from list
                                                adapter.notifyDataSetChanged(); //updates adapter
                                                write(); //Update txt file
                                                Toast.makeText(getActivity(), "Reminder Deleted", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                .setNegativeButton(android.R.string.no,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getActivity(), "Action Cancelled", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        /*list.remove(pos); //removes from list
                        adapter.notifyDataSetChanged(); //updates adapter
                        write(); //Update txt file*/
                        return true; //confirms LongClick action has been handled
                    }

                });
    }

    private void read(){
        File files = getContext().getFilesDir(); //Get file directory
        File reminder = new File(files, "reminder.txt"); //Get actual file
        String line;
        String line2;
        try{
            InputStream fs = new FileInputStream(reminder);
            InputStreamReader sr = new InputStreamReader(fs);
            BufferedReader br = new BufferedReader(sr);
            list = new ArrayList<String>(); //Initialises list as empty Arraylist
            while(((line = br.readLine()) != null) && ((line2 = br.readLine()) != null)){ //If file is not empty
                list.add((line + "\n" + line2)); //Add the two lines read as one long string into ArrayList list
            }
        }catch (Exception e){
        }
    }
    private void write(){
        File files = getContext().getFilesDir(); //Get file directory
        File reminder = new File(files, "reminder.txt"); //Get actual file
        try{
            FileUtils.writeLines(reminder, list); //Write Arraylist content into file
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
