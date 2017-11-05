package com.example.kadiripc.cryptoconverter;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<CryptoWords>> {

    private static final String CRYPTOSTR = "https://min-api.cryptocompare.com/data/pricemultifull";

    private int KEY = 1;
    private CryptoAdapter cryptoAdapter;
    private ImageView emptyView;
    private android.app.LoaderManager loaderManager;
    private ProgressBar progressBar2;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);

        cryptoAdapter = new CryptoAdapter(this, new ArrayList<CryptoWords>());
        //finds the listView reference
        listView = (ListView) findViewById(R.id.rootView);
        listView.setAdapter(cryptoAdapter);


        emptyView = (ImageView) findViewById(R.id.progress);
        progressBar2 = (ProgressBar) findViewById(R.id.progress2);


        if (cryptoAdapter.isEmpty()) {

            listView.setEmptyView(emptyView);

            emptyView.setOnClickListener(new View.OnClickListener() {


                                             @Override
                                             public void onClick(View view) {
                                                 check();
                                             }
                                         }
            );
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CryptoWords current = cryptoAdapter.getItem(i);
                Intent intent = new Intent(getApplicationContext(), ConvertCurrency.class);
                assert current != null;
                intent.putExtra("price", current.getmPrice());
                intent.putExtra("percentage", current.getmChangepct24hr());
                intent.putExtra("update", current.getmLastUpdate());
                intent.putExtra("volume24hr", current.getmVolume24hr());
                intent.putExtra("mktcap", current.getMktcap());
                intent.putExtra("tosymbol", current.getmToSYmbol());


                startActivity(intent);
            }
        });

        if (hasNetwork()) {
            loaderManager = getLoaderManager();
            loaderManager.initLoader(KEY, null, this);
        } else {

            progressBar2.setVisibility(View.GONE);

            emptyView.setImageResource(R.drawable.progress_internet);
        }

    }


    private void check() {
        KEY = KEY + 1;

        emptyView.setImageResource(0);

        progressBar2.setVisibility(View.VISIBLE);

        if (cryptoAdapter.isEmpty()) {

            listView.setEmptyView(emptyView);
        }

        if (hasNetwork()) {
            loaderManager = getLoaderManager();
            loaderManager.initLoader(KEY, null, MainActivity.this);

        } else {
            progressBar2.setVisibility(View.GONE);

            emptyView.setImageResource(R.drawable.progress_internet);
        }


        Toast.makeText(getApplicationContext(), "reloading", Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menus, menu);
        //

        SearchManager searchManager = (SearchManager) getApplicationContext().getSystemService(SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_bar).getActionView();
        searchView.setIconified(false);
        searchView.setQueryHint("Quote currency");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cryptoAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);

            return true;
        }
        if (id == R.id.settings) {
            startActivity(new Intent(this, Settings.class));
            return true;
        }

        return true;
    }


    @Override
    public Loader<List<CryptoWords>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String orderBy = sharedPreferences.getString(getString(R.string.settings_order_key), getString(R.string.settings_order_default));


        Uri baseUri = Uri.parse(CRYPTOSTR);
        Uri.Builder builder = baseUri.buildUpon();

        builder.appendQueryParameter("fsyms", orderBy);

        builder.appendQueryParameter("tsyms", "EUR,USD,IDR,KRW,CNY,USDT,JPY,AUD,CAD,MXN,VND,HKD,SGD,BRL,PLN,MYR,RUB,GBP,NGN,ZAR");
        String dam = builder.toString();
        String damm = dam.replace("%2C", ",");

        return new CryptoAsyncLoader(this, damm);
    }

    @Override
    public void onLoadFinished(Loader<List<CryptoWords>> loader, List<CryptoWords> cryptoWordses) {


        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progress2);
        cryptoAdapter.clear();
        emptyView.setImageResource(R.drawable.progress_list);
        if (!cryptoWordses.isEmpty() || cryptoWordses != null) {

            progressBar2.setVisibility(View.GONE);
            cryptoAdapter.addAll(cryptoWordses);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<CryptoWords>> loader) {
        cryptoAdapter.clear();
    }

    private boolean hasNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }
}
