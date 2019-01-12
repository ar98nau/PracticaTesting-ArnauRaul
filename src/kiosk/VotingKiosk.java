package kiosk;


import Exception.*;
import data.DigitalSignature;
import data.MailAddress;
import data.Nif;
import data.Party;
import services.ElectoralOrganism;
import services.MailerService;

/**
 * Implements a simplification of Use Case: Emit eVote
 */

public class VotingKiosk {
    private ElectoralOrganism organism;
    private MailerService mail;
    private Party actualVote;
    private VoteCounter counter;
    private BiometricProcessor BioProcessor;


    public VotingKiosk() {
    }

    public void setElectoralOrganism(ElectoralOrganism eO) { organism = eO; }
    public void setMailerService(MailerService mService){ mail = mService; }
    public void setVoteCounter(VoteCounter vCounter){ counter = vCounter; }
    public void setBiometricProcessor(BiometricProcessor bP){ BioProcessor = bP; }

    public void vote(Party party) throws WrongInputException {
        counter.scrutinize(party);
    }

    public void startNewVotingProcess() throws WrongInputException, BiometricVerificationFailedException, VotingRightsFailedException {
        boolean wantAutomatic = getAutomaticVerificaiton();
        Nif nif = getVoterNif();

        if(wantAutomatic == true && organism.canVote(nif)){
            if (!BioProcessor.automaticVerification()) {
                throw new VotingRightsFailedException();
            }
        } else if (!organism.canVote(nif)) {
            throw new VotingRightsFailedException();
        }

        actualVote = getParty();
        vote(actualVote);
        organism.disableVoter(nif);

        if(wanteReceipt()){
            MailAddress mail = geteMail();
            sendeReceipt(mail);
        }

        actualVote = null;

    }

    public void sendeReceipt(MailAddress address) throws WrongInputException {
        DigitalSignature signature = organism.askForDigitalSignature(actualVote);
        mail.send(address, signature);
    }

    private boolean wanteReceipt() { //El codi correcte demanaria seleccionar l'emisió de e-Receipt per la pantalla
        return true;
    }

    private MailAddress geteMail() throws WrongInputException { //El codi correcte demanaria una entrada de E-MAIL per la pantalla
        return new MailAddress("adreça@votant");
    }

    private boolean getAutomaticVerificaiton() { //El codi correcte demanaria seleccionar l'autenticació automàtica per la pantalla
        return true;
    }

    private Nif getVoterNif() throws WrongInputException { //El codi correcte demanaria una entrada de DNI per la pantalla
        return new Nif("12986452E");
    }

    public Party getParty() throws WrongInputException {    //El codi correcte mostraria el llistat i demanaria una entrada per la pantalla
        return new Party("erc");
    }

    public ElectoralOrganism getOrganism() {
        return organism;
    }

    public MailerService getMail() {
        return mail;
    }

    public VoteCounter getCounter() {
        return counter;
    }
}
