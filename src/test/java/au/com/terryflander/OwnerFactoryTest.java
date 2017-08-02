package au.com.terryflander;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OwnerFactoryTest extends PetDataSource {

  private Owner owner = null;

  public JSONArray getPetData() {
    return null;
  }

  @Before
  public void setUp() throws Exception {
    String text = "[{\"name\":\"Bob\",\"gender\":\"Male\",\"age\":23,\"pets\":[{\"name\":\"Garfield\",\"type\":\"Cat\"},{\"name\":\"Fido\",\"type\":\"Dog\"}]}]";
    JSONArray toTest = convertTextToJsonArray(text);
    owner = OwnerFactory.buildOwnerFromJSON(toTest.getJSONObject(0));
  }

  @Test
  public void buildOwnerFromJSON() throws Exception {
    Assert.assertEquals("Bob", owner.getOwnerName());
  }

  @Test
  public void getPets() throws Exception {
    Assert.assertEquals(2, owner.getPets().size());
  }

}