package Test;

import Exception.WrongInputException;
import auxiliars.dataSet;
import data.Party;
import kiosk.VoteCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


public class VoteCounterTest {
    private VoteCounter counter;
    private dataSet<Party> parties;

    private Party pp = new Party("PP");
    private Party cs = new Party("CS");
    private Party erc = new Party("ERC");
    private Party cup = new Party("CUP");
    private Party vox = new Party("VOX");
    private Party pdms = new Party("PODEMOS");
    private Party psc = new Party("PSC");


    VoteCounterTest() throws WrongInputException {
    }


    @BeforeEach
    void init() throws WrongInputException{  //inicialitzem partits i contadors
        parties = new dataSet<Party>();
        parties.add(pp);
        parties.add(cs);
        parties.add(erc);
        parties.add(cup);
        parties.add(vox);
        parties.add(pdms);
        parties.add(psc);
        counter = new VoteCounter(parties);
    }

    @Test
    void exceptionTesting() {
        assertThrows(WrongInputException.class, () -> new VoteCounter(null));
    }

    @Test
    void countPartyTest() {
        counter.countParty(erc);
        assertEquals(1, counter.getVotesFor(erc));

        counter.countParty(pdms);
        counter.countParty(pdms);
        assertEquals(2, counter.getVotesFor(pdms));

        Iterator iterator = parties.iterator();
        while(iterator.hasNext()){
            Party a = (Party) iterator.next();
            if(a!=pdms){
                assertNotEquals(2, counter.getVotesFor(a));
            }
        }
    }

    @Test
    void countNullTest() throws WrongInputException {
        assertEquals(0, counter.getVotesFor(counter.getNullParty()));
        counter.scrutinize(counter.getNullParty());
        assertEquals(1, counter.getVotesFor(counter.getNullParty()));
    }

    @Test
    void countBlankTest() throws WrongInputException {
        assertEquals(0, counter.getVotesFor(counter.getBlankParty()));
        counter.scrutinize(counter.getBlankParty());
        assertEquals(1, counter.getVotesFor(counter.getBlankParty()));
    }

    @Test
    void scrutinizeTest() throws WrongInputException {
        assertEquals(0, counter.getVotesFor(cup));
        counter.scrutinize(cup);
        assertEquals(1, counter.getVotesFor(cup));
        counter.scrutinize(cup);
        assertEquals(2, counter.getVotesFor(cup));

        counter.scrutinize(counter.getNullParty());
        assertEquals(1, counter.getNulls());

        counter.scrutinize(counter.getBlankParty());
        assertEquals(1, counter.getNulls());
    }

    @Test
    void WrongScrutinizeTest() {
        assertThrows(WrongInputException.class, ()-> counter.scrutinize(new Party("patata")));
    }

}