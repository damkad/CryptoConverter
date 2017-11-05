package com.example.kadiripc.cryptoconverter;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kadiri Tosin on 7/22/2017.
 */


final class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();


    public static List<CryptoWords> fetchDevData(String CryptoStrings) {
        List<CryptoWords> cryptoWordsList = null;
        URL url = convertToUrl(CryptoStrings);
        String JSONResponse;
        try {
            JSONResponse = makeHttpConnect(url);
            cryptoWordsList = ExtractList(JSONResponse);

        } catch (IOException e) {
            Log.e(LOG_TAG, "error due to on/off, ", e);

        }
        return cryptoWordsList;

    }


    private QueryUtils() {
    }
    //-takes in the url_string and converts to url_URL
    //takes in the url_URL and converts it to JSONResponse
    //takes in bytes of data and converts it to strings
    //takes in JSONResponse and returns a list of crypto currencies
    //defines a method that sums up the process

    private static URL convertToUrl(String url_String) {
        URL url = null;
        try {
            url = new URL(url_String);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Malformed error due to ", e);
        }
        return url;
    }

    private static String makeHttpConnect(URL url_URl) throws IOException {
        String JSONResponse = "";
        if (url_URl == null) {
            return JSONResponse;
        }
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;

        try {
            httpURLConnection = (HttpURLConnection) url_URl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                JSONResponse = extractString(inputStream);
            } else {
                Log.e("Bad request", httpURLConnection.getResponseMessage());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error due to on/off, ", e);
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return JSONResponse;

    }

    private static String extractString(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();


        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            output.append(line);
            line = bufferedReader.readLine();
        }
        return output.toString();
    }


    private static List<CryptoWords> ExtractList(String JSONResponse) {

        List<CryptoWords> cryptoWordsList = new ArrayList<>();
        if (JSONResponse.isEmpty()) {
            return cryptoWordsList;
        }
        try {
            JSONObject rootObject = new JSONObject(JSONResponse);
            JSONObject object = rootObject.getJSONObject("DISPLAY"); //this is line with the currency symbol
            JSONObject raw_object = rootObject.getJSONObject("RAW"); //this is in line with th currency text


            //checks if the base currency is bitcoin
            if (JSONResponse.contains("\"FROMSYMBOL\":\"BTC\"")) {
                JSONObject btc = object.getJSONObject("BTC");
                JSONObject raw_btc = raw_object.getJSONObject("BTC");

                JSONObject eur = btc.getJSONObject("EUR");
                JSONObject raw_eur = raw_btc.getJSONObject("EUR");

                JSONObject usd = btc.getJSONObject("USD");
                JSONObject raw_usd = raw_btc.getJSONObject("USD");


                JSONObject krw = btc.getJSONObject("KRW");
                JSONObject raw_krw = raw_btc.getJSONObject("KRW");

                JSONObject cny = btc.getJSONObject("CNY");
                JSONObject raw_cny = raw_btc.getJSONObject("CNY");

                JSONObject usdt = btc.getJSONObject("USDT");
                JSONObject raw_usdt = raw_btc.getJSONObject("USDT");

                JSONObject jpy = btc.getJSONObject("JPY");
                JSONObject raw_jpy = raw_btc.getJSONObject("JPY");

                JSONObject aud = btc.getJSONObject("AUD");
                JSONObject raw_aud = raw_btc.getJSONObject("AUD");

                JSONObject cad = btc.getJSONObject("CAD");
                JSONObject raw_cad = raw_btc.getJSONObject("CAD");

                JSONObject mxn = btc.getJSONObject("MXN");
                JSONObject raw_mxn = raw_btc.getJSONObject("MXN");

                JSONObject vnd = btc.getJSONObject("VND");
                JSONObject raw_vnd = raw_btc.getJSONObject("VND");

                JSONObject hkd = btc.getJSONObject("HKD");
                JSONObject raw_hkd = raw_btc.getJSONObject("HKD");

                JSONObject sgd = btc.getJSONObject("SGD");
                JSONObject raw_sgd = raw_btc.getJSONObject("SGD");

                JSONObject brl = btc.getJSONObject("BRL");
                JSONObject raw_brl = raw_btc.getJSONObject("BRL");

                JSONObject pln = btc.getJSONObject("PLN");
                JSONObject raw_pln = raw_btc.getJSONObject("PLN");

                JSONObject myr = btc.getJSONObject("MYR");
                JSONObject raw_myr = raw_btc.getJSONObject("MYR");

                JSONObject rub = btc.getJSONObject("RUB");
                JSONObject raw_rub = raw_btc.getJSONObject("RUB");

                JSONObject gbp = btc.getJSONObject("GBP");
                JSONObject raw_gbp = raw_btc.getJSONObject("GBP");

                JSONObject ngn = btc.getJSONObject("NGN");
                JSONObject raw_ngn = raw_btc.getJSONObject("NGN");

                JSONObject zar = btc.getJSONObject("ZAR");
                JSONObject raw_zar = raw_btc.getJSONObject("ZAR");

                JSONObject idr = btc.getJSONObject("IDR");
                JSONObject raw_idr = raw_btc.getJSONObject("IDR");
                //adds JPY to the list
                cryptoWordsList.add(new CryptoWords(jpy.getString("PRICE"), jpy.getString("TOSYMBOL"),
                        jpy.getString("FROMSYMBOL"), jpy.getString("VOLUME24HOUR"), jpy.getString("CHANGEPCT24HOUR"),
                        jpy.getString("LASTVOLUME"), jpy.getString("LASTMARKET"), jpy.getString("LASTUPDATE"),
                        raw_jpy.getString("FROMSYMBOL"), raw_jpy.getString("TOSYMBOL"), jpy.getString("MKTCAP")));
                //adds usd to the list
                cryptoWordsList.add(new CryptoWords(usd.getString("PRICE"), usd.getString("TOSYMBOL"),
                        usd.getString("FROMSYMBOL"), usd.getString("VOLUME24HOUR"), usd.getString("CHANGEPCT24HOUR"),
                        usd.getString("LASTVOLUME"), usd.getString("LASTMARKET"), usd.getString("LASTUPDATE"),
                        raw_usd.getString("FROMSYMBOL"), raw_usd.getString("TOSYMBOL"), usd.getString("MKTCAP")));
                //adds krw to the list
                cryptoWordsList.add(new CryptoWords(krw.getString("PRICE"), krw.getString("TOSYMBOL"),
                        krw.getString("FROMSYMBOL"), krw.getString("VOLUME24HOUR"), krw.getString("CHANGEPCT24HOUR"),
                        krw.getString("LASTVOLUME"), krw.getString("LASTMARKET"), krw.getString("LASTUPDATE"),
                        raw_krw.getString("FROMSYMBOL"), raw_krw.getString("TOSYMBOL"), krw.getString("MKTCAP")));
                //adds cny to the list
                cryptoWordsList.add(new CryptoWords(cny.getString("PRICE"), cny.getString("TOSYMBOL"),
                        cny.getString("FROMSYMBOL"), cny.getString("VOLUME24HOUR"), cny.getString("CHANGEPCT24HOUR"),
                        cny.getString("LASTVOLUME"), cny.getString("LASTMARKET"), cny.getString("LASTUPDATE"),
                        raw_cny.getString("FROMSYMBOL"), raw_cny.getString("TOSYMBOL"), cny.getString("MKTCAP")));
                //adds eur to the list
                cryptoWordsList.add(new CryptoWords(eur.getString("PRICE"), eur.getString("TOSYMBOL"),
                        eur.getString("FROMSYMBOL"), eur.getString("VOLUME24HOUR"), eur.getString("CHANGEPCT24HOUR"),
                        eur.getString("LASTVOLUME"), eur.getString("LASTMARKET"), eur.getString("LASTUPDATE"),
                        raw_eur.getString("FROMSYMBOL"), raw_eur.getString("TOSYMBOL"), eur.getString("MKTCAP")));
                //adds usdt to the list
                cryptoWordsList.add(new CryptoWords(usdt.getString("PRICE"), usdt.getString("TOSYMBOL"),
                        usdt.getString("FROMSYMBOL"), usdt.getString("VOLUME24HOUR"), usdt.getString("CHANGEPCT24HOUR"),
                        usdt.getString("LASTVOLUME"), usdt.getString("LASTMARKET"), usdt.getString("LASTUPDATE"),
                        raw_usdt.getString("FROMSYMBOL"), raw_usdt.getString("TOSYMBOL"), usdt.getString("MKTCAP")));
                //adds gbp to the list
                cryptoWordsList.add(new CryptoWords(gbp.getString("PRICE"), gbp.getString("TOSYMBOL"),
                        gbp.getString("FROMSYMBOL"), gbp.getString("VOLUME24HOUR"), gbp.getString("CHANGEPCT24HOUR"),
                        gbp.getString("LASTVOLUME"), gbp.getString("LASTMARKET"), gbp.getString("LASTUPDATE"),
                        raw_gbp.getString("FROMSYMBOL"), raw_gbp.getString("TOSYMBOL"), gbp.getString("MKTCAP")));
                //adds rub to the list
                cryptoWordsList.add(new CryptoWords(rub.getString("PRICE"), rub.getString("TOSYMBOL"),
                        rub.getString("FROMSYMBOL"), rub.getString("VOLUME24HOUR"), rub.getString("CHANGEPCT24HOUR"),
                        rub.getString("LASTVOLUME"), rub.getString("LASTMARKET"), rub.getString("LASTUPDATE"),
                        raw_rub.getString("FROMSYMBOL"), raw_rub.getString("TOSYMBOL"), rub.getString("MKTCAP")));
                //adds pln to the list
                cryptoWordsList.add(new CryptoWords(pln.getString("PRICE"), pln.getString("TOSYMBOL"),
                        pln.getString("FROMSYMBOL"), pln.getString("VOLUME24HOUR"), pln.getString("CHANGEPCT24HOUR"),
                        pln.getString("LASTVOLUME"), pln.getString("LASTMARKET"), pln.getString("LASTUPDATE"),
                        raw_pln.getString("FROMSYMBOL"), raw_pln.getString("TOSYMBOL"), pln.getString("MKTCAP")));
                //adds cad to the list
                cryptoWordsList.add(new CryptoWords(cad.getString("PRICE"), cad.getString("TOSYMBOL"),
                        cad.getString("FROMSYMBOL"), cad.getString("VOLUME24HOUR"), cad.getString("CHANGEPCT24HOUR"),
                        cad.getString("LASTVOLUME"), cad.getString("LASTMARKET"), cad.getString("LASTUPDATE"),
                        raw_cad.getString("FROMSYMBOL"), raw_cad.getString("TOSYMBOL"), cad.getString("MKTCAP")));
                //adds vnd to the list
                cryptoWordsList.add(new CryptoWords(vnd.getString("PRICE"), vnd.getString("TOSYMBOL"),
                        vnd.getString("FROMSYMBOL"), vnd.getString("VOLUME24HOUR"), vnd.getString("CHANGEPCT24HOUR"),
                        vnd.getString("LASTVOLUME"), vnd.getString("LASTMARKET"), vnd.getString("LASTUPDATE"),
                        raw_vnd.getString("FROMSYMBOL"), raw_vnd.getString("TOSYMBOL"), vnd.getString("MKTCAP")));
                //adds zar to the list
                cryptoWordsList.add(new CryptoWords(zar.getString("PRICE"), zar.getString("TOSYMBOL"),
                        zar.getString("FROMSYMBOL"), zar.getString("VOLUME24HOUR"), zar.getString("CHANGEPCT24HOUR"),
                        zar.getString("LASTVOLUME"), zar.getString("LASTMARKET"), zar.getString("LASTUPDATE"),
                        raw_zar.getString("FROMSYMBOL"), raw_zar.getString("TOSYMBOL"), zar.getString("MKTCAP")));
                //adds mxn to the list
                cryptoWordsList.add(new CryptoWords(mxn.getString("PRICE"), mxn.getString("TOSYMBOL"),
                        mxn.getString("FROMSYMBOL"), mxn.getString("VOLUME24HOUR"), mxn.getString("CHANGEPCT24HOUR"),
                        mxn.getString("LASTVOLUME"), mxn.getString("LASTMARKET"), mxn.getString("LASTUPDATE"),
                        raw_mxn.getString("FROMSYMBOL"), raw_mxn.getString("TOSYMBOL"), mxn.getString("MKTCAP")));
                //adds hkd to the list
                cryptoWordsList.add(new CryptoWords(hkd.getString("PRICE"), hkd.getString("TOSYMBOL"),
                        hkd.getString("FROMSYMBOL"), hkd.getString("VOLUME24HOUR"), hkd.getString("CHANGEPCT24HOUR"),
                        hkd.getString("LASTVOLUME"), hkd.getString("LASTMARKET"), hkd.getString("LASTUPDATE"),
                        raw_hkd.getString("FROMSYMBOL"), raw_hkd.getString("TOSYMBOL"), hkd.getString("MKTCAP")));
                //adds aud to the list
                cryptoWordsList.add(new CryptoWords(aud.getString("PRICE"), aud.getString("TOSYMBOL"),
                        aud.getString("FROMSYMBOL"), aud.getString("VOLUME24HOUR"), aud.getString("CHANGEPCT24HOUR"),
                        aud.getString("LASTVOLUME"), aud.getString("LASTMARKET"), aud.getString("LASTUPDATE"),
                        raw_aud.getString("FROMSYMBOL"), raw_aud.getString("TOSYMBOL"), aud.getString("MKTCAP")));
                //adds brl to the list
                cryptoWordsList.add(new CryptoWords(brl.getString("PRICE"), brl.getString("TOSYMBOL"),
                        brl.getString("FROMSYMBOL"), brl.getString("VOLUME24HOUR"), brl.getString("CHANGEPCT24HOUR"),
                        brl.getString("LASTVOLUME"), brl.getString("LASTMARKET"), brl.getString("LASTUPDATE"),
                        raw_brl.getString("FROMSYMBOL"), raw_brl.getString("TOSYMBOL"), brl.getString("MKTCAP")));
                //adds myr to the list
                cryptoWordsList.add(new CryptoWords(myr.getString("PRICE"), myr.getString("TOSYMBOL"),
                        myr.getString("FROMSYMBOL"), myr.getString("VOLUME24HOUR"), myr.getString("CHANGEPCT24HOUR"),
                        myr.getString("LASTVOLUME"), myr.getString("LASTMARKET"), myr.getString("LASTUPDATE"),
                        raw_myr.getString("FROMSYMBOL"), raw_myr.getString("TOSYMBOL"), myr.getString("MKTCAP")));
                //adds sgd to the list
                cryptoWordsList.add(new CryptoWords(sgd.getString("PRICE"), sgd.getString("TOSYMBOL"),
                        sgd.getString("FROMSYMBOL"), sgd.getString("VOLUME24HOUR"), sgd.getString("CHANGEPCT24HOUR"),
                        sgd.getString("LASTVOLUME"), sgd.getString("LASTMARKET"), sgd.getString("LASTUPDATE"),
                        raw_sgd.getString("FROMSYMBOL"), raw_sgd.getString("TOSYMBOL"), sgd.getString("MKTCAP")));
                //adds idr to the list
                cryptoWordsList.add(new CryptoWords(idr.getString("PRICE"), idr.getString("TOSYMBOL"),
                        idr.getString("FROMSYMBOL"), idr.getString("VOLUME24HOUR"), idr.getString("CHANGEPCT24HOUR"),
                        idr.getString("LASTVOLUME"), idr.getString("LASTMARKET"), idr.getString("LASTUPDATE"),
                        raw_idr.getString("FROMSYMBOL"), raw_idr.getString("TOSYMBOL"), idr.getString("MKTCAP")));
                //adds ngn to the list
                cryptoWordsList.add(new CryptoWords(ngn.getString("PRICE"), ngn.getString("TOSYMBOL"),
                        ngn.getString("FROMSYMBOL"), ngn.getString("VOLUME24HOUR"), ngn.getString("CHANGEPCT24HOUR"),
                        ngn.getString("LASTVOLUME"), ngn.getString("LASTMARKET"), ngn.getString("LASTUPDATE"),
                        raw_ngn.getString("FROMSYMBOL"), raw_ngn.getString("TOSYMBOL"), ngn.getString("MKTCAP")));

            }
            if (JSONResponse.contains("\"FROMSYMBOL\":\"ETH\"")) {
                JSONObject eth = object.getJSONObject("ETH");
                JSONObject raw_eth = raw_object.getJSONObject("ETH");

                JSONObject eur = eth.getJSONObject("EUR");
                JSONObject raw_eur = raw_eth.getJSONObject("EUR");

                JSONObject usd = eth.getJSONObject("USD");
                JSONObject raw_usd = raw_eth.getJSONObject("USD");


                JSONObject krw = eth.getJSONObject("KRW");
                JSONObject raw_krw = raw_eth.getJSONObject("KRW");

                JSONObject cny = eth.getJSONObject("CNY");
                JSONObject raw_cny = raw_eth.getJSONObject("CNY");

                JSONObject usdt = eth.getJSONObject("USDT");
                JSONObject raw_usdt = raw_eth.getJSONObject("USDT");

                JSONObject jpy = eth.getJSONObject("JPY");
                JSONObject raw_jpy = raw_eth.getJSONObject("JPY");

                JSONObject aud = eth.getJSONObject("AUD");
                JSONObject raw_aud = raw_eth.getJSONObject("AUD");

                JSONObject cad = eth.getJSONObject("CAD");
                JSONObject raw_cad = raw_eth.getJSONObject("CAD");

                JSONObject mxn = eth.getJSONObject("MXN");
                JSONObject raw_mxn = raw_eth.getJSONObject("MXN");

                JSONObject vnd = eth.getJSONObject("VND");
                JSONObject raw_vnd = raw_eth.getJSONObject("VND");

                JSONObject hkd = eth.getJSONObject("HKD");
                JSONObject raw_hkd = raw_eth.getJSONObject("HKD");

                JSONObject sgd = eth.getJSONObject("SGD");
                JSONObject raw_sgd = raw_eth.getJSONObject("SGD");

                JSONObject brl = eth.getJSONObject("BRL");
                JSONObject raw_brl = raw_eth.getJSONObject("BRL");

                JSONObject pln = eth.getJSONObject("PLN");
                JSONObject raw_pln = raw_eth.getJSONObject("PLN");

                JSONObject myr = eth.getJSONObject("MYR");
                JSONObject raw_myr = raw_eth.getJSONObject("MYR");

                JSONObject rub = eth.getJSONObject("RUB");
                JSONObject raw_rub = raw_eth.getJSONObject("RUB");

                JSONObject gbp = eth.getJSONObject("GBP");
                JSONObject raw_gbp = raw_eth.getJSONObject("GBP");

                JSONObject ngn = eth.getJSONObject("NGN");
                JSONObject raw_ngn = raw_eth.getJSONObject("NGN");

                JSONObject zar = eth.getJSONObject("ZAR");
                JSONObject raw_zar = raw_eth.getJSONObject("ZAR");

                JSONObject idr = eth.getJSONObject("IDR");
                JSONObject raw_idr = raw_eth.getJSONObject("IDR");

                //adds JPY to the list
                cryptoWordsList.add(new CryptoWords(jpy.getString("PRICE"), jpy.getString("TOSYMBOL"),
                        jpy.getString("FROMSYMBOL"), jpy.getString("VOLUME24HOUR"), jpy.getString("CHANGEPCT24HOUR"),
                        jpy.getString("LASTVOLUME"), jpy.getString("LASTMARKET"), jpy.getString("LASTUPDATE"),
                        raw_jpy.getString("FROMSYMBOL"), raw_jpy.getString("TOSYMBOL"), jpy.getString("MKTCAP")));
                //adds usd to the list
                cryptoWordsList.add(new CryptoWords(usd.getString("PRICE"), usd.getString("TOSYMBOL"),
                        usd.getString("FROMSYMBOL"), usd.getString("VOLUME24HOUR"), usd.getString("CHANGEPCT24HOUR"),
                        usd.getString("LASTVOLUME"), usd.getString("LASTMARKET"), usd.getString("LASTUPDATE"),
                        raw_usd.getString("FROMSYMBOL"), raw_usd.getString("TOSYMBOL"), usd.getString("MKTCAP")));
                //adds krw to the list
                cryptoWordsList.add(new CryptoWords(krw.getString("PRICE"), krw.getString("TOSYMBOL"),
                        krw.getString("FROMSYMBOL"), krw.getString("VOLUME24HOUR"), krw.getString("CHANGEPCT24HOUR"),
                        krw.getString("LASTVOLUME"), krw.getString("LASTMARKET"), krw.getString("LASTUPDATE"),
                        raw_krw.getString("FROMSYMBOL"), raw_krw.getString("TOSYMBOL"), krw.getString("MKTCAP")));
                //adds cny to the list
                cryptoWordsList.add(new CryptoWords(cny.getString("PRICE"), cny.getString("TOSYMBOL"),
                        cny.getString("FROMSYMBOL"), cny.getString("VOLUME24HOUR"), cny.getString("CHANGEPCT24HOUR"),
                        cny.getString("LASTVOLUME"), cny.getString("LASTMARKET"), cny.getString("LASTUPDATE"),
                        raw_cny.getString("FROMSYMBOL"), raw_cny.getString("TOSYMBOL"), cny.getString("MKTCAP")));
                //adds eur to the list
                cryptoWordsList.add(new CryptoWords(eur.getString("PRICE"), eur.getString("TOSYMBOL"),
                        eur.getString("FROMSYMBOL"), eur.getString("VOLUME24HOUR"), eur.getString("CHANGEPCT24HOUR"),
                        eur.getString("LASTVOLUME"), eur.getString("LASTMARKET"), eur.getString("LASTUPDATE"),
                        raw_eur.getString("FROMSYMBOL"), raw_eur.getString("TOSYMBOL"), eur.getString("MKTCAP")));
                //adds usdt to the list
                cryptoWordsList.add(new CryptoWords(usdt.getString("PRICE"), usdt.getString("TOSYMBOL"),
                        usdt.getString("FROMSYMBOL"), usdt.getString("VOLUME24HOUR"), usdt.getString("CHANGEPCT24HOUR"),
                        usdt.getString("LASTVOLUME"), usdt.getString("LASTMARKET"), usdt.getString("LASTUPDATE"),
                        raw_usdt.getString("FROMSYMBOL"), raw_usdt.getString("TOSYMBOL"), usdt.getString("MKTCAP")));
                //adds gbp to the list
                cryptoWordsList.add(new CryptoWords(gbp.getString("PRICE"), gbp.getString("TOSYMBOL"),
                        gbp.getString("FROMSYMBOL"), gbp.getString("VOLUME24HOUR"), gbp.getString("CHANGEPCT24HOUR"),
                        gbp.getString("LASTVOLUME"), gbp.getString("LASTMARKET"), gbp.getString("LASTUPDATE"),
                        raw_gbp.getString("FROMSYMBOL"), raw_gbp.getString("TOSYMBOL"), gbp.getString("MKTCAP")));
                //adds rub to the list
                cryptoWordsList.add(new CryptoWords(rub.getString("PRICE"), rub.getString("TOSYMBOL"),
                        rub.getString("FROMSYMBOL"), rub.getString("VOLUME24HOUR"), rub.getString("CHANGEPCT24HOUR"),
                        rub.getString("LASTVOLUME"), rub.getString("LASTMARKET"), rub.getString("LASTUPDATE"),
                        raw_rub.getString("FROMSYMBOL"), raw_rub.getString("TOSYMBOL"), rub.getString("MKTCAP")));
                //adds pln to the list
                cryptoWordsList.add(new CryptoWords(pln.getString("PRICE"), pln.getString("TOSYMBOL"),
                        pln.getString("FROMSYMBOL"), pln.getString("VOLUME24HOUR"), pln.getString("CHANGEPCT24HOUR"),
                        pln.getString("LASTVOLUME"), pln.getString("LASTMARKET"), pln.getString("LASTUPDATE"),
                        raw_pln.getString("FROMSYMBOL"), raw_pln.getString("TOSYMBOL"), pln.getString("MKTCAP")));
                //adds cad to the list
                cryptoWordsList.add(new CryptoWords(cad.getString("PRICE"), cad.getString("TOSYMBOL"),
                        cad.getString("FROMSYMBOL"), cad.getString("VOLUME24HOUR"), cad.getString("CHANGEPCT24HOUR"),
                        cad.getString("LASTVOLUME"), cad.getString("LASTMARKET"), cad.getString("LASTUPDATE"),
                        raw_cad.getString("FROMSYMBOL"), raw_cad.getString("TOSYMBOL"), cad.getString("MKTCAP")));
                //adds vnd to the list
                cryptoWordsList.add(new CryptoWords(vnd.getString("PRICE"), vnd.getString("TOSYMBOL"),
                        vnd.getString("FROMSYMBOL"), vnd.getString("VOLUME24HOUR"), vnd.getString("CHANGEPCT24HOUR"),
                        vnd.getString("LASTVOLUME"), vnd.getString("LASTMARKET"), vnd.getString("LASTUPDATE"),
                        raw_vnd.getString("FROMSYMBOL"), raw_vnd.getString("TOSYMBOL"), vnd.getString("MKTCAP")));
                //adds zar to the list
                cryptoWordsList.add(new CryptoWords(zar.getString("PRICE"), zar.getString("TOSYMBOL"),
                        zar.getString("FROMSYMBOL"), zar.getString("VOLUME24HOUR"), zar.getString("CHANGEPCT24HOUR"),
                        zar.getString("LASTVOLUME"), zar.getString("LASTMARKET"), zar.getString("LASTUPDATE"),
                        raw_zar.getString("FROMSYMBOL"), raw_zar.getString("TOSYMBOL"), zar.getString("MKTCAP")));
                //adds mxn to the list
                cryptoWordsList.add(new CryptoWords(mxn.getString("PRICE"), mxn.getString("TOSYMBOL"),
                        mxn.getString("FROMSYMBOL"), mxn.getString("VOLUME24HOUR"), mxn.getString("CHANGEPCT24HOUR"),
                        mxn.getString("LASTVOLUME"), mxn.getString("LASTMARKET"), mxn.getString("LASTUPDATE"),
                        raw_mxn.getString("FROMSYMBOL"), raw_mxn.getString("TOSYMBOL"), mxn.getString("MKTCAP")));
                //adds hkd to the list
                cryptoWordsList.add(new CryptoWords(hkd.getString("PRICE"), hkd.getString("TOSYMBOL"),
                        hkd.getString("FROMSYMBOL"), hkd.getString("VOLUME24HOUR"), hkd.getString("CHANGEPCT24HOUR"),
                        hkd.getString("LASTVOLUME"), hkd.getString("LASTMARKET"), hkd.getString("LASTUPDATE"),
                        raw_hkd.getString("FROMSYMBOL"), raw_hkd.getString("TOSYMBOL"), hkd.getString("MKTCAP")));
                //adds aud to the list
                cryptoWordsList.add(new CryptoWords(aud.getString("PRICE"), aud.getString("TOSYMBOL"),
                        aud.getString("FROMSYMBOL"), aud.getString("VOLUME24HOUR"), aud.getString("CHANGEPCT24HOUR"),
                        aud.getString("LASTVOLUME"), aud.getString("LASTMARKET"), aud.getString("LASTUPDATE"),
                        raw_aud.getString("FROMSYMBOL"), raw_aud.getString("TOSYMBOL"), aud.getString("MKTCAP")));
                //adds brl to the list
                cryptoWordsList.add(new CryptoWords(brl.getString("PRICE"), brl.getString("TOSYMBOL"),
                        brl.getString("FROMSYMBOL"), brl.getString("VOLUME24HOUR"), brl.getString("CHANGEPCT24HOUR"),
                        brl.getString("LASTVOLUME"), brl.getString("LASTMARKET"), brl.getString("LASTUPDATE"),
                        raw_brl.getString("FROMSYMBOL"), raw_brl.getString("TOSYMBOL"), brl.getString("MKTCAP")));
                //adds myr to the list
                cryptoWordsList.add(new CryptoWords(myr.getString("PRICE"), myr.getString("TOSYMBOL"),
                        myr.getString("FROMSYMBOL"), myr.getString("VOLUME24HOUR"), myr.getString("CHANGEPCT24HOUR"),
                        myr.getString("LASTVOLUME"), myr.getString("LASTMARKET"), myr.getString("LASTUPDATE"),
                        raw_myr.getString("FROMSYMBOL"), raw_myr.getString("TOSYMBOL"), myr.getString("MKTCAP")));
                //adds sgd to the list
                cryptoWordsList.add(new CryptoWords(sgd.getString("PRICE"), sgd.getString("TOSYMBOL"),
                        sgd.getString("FROMSYMBOL"), sgd.getString("VOLUME24HOUR"), sgd.getString("CHANGEPCT24HOUR"),
                        sgd.getString("LASTVOLUME"), sgd.getString("LASTMARKET"), sgd.getString("LASTUPDATE"),
                        raw_sgd.getString("FROMSYMBOL"), raw_sgd.getString("TOSYMBOL"), sgd.getString("MKTCAP")));
                //adds idr to the list
                cryptoWordsList.add(new CryptoWords(idr.getString("PRICE"), idr.getString("TOSYMBOL"),
                        idr.getString("FROMSYMBOL"), idr.getString("VOLUME24HOUR"), idr.getString("CHANGEPCT24HOUR"),
                        idr.getString("LASTVOLUME"), idr.getString("LASTMARKET"), idr.getString("LASTUPDATE"),
                        raw_idr.getString("FROMSYMBOL"), raw_idr.getString("TOSYMBOL"), idr.getString("MKTCAP")));
                //adds ngn to the list
                cryptoWordsList.add(new CryptoWords(ngn.getString("PRICE"), ngn.getString("TOSYMBOL"),
                        ngn.getString("FROMSYMBOL"), ngn.getString("VOLUME24HOUR"), ngn.getString("CHANGEPCT24HOUR"),
                        ngn.getString("LASTVOLUME"), ngn.getString("LASTMARKET"), ngn.getString("LASTUPDATE"),
                        raw_ngn.getString("FROMSYMBOL"), raw_ngn.getString("TOSYMBOL"), ngn.getString("MKTCAP")));

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "error due to JSON exception", e);
        }

        return cryptoWordsList;
    }

}
