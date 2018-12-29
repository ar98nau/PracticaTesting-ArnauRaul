package Test;

import Exception.*;
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
import services.ElectoralOrganismImpl;
import services.MailerService;
import services.MailerServiceImpl;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VotingKioskTest {
    private VotingKiosk terminal;
    private ElectoralOrganism OrganismeElectoral;
    private MailerService ServeiMail;
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

        terminal = new VotingKiosk();
        try {
            OrganismeElectoral = new ElectoralOrganismImpl(counter, nifs);
        } catch (WrongInputException e) {
            e.printStackTrace();
        }
        ServeiMail = new MailerServiceImpl();
    }

    @Test
    void setElectoralOrganismTest() {
        terminal.setElectoralOrganism(OrganismeElectoral);
        assertEquals(OrganismeElectoral, terminal.getOrganisme());
    }

    @Test
    void setMailerServiceTest() {
        terminal.setMailerService(ServeiMail);
        assertEquals(ServeiMail, terminal.getCorreu());
    }

    @Test
    void voteTest() {
        TestVoteCounter counterTest = null;
        ElectoralOrganism organismTest = null;
        try {
            counterTest = new TestVoteCounter(parties);
            organismTest = new ElectoralOrganismImpl(counterTest, nifs);
        } catch (WrongInputException e) {
            e.printStackTrace();
        }
        terminal.setElectoralOrganism(organismTest);
        terminal.vote(cup);
        assertTrue(counterTest.wasScrutinized());

    }

    @Test
    void sendeReceiptTest() throws WrongInputException {
        TestElectoralOrganismImpl organismTest = new TestElectoralOrganismImpl(counter, nifs);
        TestMailerServiceImpl mailerTest = new TestMailerServiceImpl();
        terminal.setElectoralOrganism(organismTest);
        terminal.setMailerService(mailerTest);
        terminal.sendeReceipt(new MailAddress("test@gmail.com"));
        assertTrue(mailerTest.wasSent());
        assertEquals(mailerTest.recievedSign, new DigitalSignature("1234567"));
    }


    private class TestVoteCounter extends VoteCounter {
        private boolean scrutinized = false;

        public TestVoteCounter(Set<Party> validParties) throws WrongInputException {
            super(validParties);
        }

        public void scrutinize(Party party) {
            scrutinized = true;
        }

        public boolean wasScrutinized() {
            return scrutinized;
        }
    }

    private class TestElectoralOrganismImpl extends ElectoralOrganismImpl{

        public TestElectoralOrganismImpl(VoteCounter inputCounter, ArrayList<Nif> nifs) throws WrongInputException {
            super(inputCounter, nifs);
        }

        public DigitalSignature askForDigitalSignature(Party party) throws WrongInputException {
            return new DigitalSignature("1234567");
        }
    }

    private class TestMailerServiceImpl extends MailerServiceImpl{
        boolean sent = false;
        DigitalSignature recievedSign;

        public void send(MailAddress address, DigitalSignature signature) {
            sent = true;
            recievedSign = signature;
        }

        public boolean wasSent() {
            return sent;
        }

        public DigitalSignature recievedSign() {
            return recievedSign;
        }
    }
}