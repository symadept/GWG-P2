package com.symadept.apps.cinescan.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.symadept.apps.cinescan.R;
import com.symadept.apps.cinescan.databinding.ActivityMovieDetailBinding;
import com.symadept.apps.cinescan.models.Results;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieDetailActivity extends AppCompatActivity {
    public ActivityMovieDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        Intent intent = getIntent();
        Results movieDetail = intent.getExtras().getParcelable(getResources().getString(R.string.key_detail_extras));
        mBinding.setMovieDetail(movieDetail);

        initViews();
    }

    private void initViews() {
        String imageUrl = "https://image.tmdb.org/t/p/" + "w500" + mBinding.getMovieDetail().backdrop_path;
        Picasso.with(this)
                .load(imageUrl)
                .resize(240, 350)
                .placeholder(R.mipmap.backddrop_image_placeholder)
                .error(R.mipmap.backdrop_image_not_available)
                .into(mBinding.movieDetailBackgroundIv);
        populateTopLayerViews();
    }

    @SuppressWarnings("SpellCheckingInspection")
    private void populateTopLayerViews() {
        String imageUrl = "https://image.tmdb.org/t/p/" + "w500" + mBinding.getMovieDetail().poster_path;
        Picasso.with(this)
                .load(imageUrl)
                .resize(240, 350)
                .placeholder(R.mipmap.poster_image_placeholder)
                .error(R.mipmap.poster_image_not_available)
                .into(mBinding.movieDetailPosterIv);

        mBinding.movieDetailTitleTv.setText(mBinding.getMovieDetail().title);

        String date_after = formatDateFromstring("yyyy-MM-dd", "MMMM dd, yyyy", mBinding.getMovieDetail().release_date);
        mBinding.movieDetailYearTv.setText(date_after);

        mBinding.movieDetailOverviewHeaderTv.setText(R.string.overview_label);
        mBinding.movieDetailOverviewMessageTv.setText(mBinding.getMovieDetail().overview);

        mBinding.ratingBar.setRating((float) mBinding.getMovieDetail().vote_average/2);
        mBinding.voteCountTv.setText(getResources().getString(R.string.open_bracket)
                + mBinding.getMovieDetail().vote_count
                + getResources().getString(R.string.close_bracket));
    }

    public static String formatDateFromstring(String inputFormat, String outputFormat, String inputDate){
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            Date parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            Log.e("Error", "ParseException - dateFormat");
        }

        return outputDate;

    }
}
