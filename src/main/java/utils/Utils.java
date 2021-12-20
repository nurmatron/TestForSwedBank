package utils;

import com.google.gson.*;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    /**
     * @param authString
     * @return a list of all the auth methods. However, it is not done. I should loop through all,
     * and assign them to a list and name them something that differents them. However i am just using the first one.
     */
    public static List<PostObject> convertResponseToListOfObjects(String authString) {
        List<PostObject> postObjects = new ArrayList<>();
        try {
            JsonObject jsonObject = new JsonParser().parse(authString).getAsJsonObject();
                    /*
                    TODO
                    this could be done better, by checking how many methods there are and putting them in a list or other collection
                    to avoid nullpointer exceptions.
                     */

            JsonElement firstMethod = jsonObject.getAsJsonArray("authenticationMethods").get(0);
                    /*
                        JsonElement secondMethod = jsonObject.getAsJsonArray("authenticationMethods").get(1);
                        JsonElement thirdMethod = jsonObject.getAsJsonArray("authenticationMethods").get(2);
                        JsonElement fourthMethod = jsonObject.getAsJsonArray("authenticationMethods").get(3);

                     */
            PostObject postObject = new PostObject();
            // i know this repetative im sorry :(
            postObject.setCode(firstMethod.getAsJsonObject().get("code").toString().replaceAll("[\"[-+^]*\", \" \"]", ""));
            postObject.setMethod(firstMethod.getAsJsonObject().get("location").getAsJsonObject().get("method").toString().replaceAll("[\"[-+^]*\", \" \"]", ""));
            postObject.setUri(firstMethod.getAsJsonObject().get("location").getAsJsonObject().get("uri").toString().replaceAll("[\"[-+^]*\", \" \"]", ""));
            postObject.setMessage(firstMethod.getAsJsonObject().get("message").toString().replaceAll("[\"[-+^]*\", \" \"]", ""));

            postObjects.add(postObject);
            System.out.println(postObject);
        } catch (JSONException e) {
            System.out.println("Error " + e.toString());
        }
        return postObjects;
    }
}
