import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

    @Test
    public void testValidAnswer(){
        Validator validator = new Validator();

        assertTrue(validator.isValid("animals","cat", "C"));
        assertTrue(validator.isValid("colors", "red","R"));
        assertTrue(validator.isValid("foods", "pizza", "P"));
        assertTrue(validator.isValid("countries", "france", "F"));


    }
    @Test
    public void WrongLetter(){
        Validator validator = new Validator();
        assertFalse(validator.isValid("animals","cat", "K"));
        assertFalse(validator.isValid("colors", "red","D"));
        assertFalse(validator.isValid("foods", "pizza", "F"));
        assertFalse(validator.isValid("countries", "france", "G"));

    }

    @Test
    public void EmptyResponse(){
        Validator validator = new Validator();

        assertFalse(validator.isValid("animals","", "C"));
        assertFalse(validator.isValid("colors", "","R"));
        assertFalse(validator.isValid("foods", "  ", "P"));
        assertFalse(validator.isValid("countries", " ", "F"));
    }

    @Test
    public void spacesInResponse(){
        Validator validator = new Validator();
        assertTrue(validator.isValid("countries", "united states", "U"));
        assertTrue(validator.isValid("celebrities", "taylor swift", "T"));


    }

}