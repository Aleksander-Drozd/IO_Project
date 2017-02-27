package Util;

import org.junit.Test;

import static org.junit.Assert.*;
import static Util.Validator.*;

public class ValidatorTest {

    @Test
    public void postCode_validStrings() throws Exception{
        String text;

        text = "23-459";
        assertTrue(text.matches(POST_CODE_REGEX));

        text = "00-000";
        assertTrue(text.matches(POST_CODE_REGEX));

        text = "99-999";
        assertTrue(text.matches(POST_CODE_REGEX));

        text = "12-345";
        assertTrue(text.matches(POST_CODE_REGEX));
    }

    @Test
    public void postCode_corruptedStrings() throws Exception{
        String text;

        text = "a2-456";
        assertFalse(text.matches(POST_CODE_REGEX));

        text = "42 456";
        assertFalse(text.matches(POST_CODE_REGEX));

        text = "52-456 ";
        assertFalse(text.matches(POST_CODE_REGEX));

        text = " 62-456";
        assertFalse(text.matches(POST_CODE_REGEX));

        text = "12-4566";
        assertFalse(text.matches(POST_CODE_REGEX));

        text = "442-456";
        assertFalse(text.matches(POST_CODE_REGEX));

        text = "4-456";
        assertFalse(text.matches(POST_CODE_REGEX));

        text = "32-46";
        assertFalse(text.matches(POST_CODE_REGEX));

        text = "32 - 456";
        assertFalse(text.matches(POST_CODE_REGEX));

        text = "ff-dfs";
        assertFalse(text.matches(POST_CODE_REGEX));
    }
}