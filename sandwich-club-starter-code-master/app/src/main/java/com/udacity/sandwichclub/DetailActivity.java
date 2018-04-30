package com.udacity.sandwichclub;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.databinding.ActivityDetailBinding;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private ActivityDetailBinding mBindings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindings = DataBindingUtil.setContentView(this, R.layout.activity_detail);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(mBindings.imageIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        mBindings.setSandwich(sandwich);

        if(sandwich.getAlsoKnownAs() == null || sandwich.getAlsoKnownAs().size()<1) {
            mBindings.alsoKnownAsTv.setText(" ");
        } else {
            String commaSeparatedItems = TextUtils.join(",", sandwich.getAlsoKnownAs());
            mBindings.alsoKnownAsTv.setText(commaSeparatedItems);
        }

        if(sandwich.getIngredients() == null || sandwich.getIngredients().size()<1) {
            mBindings.ingredientsTv.setText(" ");
        } else {
            String commaSeparatedItems = "* " + TextUtils.join("\n* ", sandwich.getIngredients());
            mBindings.ingredientsTv.setText(commaSeparatedItems);
        }
    }
}
