package main;

import Exception.WrongInputException;
import data.BiometricData;
import data.Nif;
import kiosk.VotingKiosk;
import services.ElectoralOrganism;
import services.ElectoralOrganismImpl;
import services.MailerService;
import services.MailerServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class main {
    private static Nif nif1;
    private static Nif nif2;
    private static Nif nif3;
    private static Nif nif4;
    private static Nif nif5;
    private static Nif nif6;
    private static Nif nif7;
    private static VotingKiosk kiosk;
    private static BufferedReader br;

    private static void init(ArrayList<Nif> list) throws WrongInputException{
        nif1 = new Nif("00000001A");
        nif2 = new Nif("00000002A");
        nif3 = new Nif("00000003A");
        nif4 = new Nif("00000004A");
        nif5 = new Nif("00000005A");
        nif6 = new Nif("00000006A");
        nif7 = new Nif("00000007A");
        list.add(nif1);
        list.add(nif2);
        list.add(nif3);
        list.add(nif4);
        list.add(nif5);
        list.add(nif6);
        list.add(nif7);
        ElectoralOrganism organism = new ElectoralOrganismImpl(list);
        kiosk.setElectoralOrganism(organism);
        MailerService mailService = new MailerServiceImpl();
        kiosk.setMailerService(mailService);
    }

    public static void main() throws WrongInputException {

        kiosk = new VotingKiosk();
        br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Nif> list = new ArrayList<>();
        init(list);


        System.out.println("Benvingut a la terminal de vot");
        System.out.println("Introdueix les dades Biomètriques: (escaneig facial i empremta dactilar");
        System.out.println("Le");
        BiometricData data = new BiometricData();
        System.out.println("Introdueix el teu DNI-e.           (El cens disponible es: ");
        for(Nif nifs : list){
            System.out.println(nifs.toString());
        }
        Nif dni;
        try {
            dni = new Nif(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
