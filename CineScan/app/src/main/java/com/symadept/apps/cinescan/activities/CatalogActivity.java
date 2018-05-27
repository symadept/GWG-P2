package com.symadept.apps.cinescan.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.symadept.apps.cinescan.R;
import com.symadept.apps.cinescan.adapters.CatalogAdapter;
import com.symadept.apps.cinescan.models.CatalogResponse;
import com.symadept.apps.cinescan.network.RequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CatalogActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CatalogAdapter adapter;
    private CatalogResponse catalogResponse;
    private int currentSortMenuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_popular:
                loadJSON(this, item.getItemId());
                return true;
            case R.id.menu_rated:
                loadJSON(this, item.getItemId());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initViews(){
        recyclerView = findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        currentSortMenuId = R.string.menu_popular;
        setScreenTitle(currentSortMenuId);
        loadJSON(this, R.id.menu_popular);
    }

    private void loadJSON(final Context context, int context_menu_id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);

        Call<CatalogResponse> call = null;

        if(context_menu_id == R.id.menu_rated) {
            currentSortMenuId = R.string.menu_rated;
            call = request.getTopRatedMovies();
        } else {
            currentSortMenuId = R.string.menu_popular;
            call = request.getMostPopularMovies();
        }

        call.enqueue(new Callback<CatalogResponse>() {
            @Override
            public void onResponse(Call<CatalogResponse> call, Response<CatalogResponse> response) {
                catalogResponse = response.body();
                adapter = new CatalogAdapter(catalogResponse, context);
                recyclerView.setAdapter(adapter);
                setScreenTitle(currentSortMenuId);
            }

            @Override
            public void onFailure(Call<CatalogResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

    private void setScreenTitle(int menuId) {
        String menuTitle = getResources().getString(menuId);
        String appName = getResources().getString(R.string.app_name);
        setTitle(appName + " - " + menuTitle);
    }
}
