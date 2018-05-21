package com.symadept.apps.cinescan.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.symadept.apps.cinescan.R;
import com.symadept.apps.cinescan.models.CatalogResponse;
import com.symadept.apps.cinescan.models.Results;

/**
 * Created by shaimu8 on 5/20/18.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogAdapterViewHolder> {
    private CatalogResponse response;
    private Context context;

    public CatalogAdapter(CatalogResponse response, Context context) {
        this.response = response;
        this.context = context;
    }

    @Override
    public CatalogAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_row_layout, parent, false);
        return new CatalogAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CatalogAdapterViewHolder holder, int position) {
        holder.tv_android.setVisibility(View.GONE);
//        holder.tv_android.setText(android.get(position).getAndroid_version_name());
        Results item = response.results.get(position);
        String imageUrl = "https://image.tmdb.org/t/p/" + "w500" + item.poster_path;
        Picasso.with(context).load(imageUrl).resize(240, 350).into(holder.img_android);
    }

    @Override
    public int getItemCount() {
        return response.results.size();
    }

    public class CatalogAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_android;
        private ImageView img_android;

        public CatalogAdapterViewHolder(View view) {
            super(view);

            tv_android = (TextView)view.findViewById(R.id.catalog_row_layout_tv);
            img_android = (ImageView) view.findViewById(R.id.catalog_row_layout_iv);
        }
    }
}
