package com.example.kadiripc.cryptoconverter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class ConvertCurrency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_currency);
        Intent intent = getIntent();
        final String price = intent.getStringExtra("price");
        String perc = intent.getStringExtra("percentage");
        String update = intent.getStringExtra("update");
        String vol = intent.getStringExtra("volume24hr");
        String mktcap = intent.getStringExtra("mktcap");
        final String toSymbol = intent.getStringExtra("tosymbol");


        //find all the views and assign the string variables to the textviews
        TextView conv_price = (TextView) findViewById(R.id.conversion_price);
        TextView conv_percentage = (TextView) findViewById(R.id.conversion_percentage24h);
        TextView conv_perc = (TextView) findViewById(R.id.conversion_percentage);
        TextView conv_update = (TextView) findViewById(R.id.conversion_last_update);
        TextView conv_mktcap = (TextView) findViewById(R.id.conversion_mktcap);
        TextView con_vol = (TextView) findViewById(R.id.conversion_volume24h);

        conv_price.setText(price);
        conv_perc.setText(perc);
        conv_percentage.setText(perc + "%");
        conv_update.setText(update);
        conv_mktcap.setText(mktcap);
        con_vol.setText(vol);

        //performs calculations
        EditText editText = (EditText) findViewById(R.id.conversion_base_currency);
        editText.setHint("base currency");
        final TextView quoteCurrency = (TextView) findViewById(R.id.conversion_quote_currency);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    Float value1 = Float.parseFloat(s.toString());
                    //remove the symbol from price
                    String price_modified = price.replace(toSymbol, "");
                    String price_mod_modified = price_modified.replace(",", "");
                    Float value2 = Float.parseFloat(price_mod_modified); //base currency value
                    float product = value1 * value2;
                    quoteCurrency.setText(toSymbol + "" +  String.format("%,.2f", product));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
