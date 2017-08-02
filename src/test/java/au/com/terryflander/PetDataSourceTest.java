package au.com.terryflander;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

public class PetDataSourceTest extends PetDataSource{

  public JSONArray getPetData() {
    return null;
  }

  @Test
  public void checkSetSourceName() throws Exception {
    setSourceName("TestSource");
    Assert.assertEquals("TestSource", getSourceName());
  }

  @Test
  public void convertTextToJsonArray() throws Exception {
    String text = "[{\"name\":\"Bob\",\"gender\":\"Male\",\"age\":23,\"pets\":[{\"name\":\"Garfield\",\"type\":\"Cat\"},{\"name\":\"Fido\",\"type\":\"Dog\"}]}]";
    JSONArray toTest = convertTextToJsonArray(text);
    Assert.assertEquals(1, toTest.length());
  }

  @Test
  public void checkWrapJsonwithEmptyString() throws Exception {
    Assert.assertEquals("{data:[]}",wrapJson(""));
  }

}