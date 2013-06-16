package tren2gtfs

@Grab( 'net.sourceforge.nekohtml:nekohtml:1.9.18' )
@GrabExclude('xml-apis:xml-apis')

class WebConnectService {

    final String connectionUserAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1 (compatible; loteria_pr_bot; +http://www.webninjapr.com/)";
    final String connectionAccept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
    final String connectionAcceptLanguage = "en-US,en;q=0.8";
    final String connectionAcceptCharset = "ISO-8859-1,utf-8;q=0.7,*;q=0.3";
    final String connectionContentType = "application/x-www-form-urlencoded";

    def doPostConnection(URL url, String requestParameters, ArrayList<String> cookies){
        DataOutputStream wr;
        HttpURLConnection connectionPost;
        //System.setProperty("http.agent", "");
        def webpage=null
        def retCookies=null
        try {
            connectionPost = url.openConnection();
            connectionPost.setConnectTimeout(10000)
            connectionPost.setRequestMethod("POST");
            connectionPost.setDoOutput(true);
            connectionPost.setDoInput(true);
            connectionPost.setRequestProperty("User-Agent", connectionUserAgent);
            connectionPost.setRequestProperty("Accept", connectionAccept);
            //connectionPost.setRequestProperty(Accept-Encoding:gzip,deflate,sdch)
            connectionPost.setRequestProperty("Accept-Language",connectionAcceptLanguage);
            connectionPost.setRequestProperty("Accept-Charset", connectionAcceptCharset);
            //connectionPost.setRequestProperty(java.net.URLEncoder.encode("Cache-Control", "ISO-8859-1"),java.net.URLEncoder.encode("max-age=0", "ISO-8859-1"));
            connectionPost.setRequestProperty("Content-Type", connectionContentType);
            if(cookies && cookies.size()>0){
                connectionPost.setRequestProperty("Cookie", cookies.join("; "));
            }
            connectionPost.setRequestProperty("Content-Length",requestParameters.getBytes().length.toString());

            connectionPost.connect();
            //Send request
            wr = new DataOutputStream (
                    connectionPost.getOutputStream ());
            wr.writeBytes (requestParameters);
            wr.flush ();

            //From http://www.hccp.org/java-net-cookie-how-to.html
            String headerName=null;
            ArrayList<String> returnCookies = [];
            for (int i=1; (headerName = connectionPost.getHeaderFieldKey(i))!=null; i++) {
                if (headerName.equals("Set-Cookie")) {
                    String cookie = connectionPost.getHeaderField(i);

                    def cookieCutter = cookie.split(";\\s?")

                    returnCookies.addAll(cookieCutter);
                    //String cookieName = cookie.substring(0, cookie.indexOf("="));
                    //String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
                }
            }

            webpage = connectionPost.content.text
            retCookies = returnCookies
        } catch (e){
            log.error(e); //throw new Exception(e);
        } finally {
            if(wr){
                wr.close ();
            }
            if(connectionPost){
                connectionPost.disconnect();
            }
            return [webpage: webpage, cookies: retCookies];
        }
    }

    def doGetConnection(URL url, String requestParameters){
        DataOutputStream wr;
        HttpURLConnection connectionGet;
        //System.setProperty("http.agent", "");
        def webpage = null
        def cookies = null
        try {
            connectionGet = url.openConnection();
            connectionGet.setConnectTimeout(10000)
            connectionGet.setRequestMethod("GET");
            connectionGet.setDoOutput(false);
            connectionGet.setDoInput(true);
            connectionGet.setRequestProperty("User-Agent", connectionUserAgent);
            connectionGet.setRequestProperty("Accept", connectionAccept);
            //connectionGet.setRequestProperty(Accept-Encoding:gzip,deflate,sdch)
            connectionGet.setRequestProperty("Accept-Language",connectionAcceptLanguage);
            connectionGet.setRequestProperty("Accept-Charset", connectionAcceptCharset);
            //connectionGet.setRequestProperty(java.net.URLEncoder.encode("Cache-Control", "ISO-8859-1"),java.net.URLEncoder.encode("max-age=0", "ISO-8859-1"));
            connectionGet.setRequestProperty("Content-Type", connectionContentType);
            connectionGet.setRequestProperty("Content-Length",requestParameters.getBytes().length.toString());

            connectionGet.connect();

            //From http://www.hccp.org/java-net-cookie-how-to.html
            String headerName=null;
            ArrayList<String> returnCookies = [];
            for (int i=1; (headerName = connectionGet.getHeaderFieldKey(i))!=null; i++) {
                if (headerName.equals("Set-Cookie")) {
                    String cookie = connectionGet.getHeaderField(i);

                    def cookieCutter = cookie.split(";\\s?")

                    returnCookies.addAll(cookieCutter);
                    //String cookieName = cookie.substring(0, cookie.indexOf("="));
                    //String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
                }
            }

            webpage = connectionGet.content.text
            cookies = returnCookies
        } catch (e){
            log.error(e) //throw new Exception(e);
        } finally {
            if(wr){
                wr.close ();
            }
            if(connectionGet){
                connectionGet.disconnect();
            }
            return [webpage: webpage, cookies: cookies];
        }

    }

}

class TrenXmlParserFactory {
    static def parser=null
    static def xmlParser=null

    static def getXmlParser(){
        if(!parser){
            parser = new org.cyberneko.html.parsers.SAXParser()
            parser.setFeature('http://xml.org/sax/features/namespaces', false)
        }
        if(!xmlParser){
            xmlParser = new XmlParser(parser)
        }
        return xmlParser;
    }
}

