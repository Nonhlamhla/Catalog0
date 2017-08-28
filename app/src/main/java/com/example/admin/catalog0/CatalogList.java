package com.example.admin.catalog0;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Admin on 8/28/2017.
 */

public class CatalogList extends ArrayAdapter<Catalog> {


    private Activity context;
    private List<Catalog> catalogList;
    private Activity applicationContext;

    public CatalogList(Activity context, List<Catalog> catalogList) {
        super(context, R.layout.list_layout, catalogList);
        this.context = context;
        this.catalogList = catalogList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView item1 = listViewItem.findViewById(R.id.item1);
        ImageButton imageButton1 = listViewItem.findViewById(R.id.imageButton1);

        Catalog catalog = catalogList.get(position);
        item1.setText(catalog.getCatalogtitle());



        Glide.with(context)
                .load(catalog.getCatalogimageurl())
                .into(imageButton1);



        return listViewItem;
    }
}





