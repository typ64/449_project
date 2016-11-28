package com.example.typhene.a449_project;

/**
 * Created by typhene on 11/25/2016.
 */
import junit.framework.TestCase;


public class tdd extends TestCase {

    InputChecker testObject;

    public void setUp() {
      testObject = new InputChecker();
    }
    public void testInput() throws Exception {

        //model.increment();
        //assertEquals(1, model.getValue());
        assertNotNull(testObject);
    }

    public void testSampleInput_YesInput() throws Exception{
        assertTrue(testObject.isValidInput("turkey"));
    }
    public void testSampleInput_NotValidInput() throws Exception{
        assertTrue(testObject.isValidInput("123"));
    }
    public void testSampleInput_NotValidInput2() throws Exception{
        assertTrue(testObject.isValidInput("l"));
    }
    public void testSampleInput_NotValidInput3() throws Exception{
        assertTrue(testObject.isValidInput("23oz"));
    }
}
/*import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ItemInputTest {

    @Test
    public void itemInput_CorrectInputExample_ReturnsTrue() {
        assertThat(ItemInput.isInputValid("10"), is(true));
    }

}*/