package vehicule.service;

import vehicule.model.Modele;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IModele extends Remote {
    public List<Modele> findAll() throws RemoteException;
}
