package Test;

import Exception.*;
import auxiliars.dataSet;
import data.Nif;
import data.Party;
import kiosk.VotingKiosk;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import services.ElectoralOrganism;
import services.ElectoralOrganismImpl;
import services.MailerService;
import services.MailerServiceImpl;

import java.util.ArrayList;

import static com.sun.tools.doclint.Entity.cup;
import static org.junit.jupiter.api.Assertions.*;

class VotingKioskTest {
    private VotingKiosk terminal;
    private ElectoralOrganism OrganismeElectoral;
    private MailerService ServeiMail;
    private dataSet<Party> parties;
    private ArrayList<Nif> nifs;

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

    @BeforeAll
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
            OrganismeElectoral = new ElectoralOrganismImpl(parties, nifs);
        } catch (WrongInputException e) {
            e.printStackTrace();
        }
        ServeiMail = new MailerServiceImpl();
    }

    @Test
    void setElectoralOrganismTest() {
    }

    @Test
    void setMailerServiceTest() {
    }

    @Test
    void voteTest() {
    }

    @Test
    void sendeReceiptTest() {
    }
}