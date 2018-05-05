package com.symadept.apps.cinescan.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.symadept.apps.cinescan.R;

public class CatalogAdapter extends BaseAdapter {
    private Context mContext;

    public CatalogAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.catalog_gv_item, null);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.catalog_image_iv);
        imageView.setImageResource(mThumbIds[position]);

//        imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setPadding(8, 8, 8, 8);

        return convertView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.mipmap.sample_2, R.mipmap.sample_3,
            R.mipmap.sample_4, R.mipmap.sample_5,
            R.mipmap.sample_6, R.mipmap.sample_7,
            R.mipmap.sample_0, R.mipmap.sample_1,
            R.mipmap.sample_2, R.mipmap.sample_3,
            R.mipmap.sample_4, R.mipmap.sample_5,
            R.mipmap.sample_6, R.mipmap.sample_7,
            R.mipmap.sample_0, R.mipmap.sample_1,
            R.mipmap.sample_2, R.mipmap.sample_3,
            R.mipmap.sample_4, R.mipmap.sample_5,
            R.mipmap.sample_6, R.mipmap.sample_7
    };
}
