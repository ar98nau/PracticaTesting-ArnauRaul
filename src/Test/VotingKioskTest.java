package Test;

import Exception.BiometricVerificationFailedException;
import Exception.VotingRightsFailedException;
import Exception.WrongInputException;
import auxiliars.dataSet;
import data.DigitalSignature;
import data.MailAddress;
import data.Nif;
import data.Party;
import kiosk.BiometricProcessor;
import kiosk.VoteCounter;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ElectoralOrganism;
import services.MailerService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class VotingKioskTest {
    private VotingKiosk terminal;
    private ElectoralOrganism organism;
    private MailerService mailService;
    private VoteCounter counter;
    private BiometricProcessor bioProc;

    @BeforeEach
    void init(){
        dataSet<Party> parties = new dataSet<>();
        try {
            counter = new TestVoteCounter(parties);
        } catch (WrongInputException e) {
            e.printStackTrace();
        }

        terminal = new VotingKiosk();

        organism = new TestElectoralOrganism();
        mailService = new TestMailerService();
        bioProc = new BiometricProcessorTest();
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
    void voteTest() throws WrongInputException {
        terminal.setVoteCounter(counter);
        terminal.vote(new Party("cup"));
        assertTrue(((TestVoteCounter)counter).wasScrutinized());
    }

    @Test
    void startNewValidVotingProcessTest() throws WrongInputException, BiometricVerificationFailedException, VotingRightsFailedException {
        terminal.setVoteCounter(counter);
        terminal.setElectoralOrganism(organism);
        terminal.setMailerService(mailService);
        terminal.setBiometricProcessor(bioProc);

        terminal.startNewVotingProcess();
        assertTrue(((TestElectoralOrganism)organism).isDisabled());
    }

    @Test
    void startNewNoValidVotingProcessTest() {
        ElectoralOrganism NoNifOrganism = new NoNifElectoralOrganismTest();
        terminal.setVoteCounter(counter);
        terminal.setElectoralOrganism(NoNifOrganism);
        terminal.setMailerService(mailService);
        terminal.setBiometricProcessor(bioProc);

        assertThrows(VotingRightsFailedException.class, () -> terminal.startNewVotingProcess());
    }

    @Test
    void sendeReceiptTest() throws WrongInputException {
        terminal.setElectoralOrganism(organism);
        terminal.setMailerService(mailService);
        terminal.sendeReceipt(new MailAddress("test@gmail.com"));
        assertTrue(((TestMailerService) mailService).wasSent());
        assertEquals(((TestMailerService) mailService).recievedSign().toString(), new DigitalSignature("1234567").toString());
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
        private boolean disabled = false;

        public TestElectoralOrganism() {
        }

        @Override
        public boolean canVote(Nif nif) {
            return true;
        }

        @Override
        public void disableVoter(Nif nif) {
            disabled = true;
        }

        public DigitalSignature askForDigitalSignature(Party party) throws WrongInputException {
            return new DigitalSignature("1234567");
        }

        public boolean isDisabled() {
            return disabled;
        }
    }

    private class NoNifElectoralOrganismTest extends TestElectoralOrganism {

        public NoNifElectoralOrganismTest() {
        }

        @Override
        public boolean canVote(Nif nif) {
            return false;
        }
    }

    private class TestMailerService implements MailerService{
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

    private class BiometricProcessorTest extends BiometricProcessor{
        @Override
        public boolean automaticVerification() {
            return true;
        }
    }
}