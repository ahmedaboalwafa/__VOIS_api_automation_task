package api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class Jsonplaceholder {
    private static final String apiURL="https://jsonplaceholder.typicode.com/users/";
    private static int userID;
    public static Response getRandomUserData(){
        return given().get(apiURL+ userID);
    }

    public static void getRandomUserID(){
        userID=new Random().nextInt(1,11);
    }
    public static Response getUserPosts(){
        return given().get(apiURL+userID+"/posts");
    }

    public static Response postWithRandomUser(String title,String body){
        HashMap postData=new HashMap<>();
        postData.put("title",title);
        postData.put("body",body);
        return given().contentType("application/json").body(postData).post(apiURL+userID+"/posts");
    }

}

