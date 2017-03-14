/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.hamcrest.Matcher;
import testex.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.Test;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import testex.jokeFactory.FetcherFactory;
import testex.JokeFetcher;
import testex.jokeFactory.*;

/**
 *
 * @author martin
 */
@RunWith(MockitoJUnitRunner.class)
public class jokeFetcherTests {

    private DateFormatter formatter;
    private FetcherFactory factory;
    private ChuckNorris chuck;
    private EduJoke eduprog;
    private Moma moma;
    private Tambal tambal;

    public jokeFetcherTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        formatter = Mockito.mock(DateFormatter.class);
        factory = Mockito.mock(FetcherFactory.class);
        chuck = Mockito.mock(ChuckNorris.class);
        eduprog = Mockito.mock(EduJoke.class);
        moma = Mockito.mock(Moma.class);
        tambal = Mockito.mock(Tambal.class);
    }

    @After
    public void tearDown() {
    }

    //verifies the testGetAvailableTypes()
    @Test
    public void getAvailableTypesTest() {
        JokeFetcher jf = new JokeFetcher(formatter, factory);
        List<String> expectedTypes = Arrays.asList("eduprog", "chucknorris", "moma", "tambal");
        int expectedLength = expectedTypes.size();

        List<String> returnedTypes = jf.getAvailableTypes();
        assertThat(returnedTypes.size(), is(equalTo(expectedLength)));
        assertThat(returnedTypes, (Matcher) hasItems(expectedTypes.toArray()));
    }

    //verifies the checkIfValidToken()
    @Test
    public void checkIfValidTokenTest() {
        JokeFetcher jf = new JokeFetcher(formatter, factory);
        String validString = "chucknorris";
        String invalidString = "dark humor";
        boolean validresult = jf.isStringValid(validString);
        boolean invalidresult = jf.isStringValid(invalidString);

        assertThat(validresult, is(equalTo(true)));
        assertThat(invalidresult, is(equalTo(false)));

    }

    @Test
    public void getFactoriesTest() {
        // Couldnt get it to work... Java!!
        given(factory.getJokeFetchers(anyObject())).willReturn(Arrays.asList(chuck,moma,eduprog,tambal));  
        
        List<IJokeFetcher>  resultFetchers = factory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal");
        IJokeFetcher[] expectedFetchers = {eq(new EduJoke()), eq(new ChuckNorris()), eq(new Moma()), eq(new Tambal())};
        
        assertThat(resultFetchers.size(),is(equalTo(expectedFetchers.length)));
         assertThat(resultFetchers, (Matcher) hasItems(expectedFetchers));
    }

    @Test
    public void getJokesFormattedDateTest() throws JokeException {

        given(formatter.getFormattedDate(eq("Europe/Copenhagen"), anyObject())).willReturn("17 feb. 2017 10:56 AM");

        JokeFetcher jf = new JokeFetcher(formatter, factory);
        Jokes returnedJokes = jf.getJokes("eduprog", "Europe/Copenhagen");
        assertThat(returnedJokes.getTimeZoneString(), is(equalTo("17 feb. 2017 10:56 AM")));
        Mockito.verify(formatter, times(1)).getFormattedDate(anyObject(), anyObject());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
