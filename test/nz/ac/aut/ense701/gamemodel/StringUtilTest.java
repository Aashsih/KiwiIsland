/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hwt3314
 */
public class StringUtilTest extends junit.framework.TestCase{
    
    public StringUtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testIsAplhaNumericNonAlphaNumericString(){
        assertFalse(StringUtil.isAlphaNumeric("!"));
        assertFalse(StringUtil.isAlphaNumeric("@"));
        assertFalse(StringUtil.isAlphaNumeric("#"));
        assertFalse(StringUtil.isAlphaNumeric("$"));
        assertFalse(StringUtil.isAlphaNumeric("%"));
        assertFalse(StringUtil.isAlphaNumeric("^"));
        assertFalse(StringUtil.isAlphaNumeric("&"));
        assertFalse(StringUtil.isAlphaNumeric("*"));
        assertFalse(StringUtil.isAlphaNumeric("("));
        assertFalse(StringUtil.isAlphaNumeric(")"));
        assertFalse(StringUtil.isAlphaNumeric("-"));
        assertFalse(StringUtil.isAlphaNumeric("_"));
        assertFalse(StringUtil.isAlphaNumeric("="));
        assertFalse(StringUtil.isAlphaNumeric("+"));
        assertFalse(StringUtil.isAlphaNumeric("]"));
        assertFalse(StringUtil.isAlphaNumeric("}"));
        assertFalse(StringUtil.isAlphaNumeric("["));
        assertFalse(StringUtil.isAlphaNumeric("{"));
        assertFalse(StringUtil.isAlphaNumeric("'"));
        assertFalse(StringUtil.isAlphaNumeric("\""));
        assertFalse(StringUtil.isAlphaNumeric(":"));
        assertFalse(StringUtil.isAlphaNumeric(";"));
        assertFalse(StringUtil.isAlphaNumeric("/"));
        assertFalse(StringUtil.isAlphaNumeric("?"));
        assertFalse(StringUtil.isAlphaNumeric("."));
        assertFalse(StringUtil.isAlphaNumeric(">"));
        assertFalse(StringUtil.isAlphaNumeric(","));
        assertFalse(StringUtil.isAlphaNumeric("<"));
        assertFalse(StringUtil.isAlphaNumeric("`"));
        assertFalse(StringUtil.isAlphaNumeric("~"));
        
    }
    
    @Test
    public void testIsAplhaNumericAlphaNumericString(){
        assertTrue(StringUtil.isAlphaNumeric("a12sSADD"));
    }
}