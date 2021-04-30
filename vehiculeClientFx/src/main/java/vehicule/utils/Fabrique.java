package vehicule.utils;

import vehicule.service.IModele;
import vehicule.service.IProprietaire;
import vehicule.service.IVehicule;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Fabrique {
    private static IProprietaire iProprietaire;
    private static IVehicule iVehicule;
    private static IModele iModele;

    private static void init() throws Exception{
        try {
            Registry registry = LocateRegistry.getRegistry(5003);
            iProprietaire = (IProprietaire) registry.lookup("proprietaireRemote");
            iModele = (IModele) registry.lookup("modeleRemote");
            iVehicule = (IVehicule) registry.lookup("vehiculeRemote");
        }
        catch(Exception e){
            throw e;
        }
    }

    public static IProprietaire getiProprietaire() throws  Exception{
        try {
            if(iProprietaire == null) {
                init();
            }
            return iProprietaire;
        }
        catch(Exception e){
            throw e;
        }
    }

    public static IModele getiModele() throws  Exception{
        try {
            if(iModele == null) {
                init();
            }
            return iModele;
        }
        catch(Exception e){
            throw e;
        }
    }

    public static IVehicule getiVehicule() throws  Exception{
        try {
            if(iVehicule == null) {
                init();
            }
            return iVehicule;
        }
        catch(Exception e){
            throw e;
        }
    }


}
