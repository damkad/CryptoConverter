package com.example.kadiripc.cryptoconverter;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Kadiri Tosin on 7/24/2017.
 */

class CryptoAsyncLoader extends AsyncTaskLoader<List<CryptoWords>> {
   private final String mUrl;
  public CryptoAsyncLoader(Context context, String vUrl){
      super(context);
      mUrl = vUrl;

  }

    @Override
    public List<CryptoWords> loadInBackground() {

        return QueryUtils.fetchDevData(mUrl);




    }

    @Override
    protected void onStartLoading() {
        forceLoad();

    }
}

