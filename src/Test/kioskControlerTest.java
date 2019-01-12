package Test;

import data.DigitalSignature;
import data.MailAddress;
import data.Nif;
import data.Party;
import kiosk.VotingKiosk;
import kiosk.kioskControler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ElectoralOrganismImpl;
import Exception.*;
import services.MailerServiceImpl;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class kioskControlerTest {

    kioskControler controler;

    @BeforeEach
    void init(){
        controler = new kioskControler();

    }
    @Test
    void votingProcess() {

    }

    private class ElectoralOrganismMock extends ElectoralOrganismImpl{

        public ElectoralOrganismMock(ArrayList<Nif> nifs) throws WrongInputException {
            super(nifs);
        }
        @Override
        public boolean canVote(Nif nif){
            return true;
        }
    }

    private class MailServiceMock extends MailerServiceImpl{
        boolean send = false;
        @Override
        public void send(MailAddress address, DigitalSignature signature) {
            send = true;
        }
    }

    private class VotingKioskMock extends VotingKiosk{
        public boolean voted = false;
        @Override
        public void vote(Party party){
            voted = true;
        }
    }
}