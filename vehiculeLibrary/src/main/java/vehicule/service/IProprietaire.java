package vehicule.service;

import vehicule.model.Modele;
import vehicule.model.Proprietaire;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IProprietaire extends Remote {
    public List<Proprietaire> findAll() throws RemoteException;
}
