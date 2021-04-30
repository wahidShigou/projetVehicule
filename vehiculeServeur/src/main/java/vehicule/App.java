package vehicule;

import vehicule.service.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            System.setSecurityManager(new SecurityManager());
            Registry registry = LocateRegistry.createRegistry(5003);

            IProprietaire iProprietaire = new ProprietaireDao();
            registry.bind("proprietaireRemote", iProprietaire);

            IVehicule iVehicule = new VehiculeDao();
            registry.bind("vehiculeRemote", iVehicule);

            IModele iModele = new ModeleDao();
            registry.bind("modeleRemote", iModele);

            System.out.println("Serveur lance sur le port 5003");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
