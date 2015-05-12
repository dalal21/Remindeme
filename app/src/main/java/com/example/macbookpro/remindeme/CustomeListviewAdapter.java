package com.example.macbookpro.remindeme;

import java.util.List;

import com.example.macbookpro.remindeme.set_of_alerts.Listviewitem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



public class CustomeListviewAdapter extends BaseAdapter{

    LayoutInflater inflater;
    List<Listviewitem> items;

    public CustomeListviewAdapter(Activity context, List<Listviewitem> items) {
        super();

        this.items = items;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Listviewitem li =items.get(position);



        View vi=convertView;

        if(convertView==null)
            vi = inflater.inflate(R.layout.item, null);

        ImageView img=(ImageView) vi.findViewById(R.id.imgThumbnail);
        TextView tx=(TextView) vi.findViewById(R.id.txtTitle);
        TextView tx2=(TextView) vi.findViewById(R.id.txtSubTitle);

        img.setImageResource(li.bd);
        tx.setText(li.Title);
        tx2.setText(li.subti);
        return vi;
    }
}
