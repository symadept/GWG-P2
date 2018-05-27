package com.symadept.apps.cinescan.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;

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
        Results movieDetail = intent.getExtras().getParcelable("movieDetail");
        mBinding.setMovieDetail(movieDetail);

        initViews();
    }

    private void initViews() {
        String imageUrl = "https://image.tmdb.org/t/p/" + "w500" + mBinding.getMovieDetail().backdrop_path;
        Picasso.with(this)
                .load(imageUrl)
                .resize(240, 350)
                .into(mBinding.movieDetailBackgroundIv, new Callback() {
                    @Override
                    public void onSuccess() {
                        populateTopLayerViews();
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    private void populateTopLayerViews() {
        String imageUrl = "https://image.tmdb.org/t/p/" + "w500" + mBinding.getMovieDetail().poster_path;
        Picasso.with(this).load(imageUrl).resize(240, 350).into(mBinding.movieDetailPosterIv);

        mBinding.movieDetailTitleTv.setText(mBinding.getMovieDetail().title);

        String date_after = formateDateFromstring("yyyy-MM-dd", "MMMM dd, yyyy", mBinding.getMovieDetail().release_date);
        mBinding.movieDetailYearTv.setText(date_after);

        mBinding.movieDetailOverviewHeaderTv.setText(R.string.overview_label);
        mBinding.movieDetailOverviewMessageTv.setText(mBinding.getMovieDetail().overview);

        mBinding.ratingBar.setRating((float) mBinding.getMovieDetail().vote_average/2);
        mBinding.voteCountTv.setText("(" + mBinding.getMovieDetail().vote_count+ ")");
    }

    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate){

        inputDate = "2018-24-05";
        Date parsed = null;
        String outputDate = "";

        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
//            Calendar now= Calendar.getInstance();
//            Calendar yourDate = Calendar.getInstance();
//            yourDate.setTime(parsed);
//            boolean isFuture = now.before(yourDate);



            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
            Log.e("Error", "ParseException - dateFormat");
        }

        return outputDate;

    }
}
