package au.com.terryflander;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OwnerTest {

  private Owner owner = null;

  @Before
  public void setUp() throws Exception {
    this.owner = new Owner("Fred", "Male", 22);
    this.owner.addPet("Dog", "Rover");
  }

  @Test
  public void getOwnerName() throws Exception {
    Assert.assertEquals("Fred", owner.getOwnerName());
  }

  @Test
  public void getGender() throws Exception {
    Assert.assertEquals("Male", owner.getGender());
  }

  @Test
  public void getAge() throws Exception {
    Assert.assertEquals(22, owner.getAge());
  }

  @Test
  public void addPet() throws Exception {
    this.owner.addPet("Cat", "Redeye");
    Assert.assertEquals(2, owner.getPets().size());
  }

  @Test
  public void getPets() throws Exception {
    Assert.assertEquals(1, owner.getPets().size());
  }

}