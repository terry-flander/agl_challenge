package agl_challenge_cats;

import java.util.List;
import org.json.*;

import org.junit.Assert;
import org.junit.Test;

public class PetsTest {

  private final String text = "[{\"name\":\"Bob\",\"gender\":\"Male\",\"age\":23,\"pets\":[{\"name\":\"Garfield\",\"type\":\"Cat\"},{\"name\":\"Fido\",\"type\":\"Dog\"}]},{\"name\":\"Jennifer\",\"gender\":\"Female\",\"age\":18,\"pets\":[{\"name\":\"Garfield\",\"type\":\"Cat\"}]},{\"name\":\"Steve\",\"gender\":\"Male\",\"age\":45,\"pets\":null},{\"name\":\"Fred\",\"gender\":\"Male\",\"age\":40,\"pets\":[{\"name\":\"Tom\",\"type\":\"Cat\"},{\"name\":\"Max\",\"type\":\"Cat\"},{\"name\":\"Sam\",\"type\":\"Dog\"},{\"name\":\"Jim\",\"type\":\"Cat\"}]},{\"name\":\"Samantha\",\"gender\":\"Female\",\"age\":40,\"pets\":[{\"name\":\"Tabby\",\"type\":\"Cat\"}]},{\"name\":\"Alice\",\"gender\":\"Female\",\"age\":64,\"pets\":[{\"name\":\"Simba\",\"type\":\"Cat\"},{\"name\":\"Nemo\",\"type\":\"Fish\"}]}]";
  private final String sourceUrl = "http://agl-developer-test.azurewebsites.net/people.json";

  @Test
  public void checkPetsByTypeFromUrl() throws Exception {
    Assert.assertEquals("Male<ul><li>Garfield</li><li>Jim</li><li>Max</li><li>Tom</li></ul>Female<ul><li>Garfield</li><li>Simba</li><li>Tabby</li>", Pets.getPetsByTypeFormatHtml(sourceUrl, "Cat"));
  }

  @Test
  public void checkHtmlPetsFromList() throws Exception {
    JSONArray arr =  Pets.convertTextToJsonArray(text);
    List<Pet> allPets = Pets.getPetList(arr);
    Assert.assertEquals("Male<ul><li>Garfield</li><li>Jim</li><li>Max</li><li>Tom</li></ul>Female<ul><li>Garfield</li><li>Simba</li><li>Tabby</li>",
        Pets.getHtmlPetsFromList(allPets, "Cat"));
  }

  @Test
  public void checkFirstPetOwner() throws Exception {
    JSONArray arr =  Pets.convertTextToJsonArray(text);
    JSONObject owner = arr.getJSONObject(0);
    Assert.assertEquals("Bob", owner.getString("name"));
  }

  @Test
  public void checkFirstPetOwnersPets() throws Exception {
    JSONArray arr =  Pets.convertTextToJsonArray(text);
    JSONObject owner = arr.getJSONObject(0);
    JSONArray pets = Pets.getPets(owner);
    Assert.assertEquals(2, pets.length());
  }

  @Test
  public void checkPetListValid() throws Exception {
    String petTest = "{\"pets\":[{\"name\":\"Garfield\",\"type\":\"Cat\"},{\"name\":\"Fido\",\"type\":\"Dog\"}]}";
    JSONObject obj = new JSONObject(petTest);
    JSONArray arr = obj.getJSONArray("pets");
    Assert.assertEquals(2,  arr.length());
  }

  @Test
  public void checkTextToJsonArray() throws Exception {
    JSONArray arr = Pets.convertTextToJsonArray(text);
    Assert.assertEquals(6, arr.length());
  }

  @Test
  public void checkUrlText() throws Exception {
    String sourceUrl = "http://agl-developer-test.azurewebsites.net/people.json";
    Assert.assertEquals(text, Pets.getUrlText(sourceUrl));
  }

  @Test
  public void checkWrapJsonBlank() throws Exception {
    Assert.assertEquals("{data:[]}", Pets.wrapJson(""));
  }

  @Test
  public void checkWrapJsonEmpty() throws Exception {
    Assert.assertEquals("{data:[]}", Pets.wrapJson("[]"));
  }


}