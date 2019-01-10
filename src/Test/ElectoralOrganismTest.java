package Test;

import Exception.WrongInputException;
import auxiliars.dataSet;
import data.Nif;
import data.Party;
import kiosk.VoteCounter;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ElectoralOrganism;
import services.ElectoralOrganismImpl;
import services.MailerService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ElectoralOrganismTest {
    private VotingKiosk terminal;
    private ElectoralOrganism OrganismeElectoral;
    private MailerService mailService;
    private dataSet<Party> parties;
    private ArrayList<Nif> nifs;
    VoteCounter counter;

    private Party pp;
    private Party cs;
    private Party erc;
    private Party cup;
    private Party vox;
    private Party pdms;
    private Party psc;

    private Nif nif1;
    private Nif nif2;
    private Nif nif3;
    private Nif nif4;
    private Nif nif5;
    private Nif nif6;
    private Nif nif7;

    @BeforeEach
    void init(){
        {
            try {
                pp = new Party("PP");
                cs = new Party("CS");
                erc = new Party("ERC");
                cup = new Party("CUP");
                vox = new Party("VOX");
                pdms = new Party("PODEMOS");
                psc = new Party("PSC");
            } catch (WrongInputException e) {
                e.printStackTrace();
            }
        }

        {
            try {
                nif1 = new Nif("12345678M");
                nif2 = new Nif("00000123A");
                nif3 = new Nif("12986452E");
                nif4 = new Nif("19273657H");
                nif5 = new Nif("18294756G");
                nif6 = new Nif("18293745Q");
                nif7 = new Nif("84776231Z");
            } catch (WrongInputException e) {
                e.printStackTrace();
            }
        }

        parties = new dataSet<Party>();
        parties.add(pp);
        parties.add(cs);
        parties.add(erc);
        parties.add(cup);
        parties.add(vox);
        parties.add(pdms);
        parties.add(psc);
        try {
            counter = new VoteCounter(parties);
        } catch (WrongInputException e) {
            e.printStackTrace();
        }

        nifs = new ArrayList<Nif>();
        nifs.add(nif1);
        nifs.add(nif2);
        nifs.add(nif3);
        nifs.add(nif4);
        nifs.add(nif5);
        nifs.add(nif6);
        nifs.add(nif7);

        try {
            OrganismeElectoral = new ElectoralOrganismImpl(counter, nifs);
        } catch (WrongInputException e) {
            e.printStackTrace();
        }
    }

    @Test
    void exceptionTesting() {
        assertThrows(WrongInputException.class, () -> new ElectoralOrganismImpl(null, nifs));
        assertThrows(WrongInputException.class, () -> new ElectoralOrganismImpl(counter, null));
        assertThrows(WrongInputException.class, () -> new ElectoralOrganismImpl(null, null));
    }

    @Test
    void canVoteTest() {
        assertEquals(true, OrganismeElectoral.canVote(nif1));
        assertEquals(true, OrganismeElectoral.canVote(nif5));

        Nif nif25 = null;
        try {
            nif25 = new Nif("12846738H");
        } catch (WrongInputException e) {
            e.printStackTrace();
        }

        assertEquals(false, OrganismeElectoral.canVote(nif25));
    }

    @Test
    void disableVoterTest() {
        boolean oldState = OrganismeElectoral.canVote(nif4);
        OrganismeElectoral.disableVoter(nif4);
        assertNotEquals(oldState, OrganismeElectoral.canVote(nif4));
    }
}