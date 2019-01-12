package Test;

import Exception.WrongInputException;
import auxiliars.dataSet;
import data.DigitalSignature;
import data.MailAddress;
import data.Nif;
import data.Party;
import kiosk.VoteCounter;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ElectoralOrganism;
import services.MailerService;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VotingKioskTest {
    private VotingKiosk terminal;
    private ElectoralOrganism organism;
    private MailerService mailService;
    private VoteCounter counter;
    private ArrayList<Nif> nifs;

    private Nif nif1;
    private Nif nif2;
    private Nif nif3;
    private Nif nif4;
    private Nif nif5;
    private Nif nif6;
    private Nif nif7;

    private Party pp = null;
    private Party cs = null;
    private Party erc = null;
    private Party cup = null;
    private Party vox = null;
    private Party pdms = null;
    private Party psc = null;

    @BeforeEach
    void init(){
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

        dataSet<Party> parties = new dataSet<>();
        parties.add(pp);
        parties.add(cs);
        parties.add(erc);
        parties.add(cup);
        parties.add(vox);
        parties.add(pdms);
        parties.add(psc);

        try {
            counter = new TestVoteCounter(parties);
        } catch (WrongInputException e) {
            e.printStackTrace();
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

        nifs = new ArrayList<Nif>();
        nifs.add(nif1);
        nifs.add(nif2);
        nifs.add(nif3);
        nifs.add(nif4);
        nifs.add(nif5);
        nifs.add(nif6);
        nifs.add(nif7);

        terminal = new VotingKiosk();

        organism = new TestElectoralOrganism();
        mailService = new TestMailerService();
    }

    @Test
    void setElectoralOrganismTest() {
        terminal.setElectoralOrganism(organism);
        assertEquals(organism, terminal.getOrganism());
    }

    @Test
    void setMailerServiceTest() {
        terminal.setMailerService(mailService);
        assertEquals(mailService, terminal.getMail());
    }

    @Test
    void setVoteCounterTest() {
        terminal.setVoteCounter(counter);
        assertEquals(counter, terminal.getCounter());
    }

    @Test
    void voteTest() {
        terminal.setElectoralOrganism(organism);
        terminal.setVoteCounter(counter);
        terminal.vote(cup);
        assertTrue(((TestVoteCounter)counter).wasScrutinized());
    }

    @Test
    void sendeReceiptTest() throws WrongInputException {
        terminal.setElectoralOrganism(organism);
        terminal.setMailerService(mailService);
        terminal.sendeReceipt(new MailAddress("test@gmail.com"));
        assertTrue(((TestMailerService) mailService).wasSent());
        assertEquals(((TestMailerService) mailService).recievedSign(), new DigitalSignature("1234567"));
    }


    private class TestVoteCounter extends VoteCounter {
        private boolean scrutinized = false;

        public TestVoteCounter(Set<Party> validParties) throws WrongInputException {
            super(validParties);
        }

        @Override
        public void scrutinize(Party party) {
            scrutinized = true;
        }

        public boolean wasScrutinized() {
            return scrutinized;
        }
    }

    private class TestElectoralOrganism implements ElectoralOrganism{

        public TestElectoralOrganism() {
        }

        @Override
        public boolean canVote(Nif nif) {
            return false;
        }

        @Override
        public void disableVoter(Nif nif) {

        }

        public DigitalSignature askForDigitalSignature(Party party) throws WrongInputException {
            return new DigitalSignature("1234567");
        }
    }

    private class classMailerService implements MailerService{

        @Override
        public void send(MailAddress address, DigitalSignature signature) {
        }
    }

    private class TestMailerService extends classMailerService{
        boolean sent = false;
        private DigitalSignature recievedSign;

        public TestMailerService() {
        }

        @Override
        public void send(MailAddress address, DigitalSignature signature) {
            sent = true;
            recievedSign = signature;
        }

        private boolean wasSent() {
            return sent;
        }

        public DigitalSignature recievedSign() {
            return recievedSign;
        }
    }
}