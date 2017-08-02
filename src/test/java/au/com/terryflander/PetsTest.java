package au.com.terryflander;

import au.com.terryflander.Pets.ReportPet;
import java.util.List;
import org.json.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PetsTest {

  private final String text = "[{\"name\":\"Bob\",\"gender\":\"Male\",\"age\":23,\"pets\":[{\"name\":\"Garfield\",\"type\":\"Cat\"},{\"name\":\"Fido\",\"type\":\"Dog\"}]},{\"name\":\"Jennifer\",\"gender\":\"Female\",\"age\":18,\"pets\":[{\"name\":\"Garfield\",\"type\":\"Cat\"}]},{\"name\":\"Steve\",\"gender\":\"Male\",\"age\":45,\"pets\":null},{\"name\":\"Fred\",\"gender\":\"Male\",\"age\":40,\"pets\":[{\"name\":\"Tom\",\"type\":\"Cat\"},{\"name\":\"Max\",\"type\":\"Cat\"},{\"name\":\"Sam\",\"type\":\"Dog\"},{\"name\":\"Jim\",\"type\":\"Cat\"}]},{\"name\":\"Samantha\",\"gender\":\"Female\",\"age\":40,\"pets\":[{\"name\":\"Tabby\",\"type\":\"Cat\"}]},{\"name\":\"Alice\",\"gender\":\"Female\",\"age\":64,\"pets\":[{\"name\":\"Simba\",\"type\":\"Cat\"},{\"name\":\"Nemo\",\"type\":\"Fish\"}]}]";
  private final String sourceUrl = "http://agl-developer-test.azurewebsites.net/people.json";

  PetDataSource pds = new TestDataSource();

  @Before
  public void setUp() throws Exception {
    this.pds = new TestDataSource();
  }

  @Test
  public void checkPetsByTypeFormattedHTML() throws Exception {
    String test = Pets.getPetsByTypeFormatHtml(this.pds, "Cat");
    Assert.assertEquals("Male<ul><li>Garfield</li><li>Jim</li><li>Max</li><li>Tom</li></ul>Female<ul><li>Garfield</li><li>Simba</li><li>Tabby</li>",
      test);
  }

  @Test
  public void checkHtmlPetsFromList() throws Exception {
    List<Owner> owners =  Pets.getOwnerList(this.pds.getPetData());
    Assert.assertEquals(6,owners.size());
  }

  @Test
  public void checkReportPetsFromOwner() throws Exception {
    List<Owner> owners =  Pets.getOwnerList(this.pds.getPetData());
    List<ReportPet> pets = Pets.getPetsFromOwners(owners);
    Assert.assertEquals(10, pets.size());
  }

  @Test
  public void checkSelectReportPetsByType() throws Exception {
    List<Owner> owners =  Pets.getOwnerList(this.pds.getPetData());
    List<ReportPet> pets = Pets.getPetsFromOwners(owners);
    pets = Pets.selectPetsByPetType(pets, "Cat");
    Assert.assertEquals(7, pets.size());
  }

/*
  @Test
  public void checkPetListValid() throws Exception {
    String petTest = "{\"pets\":[{\"name\":\"Garfield\",\"type\":\"Cat\"},{\"name\":\"Fido\",\"type\":\"Dog\"}]}";
    JSONObject obj = new JSONObject(petTest);
    JSONArray arr = obj.getJSONArray("pets");
    Assert.assertEquals(2,  arr.length());
  }


*/

}