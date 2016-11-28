package com.example.typhene.a449_project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by typhene on 11/26/2016.
 * Source https://www.youtube.com/watch?v=O-ZT_dtlrR0
 */
public class InputChecker {
  public boolean isValidInput(String string) {
         /* if(string.equals("123"))
            return true;
        return false;*/
      //StringBuffer properItemStringBuffer = new StringBuffer(string);
      String letter = "[a-zA-Z]";
      Pattern pattern = Pattern.compile(getPattern(letter));
      Matcher regexMater = pattern.matcher(string);
     return string.length() <= 3 && !regexMater.matches();
    }

    private String getPattern(String letter) {
        return Pattern.compile(letter).toString();
    }
}
