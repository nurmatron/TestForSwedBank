package apiCalls;


import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import utils.PostObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


/**
 * A class for all the api calls im doing to  minimize size of main.
 */
public class ApiCalls {
    static HttpClient client = HttpClient.newHttpClient();


    public static String getAuthMethodAsString() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().GET().headers("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:95.0) Gecko/20100101 Firefox/95.0", "Authorization", "QjdkWkhRY1k3OFZSVno5bDoxNjM5NjYzNTk3MTkz", "X-Client", "fdp-internet-bank/185.0.0", "Content-Type", "application/json").uri(URI.create("https://online.swedbank.se/TDE_DAP_Portal_REST_WEB/api/v5/identification/")).build();
        HttpResponse<String> body = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(body.body());
        return body.body();
    }

    /**
     * I used apaches httpclient here because i could not find how to get the cookies with the "regular java.net client.
     */
    public static CookieStore getCookies() {
        org.apache.http.client.HttpClient client;
        CookieStore cookieStore = new BasicCookieStore();
        HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(cookieStore);
        client = builder.build();
        HttpGet httpGet = new HttpGet("https://online.swedbank.se/TDE_DAP_Portal_REST_WEB/api/v5/");
        org.apache.http.HttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(httpGet);
        } catch (Exception e) {
            System.out.println(e);
        }
        return cookieStore;
    }


    public static String postBankIdLogin(String persNr, List<PostObject> postObjects, CookieStore cookies) throws IOException, InterruptedException {

        Cookie JSESSIONID = cookies.getCookies().get(0);
        Cookie SWBTC = cookies.getCookies().get(1);
        Cookie TS014396a4 = cookies.getCookies().get(2);
        Cookie TS01dcfcda = cookies.getCookies().get(3);
        HttpResponse<String> body = null;
        // no null check, also could be changed to a check for name or something where u map over.

        try {
            System.out.println(persNr);
            String method = postObjects.get(0).getMethod();
            HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString("{\"bankIdOnSameDevice\":\"false\",\"generateEasyLoginId\":\"false\",\"useEasyLogin\":\"false\",\"userId\":\"" + persNr + "\"}"))
                    // {"bankIdOnSameDevice":"false","generateEasyLoginId":"false","useEasyLogin":"false","userId":"199204302534"}
                    .headers("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:95.0) Gecko/20100101 Firefox/95.0", "Authorization", "QjdkWkhRY1k3OFZSVno5bDoxNjM5NzgwNDczNTg1", "X-Client", "fdp-internet-bank/185.0.0", "Content-Type", "application/json", "Accept", "application/json").uri(URI.create("https://online.swedbank.se/TDE_DAP_Portal_REST_WEB/api" + postObjects.get(0).getUri())).header("Cookie", JSESSIONID.getValue()).header("Cookie", SWBTC.getValue()).header("Cookie", TS01dcfcda.getValue()).build();

            body = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(body.body());
            System.out.println(body.statusCode());
        } catch (Exception e) {
            System.out.println(e);


        }
        return body.body();
    }
    /**
     * I dont know what the bankid endpoint wants more, however I do get this method in response so I figured i'd try it..
     *
     */

    public static String checkStatus(CookieStore cookies) {
        String body = null;

        Cookie JSESSIONID = cookies.getCookies().get(0);
        Cookie SWBTC = cookies.getCookies().get(1);
        Cookie TS014396a4 = cookies.getCookies().get(2);
        Cookie TS01dcfcda = cookies.getCookies().get(3);
        try {
            HttpRequest request = HttpRequest.newBuilder().GET().headers(
                    "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:95.0) Gecko/20100101 Firefox/95.0",
                    "Authorization", "QjdkWkhRY1k3OFZSVno5bDoxNjM5NzgwNDczNTg1", "X-Client", "fdp-internet-bank/185.0.0",
                    "Content-Type", "application/json")
                    .uri(URI.create("https://online.swedbank.se/TDE_DAP_Portal_REST_WEB/api/v5/identification/bankid/mobile/verify"))
                    .header("Cookie", JSESSIONID.getValue())
                    .header("Cookie", SWBTC.getValue())
                    .header("Cookie", TS01dcfcda.getValue())
                    .build();

            HttpResponse<String> body1 = client.send(request, HttpResponse.BodyHandlers.ofString());
           body = body1.body();

        } catch (Exception e) {
            System.out.println(e);
        }
           return body;
    }


}