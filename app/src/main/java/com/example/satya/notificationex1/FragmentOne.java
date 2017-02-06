package com.example.satya.notificationex1;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    EditText et1,et2,et3,et4;
    Button bt1,bt2;
    //these 2 declaration is very important for Notification
    NotificationManager notificationManager ;
    NotificationCompat.Builder builder;

    public FragmentOne() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        et1= (EditText) v.findViewById(R.id.et1);
        et2= (EditText) v.findViewById(R.id.et2);
        et3= (EditText) v.findViewById(R.id.et3);
        et4= (EditText) v.findViewById(R.id.et4);
        bt1= (Button) v.findViewById(R.id.bt1);
        bt2= (Button) v.findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setContentTitle(et2.getText().toString());
                //here 1 is used to update same notification
                notificationManager.notify(1,builder.build());
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Actual code for displaying Notification
                notificationManager= (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                builder= new NotificationCompat.Builder(getActivity());
                //creating small icon
                builder.setSmallIcon(R.mipmap.ic_launcher);
                //creating Ticker
                builder.setTicker(et1.getText().toString());
                //getDrawable(R.drawable.splash) it is used to convert Bigimage into Bitmap image
                BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.ic_tram_black_24dp);
                //fetching data from Bitmap file
                builder.setLargeIcon(bitmapDrawable.getBitmap());
                //here fetching Titele from et2
                builder.setContentTitle(et2.getText().toString());
                builder.setContentText(et3.getText().toString());
                builder.setContentInfo(et4.getText().toString());
                //we will wride code for clicking notification screen
                Intent intent= new Intent(getActivity(),MainActivity.class);
                //here keeping notification as pending till not clicking
                PendingIntent  pendingIntent = PendingIntent.getActivity(getActivity(),0,intent,0);
                //here  intent remember about pending intent
                builder.setContentIntent(pendingIntent);
                //after seeing notification it will autometically removed from notification Bar
               // builder.setAutoCancel(true);
                //now push notification to top bar
                //here 1 is the notification unique id
                notificationManager.notify(1,builder.build());
                //u can deleteing notification
               // notificationManager.cancel(1);
                //u can deleteing Allnotification
                //by taking another button we can test
                //notificationManager.cancelAll();
                //hiw to update notification
               // et1.setText("");
                //et2.setText("");
                //et3.setText("");
                //et4.setText("");
               // et1.requestFocus();
            }
        });
        return v;
    }

}
