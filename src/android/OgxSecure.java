import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.ICordovaCookieManager;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.os.Build;
import java.net.HttpCookie;

public class OgxSecure extends CordovaPlugin { 

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);   
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Context context = cordova.getActivity().getApplicationContext(); 
            CookieSyncManager.createInstance(context);
        }  
        String key = preferences.getString("OgxKey", null);
        String hostname = preferences.getString("hostname", null);
        ICordovaCookieManager cookieManager = webView.getCookieManager();
        cookieManager.setCookie("https://"+hostname, "ogx_key="+key);
    }
}
