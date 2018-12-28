package Test;

import data.Party;
import kiosk.VoteCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import Exception.*;
import org.junit.jupiter.api.TestInstance;

import auxiliars.*;
import java.util.Iterator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VoteCounterTest {
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
    void countNullTest() {
        assertEquals(0, counter.getVotesFor(counter.nullParty));
        counter.scrutinize(counter.nullParty);
        assertEquals(1, counter.getVotesFor(counter.nullParty));
    }

    @Test
    void countBlankTest() {
        assertEquals(0, counter.getVotesFor(counter.blankParty));
        counter.scrutinize(counter.blankParty);
        assertEquals(1, counter.getVotesFor(counter.blankParty));
    }

    @Test
    void scrutinizeTest() {
        assertEquals(0, counter.getVotesFor(cup));
        counter.scrutinize(cup);
        assertEquals(1, counter.getVotesFor(cup));
        counter.scrutinize(cup);
        assertEquals(2, counter.getVotesFor(cup));

        counter.scrutinize(counter.nullParty);
        assertEquals(1, counter.getNulls());

        counter.scrutinize(counter.blankParty);
        assertEquals(1, counter.getNulls());
    }

}