package au.com.terryflander;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HttpDataSourceTest {


  @Test
  public void getHttpJson() throws Exception {
    PetDataSource pds = new HttpDataSource();
    String sourceUrl = "http://agl-developer-test.azurewebsites.net/people.json";
    pds.setSourceName(sourceUrl);
    JSONArray data = pds.getPetData();
    Assert.assertEquals(6, data.length());
  }

}