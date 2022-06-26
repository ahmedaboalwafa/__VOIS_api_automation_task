package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static api.Jsonplaceholder.*;

public class UserTests {
    @BeforeClass
    public void setup(){
        getRandomUserID();
    }

    @Test
    public void printRandomUserEmail(){
        Response response= getRandomUserData();
        Assert.assertEquals(response.statusCode(),200);
        String email=response.then().extract().path("email");
        System.out.println("User Email: "+email);
    }

    @Test
    public void validateUserPostsIDs(){
        Response response=getUserPosts();
        Assert.assertEquals(response.statusCode(),200);
        ArrayList<Integer> postIDs=response.then().extract().path("id");
        for (Integer id:postIDs) {
            if(id>100 || id<1)
                Assert.assertTrue(false,"Invalid Post ID exist");
        }
    }

    @Test
    public void testCreatePost(){
        String title="Test";
        String body="Body";
        Response response=postWithRandomUser(title,body);
        Assert.assertEquals(response.statusCode(),201);
    }
}
