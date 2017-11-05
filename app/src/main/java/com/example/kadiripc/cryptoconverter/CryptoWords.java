package com.example.kadiripc.cryptoconverter;

/**
 * Created by Kadiri Tosin on 7/22/2017.
 */
public class CryptoWords {
    //attributes CryptoWords objects includes
    //price, tosymbol, fromsymbol, volume24hr, changepct24hr, lastvolume
    //lastmarket, lastupdate, fromsymbol(txt), tosymbol(txt)
    private String mPrice, mToSYmbol, mFromSymbol, mVolume24hr, mChangepct24hr,
            mLastVolume, mLastMarket, mLastUpdate, mFromSymbol_text, mToSymbol_text, mktcap;

    public CryptoWords(String mPrice, String mToSYmbol, String mFromSymbol, String mVolume24hr,
                       String mChangepct24hr, String mLastVolume, String mLastMarket, String mLastUpdate,
                       String mFromSymbol_text, String mToSymbol_text, String mktcap) {
        this.mPrice = mPrice;
        this.mToSYmbol = mToSYmbol;
        this.mFromSymbol = mFromSymbol;
        this.mVolume24hr = mVolume24hr;
        this.mChangepct24hr = mChangepct24hr;
        this.mLastVolume = mLastVolume;
        this.mLastMarket = mLastMarket;
        this.mLastUpdate = mLastUpdate;
        this.mFromSymbol_text = mFromSymbol_text;
        this.mToSymbol_text = mToSymbol_text;
        this.mktcap = mktcap;
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getmToSYmbol() {
        return mToSYmbol;
    }

    public String getmFromSymbol() {
        return mFromSymbol;
    }

    public String getmVolume24hr() {
        return mVolume24hr;
    }

    public String getmChangepct24hr() {
        return mChangepct24hr;
    }

    public String getmLastVolume() {
        return mLastVolume;
    }

    public String getmLastMarket() {
        return mLastMarket;
    }

    public String getmLastUpdate() {
        return mLastUpdate;
    }

    public String getmFromSymbol_text() {
        return mFromSymbol_text;
    }

    public String getmToSymbol_text() {
        return mToSymbol_text;
    }
    public String getMktcap(){
        return mktcap;
    }
}
