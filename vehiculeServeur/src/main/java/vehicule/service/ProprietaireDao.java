package vehicule.service;

import org.hibernate.Session;
import vehicule.model.Modele;
import vehicule.model.Proprietaire;
import vehicule.utils.HibernateUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ProprietaireDao extends UnicastRemoteObject implements IProprietaire{

    private Session session;
    public ProprietaireDao() throws RemoteException{
        session = HibernateUtil.getSession();
    }

    @Override
    public List<Proprietaire> findAll() throws RemoteException {
        return session.createQuery("SELECT p FROM Proprietaire p", Proprietaire.class).list();
    }
}
