package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter
{
    private ArrayList<String> list = new ArrayList<String>();
    MainActivity mainActivity = new MainActivity();

    public MyCustomAdapter(ArrayList<String> list, MainActivity mainActivity)
    {
        this.list = list;
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int i)
    {
        return list.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) mainActivity.getSystemService(mainActivity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.itemtodo,null);
        }

        TextView task_title = (TextView) view.findViewById(R.id.task_title);
        task_title.setText(list.get(i));
        return view;
    }
}
