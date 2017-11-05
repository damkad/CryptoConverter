package com.example.kadiripc.cryptoconverter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kadiri Tosin on 7/22/2017.
 */

class CryptoAdapter extends ArrayAdapter<CryptoWords> implements Filterable {
    private List<CryptoWords> mArrayList;
    private final List<CryptoWords> mFilterList;

    public CryptoAdapter(Context context, List<CryptoWords> vArrayList) {
        super(context, 0, vArrayList);
        mArrayList = vArrayList;
        mFilterList = vArrayList;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Nullable
    @Override
    public CryptoWords getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public int getPosition(@Nullable CryptoWords item) {
        return mArrayList.indexOf(item);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        CryptoFilter cryptoFilter = new CryptoFilter();
        return cryptoFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View recycler = convertView;
        if (recycler == null) {
            recycler = LayoutInflater.from(getContext()).inflate(R.layout.template, parent, false);
        }
        //gets the position of the CryptoWords
        CryptoWords current = getItem(position);
        //finds the toSymbol view
        TextView from_symbol_text = (TextView) recycler.findViewById(R.id.raw_fromSymbol);
        TextView to_symbol_text = (TextView) recycler.findViewById(R.id.raw_toSymbol);
        TextView volume_24hr = (TextView) recycler.findViewById(R.id.volume24hr);
        TextView price = (TextView) recycler.findViewById(R.id.price);
        TextView last_update = (TextView) recycler.findViewById(R.id.lastUpdate);
        TextView last_market = (TextView) recycler.findViewById(R.id.marketName);
        TextView volume_traded = (TextView) recycler.findViewById(R.id.lastVolumeTraded);
        assert current != null;

        from_symbol_text.setText(current.getmFromSymbol_text());
        to_symbol_text.setText(current.getmToSymbol_text());
        volume_24hr.setText("Vol: "+current.getmVolume24hr());
        price.setText(current.getmPrice());
        last_update.setText(current.getmLastUpdate());
        last_market.setText(current.getmLastMarket());
        volume_traded.setText(current.getmLastVolume());

        ImageView base_currency = (ImageView) recycler.findViewById(R.id.base_image);
        ImageView quote_currency = (ImageView) recycler.findViewById(R.id.quote_image);
        String symbol_id = current.getmFromSymbol_text();
        String symbol_to_id = current.getmToSymbol_text();
        //condition for the base currency
        if (symbol_id.contains("BTC")) {
            base_currency.setImageResource(R.drawable.btc);
        }
        if (symbol_id.contains("ETH")) {
            base_currency.setImageResource(R.drawable.eth);
        }
        //condition for the quote currency
        if (symbol_to_id.contains("JPY") || symbol_to_id.contains("CNY")) {
            quote_currency.setImageResource(R.drawable.jpy);
        } else if (symbol_to_id.contains("KRW")) {
            quote_currency.setImageResource(R.drawable.krw);
        } else if (symbol_to_id.contains("EUR")) {
            quote_currency.setImageResource(R.drawable.euro);
        } else if (symbol_to_id.contains("USDT")) {
            quote_currency.setImageResource(R.drawable.usdt);
        } else if (symbol_to_id.contains("GBP")) {
            quote_currency.setImageResource(R.drawable.gbp);
        } else if (symbol_to_id.contains("RUB")) {
            quote_currency.setImageResource(R.drawable.rub);
        } else if (symbol_to_id.contains("VND")) {
            quote_currency.setImageResource(R.drawable.vnd);
        } else if (symbol_to_id.contains("ZAR")) {
            quote_currency.setImageResource(R.drawable.zar);
        } else if (symbol_to_id.contains("MYR")) {
            quote_currency.setImageResource(R.drawable.myr);
        } else if (symbol_to_id.contains("IDR")) {
            quote_currency.setImageResource(R.drawable.idr);
        } else if (symbol_to_id.contains("NGN")) {
            quote_currency.setImageResource(R.drawable.ngn);
        } else if (symbol_to_id.contains("PLN")) {
            quote_currency.setImageResource(R.drawable.pln);
        } else quote_currency.setImageResource(R.drawable.dollars);
        return recycler;
    }

    //define a filter class
    private class CryptoFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //create an instance of FilterResults
            FilterResults filterResults = new FilterResults();
            //checks if there is a list item to filter
            if (mFilterList != null && mFilterList.size() > 0) {
                //create a new filter list
                List<CryptoWords> filterList = new ArrayList<>();
                //checks for every javaDel in the list
                for (CryptoWords cryptoWords : mFilterList) {
                    //checks if it matches with the javadel name
                    if (cryptoWords.getmToSymbol_text().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        //add the javadel to the new filterlist created
                        filterList.add(cryptoWords);
                    }
                }
                //update the filterResults
                filterResults.values = filterList;
                filterResults.count = filterList.size();
            } else {
                filterResults.values = mFilterList;
                filterResults.count = mFilterList.size();
            }


            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mArrayList = (List<CryptoWords>) results.values;
            notifyDataSetChanged();
        }
    }


}
