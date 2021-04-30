package vehicule.service;

import vehicule.model.Vehicule;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IVehicule extends Remote {
    public void add(Vehicule v) throws RemoteException;
    public String  createMatricule() throws RemoteException;
    public List<Vehicule> findAll() throws RemoteException;
    public void delete(Vehicule v) throws RemoteException;
    public void update(Vehicule v) throws RemoteException;
}
