package au.com.terryflander;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PetTest {

  private Pet pet = null;

  @Before
  public void setUp() throws Exception {
    this.pet = new Pet("Dog", "Rover");
  }

  @Test
  public void getType() throws Exception {
    Assert.assertEquals("Dog", pet.getType());
  }

  @Test
  public void getPetName() throws Exception {
    Assert.assertEquals("Rover", pet.getPetName());
  }

}