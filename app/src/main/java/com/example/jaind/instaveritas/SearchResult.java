package com.example.jaind.instaveritas;

/**
 * Created by jaind on 21/8/16.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class SearchResult extends Activity {
    static private SearchAdapter adapter;

    Context conetxt;
    ListView search_list;
    TextView more;
    int start = 0;
    int end;
    int number_of_item = 2;
    ArrayList<SearchData> sd = new ArrayList<SearchData>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serchresult);
        conetxt = this;


        search_list = (ListView) findViewById(R.id.searchlist);
        more = (TextView) findViewById(R.id.more);

        end = start + number_of_item;

        while(start<end)
        {
            if(start >= Appcontroller.searchDataList.size())
            {
                more.setEnabled(false);
                break;
            }
            else
            {
                sd.add(Appcontroller.searchDataList.get(start));
                start ++;
            }
        }



        adapter = new SearchAdapter(conetxt,sd);
        search_list.setAdapter(adapter);
        search_list.setItemsCanFocus(true);


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sd.clear();
                end = start + number_of_item;
                while(start<end)
                {
                    if(start >= Appcontroller.searchDataList.size())
                    {
                        more.setEnabled(false);
                        break;
                    }
                    else
                    {
                        sd.add(Appcontroller.searchDataList.get(start));
                        start ++;
                    }
                }
                adapter.notifyDataSetChanged();


            }

        });


    }

}

