/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package edu.umich.eecs.lab11.summon;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import org.apache.cordova.*;

public class MainActivity extends CordovaActivity
{
    private String deviceId = "";
    private String deviceName = "";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.init();
        WebView wv = ((WebView) appView.getEngine().getView());
        wv.addJavascriptInterface(new JavaScriptInterface(this), "gateway");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setSupportMultipleWindows(false);
        wv.getSettings().setNeedInitialFocus(false);
        wv.getSettings().setSupportZoom(false);
        wv.getSettings().setAllowFileAccess(true);
        wv.getSettings().setAppCacheEnabled(true);
        wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        String cachePath = wv.getContext().getCacheDir().getAbsolutePath();
        wv.getSettings().setAppCachePath(cachePath);
        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }

    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public String getDeviceId(){ return deviceId; }

        @JavascriptInterface
        public String getDeviceName() { return deviceName; }

        @JavascriptInterface
        public void setDeviceId(String s){ deviceId = s; }

        @JavascriptInterface
        public void setDeviceName(String s) { deviceName = s; }

        @JavascriptInterface
        public void go(String s) { loadUrl(s); }
    }

}