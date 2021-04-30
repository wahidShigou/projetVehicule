package vehicule.service;

import org.hibernate.Session;
import vehicule.model.Modele;
import vehicule.model.Vehicule;
import vehicule.utils.HibernateUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ModeleDao extends UnicastRemoteObject implements IModele{

    private Session session;
    public ModeleDao() throws RemoteException{
        session = HibernateUtil.getSession();
    }

    @Override
    public List<Modele> findAll() throws RemoteException {

        return session.createQuery("SELECT m FROM Modele m", Modele.class).list();
    }
}
