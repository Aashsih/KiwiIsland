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

/**
 *
 * @author hwt3314
 */
public class StringUtilTest extends junit.framework.TestCase{
    
    public StringUtilTest() {
        //Default constructor for the test class
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