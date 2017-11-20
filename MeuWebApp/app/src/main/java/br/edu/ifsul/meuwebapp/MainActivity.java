package br.edu.ifsul.meuwebapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wv = (WebView) findViewById(R.id.wbvContainer);

        wv.setWebChromeClient(new WebChromeClient() {});
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        wv.addJavascriptInterface(new JavaScriptInterface(this), "Android");


        LocationGPS locationGPS = new LocationGPS(context);

        double latidude = 0;
        double longitude = 0;

        if (locationGPS.checkLocation()) {
            latidude = locationGPS.getLatitude();
            longitude = locationGPS.getLongitude();
        }


        String customHtml = "<html><body><center><h1>Curso Android</h1>" +
                "<h2>IFSUL</h2><h3>Exemplo WebView</h3>" +
                "<p>HTML Simples" + String.valueOf(latidude) + "</p>" +
                "</center><input type=\"button\" value=\"Get from android\" onClick=\"getFromAndroid()\" />\n" +
                "<script type=\"text/javascript\">\n" +
                "    var myVar = null;\n" +
                "    function getFromAndroid() {\n" +
                "        myVar = Android.bitMapToBase64('oi');\n" +
                "        alert(myVar);\n" +
                "    }\n" +
                "</script></body></html>";

        wv.loadData(customHtml, "text/html", "UTF-8");


     //   wv.loadUrl("file:///android_asset/www/index.html");

    }
}

class JavaScriptInterface {
    Context mContext;
    JavaScriptInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public String bitMapToBase64(String text)
    {
        Log.e("TAG--------","EEEEEEEEEEEEEEEEEEEE"+text.toString());
        return "sss";
    }
}




































 //