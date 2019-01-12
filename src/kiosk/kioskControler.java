package kiosk;

import data.BiometricData;
import data.MailAddress;
import data.Nif;
import data.Party;
import services.ElectoralOrganism;
import services.ElectoralOrganismImpl;
import services.MailerService;
import services.MailerServiceImpl;
import Exception.WrongInputException;
import Exception.BiometricVerificationFailedException;
import java.util.ArrayList;

public class kioskControler { // classe cas d'ús: exercir dret a vot

    private VotingKiosk kiosk;
    private ElectoralOrganism organism;
    private MailerService mail;
    private BiometricProcessor BioProcessor;
    public kioskControler(){
        kiosk = new VotingKiosk();
        mail = new MailerServiceImpl();
        BioProcessor = new BiometricProcessor();
    }

    public void setElectoralRoll(ArrayList<Nif> nifs) throws WrongInputException {
        organism = new ElectoralOrganismImpl(nifs);
    }

    public void VotingProcess(Nif nif, Party party) throws WrongInputException, BiometricVerificationFailedException {

        if(BioProcessor.automaticVerification() && organism.canVote(nif)){
            kiosk.vote(party);
            organism.disableVoter(nif);
            kiosk.sendeReceipt(new MailAddress("adreça@votant"));
        }

    }
}
