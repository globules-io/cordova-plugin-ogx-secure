import Foundation

@objc(OgxSecure) class OgxSecure : CDVPlugin{   

    override init() {
        let configuration = CDVWebViewEngineConfigurationDelegate();
        //let cookieStore = self.webView.configuration.websiteDataStore.httpCookieStore;
        let cookieStore = configuration.websiteDataStore.httpCookieStore;
        let key = self.commandDelegate.settings["OgxKey".lowercased()] as? NSString;
        let hostname = self.commandDelegate.settings["hostname".lowercased()] as? NSString;
        let cookie = HTTPCookie(properties: [
            .domain: hostname,
            .path: "/",
            .name: "ogx_key",
            .value: key,
            .secure: "TRUE",
            .expires: NSDate(timeIntervalSinceNow: 31556926)
        ]);
        cookieStore.setCookie(cookie, completionHandler: nil?);
    }
}