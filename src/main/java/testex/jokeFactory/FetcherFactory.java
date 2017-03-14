/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testex.jokeFactory;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author martin
 */
public class FetcherFactory implements IFetcherFactory {

  private final List<String> availableTypes = 
        Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

  @Override
  public List<String> getAvailableTypes(){ return availableTypes;}
  
  @Override
  public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
    //This is for you to do, but wait
    return null;   
  }
}

