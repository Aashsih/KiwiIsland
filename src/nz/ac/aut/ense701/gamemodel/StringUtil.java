/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gamemodel;

/**
 *
 * @author NathanK
 */
public class StringUtil {
    
   private StringUtil(){
       
   }
   
   public static boolean isAlphaNumeric(String string){
       if(string.matches("^[\\p{Alnum}]*$")){
            return true;
        }           
        return false;
   }
}
