package vehicule.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import vehicule.model.Vehicule;
import vehicule.utils.HibernateUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.List;

public class VehiculeDao extends UnicastRemoteObject implements IVehicule{

    private Session session;
    public VehiculeDao() throws RemoteException{
        session = HibernateUtil.getSession();
    }

    @Override
    public void add(Vehicule vehicule) throws RemoteException {
        Transaction t = session.getTransaction();
        try {
            t.begin();
            session.save(vehicule);
            t.commit();
        }catch (Exception e){
            t.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public String createMatricule() throws RemoteException {
        try {
            int id = (int)session.createQuery("SELECT MAX(v.id) FROM Vehicule v").uniqueResult();
            return "VEH-" + new DecimalFormat("00000").format((id+1));
        }catch (Exception ex){
            return "VEH-000001";
        }
    }

    @Override
    public List<Vehicule> findAll() throws RemoteException {
        return session.createQuery("SELECT v FROM Vehicule v", Vehicule.class).list();
    }

    @Override
    public void delete(Vehicule vehicule) throws RemoteException {
        Transaction t = session.getTransaction();
        try
        {
            t.begin();
            Vehicule v= (Vehicule) session.get(Vehicule .class, vehicule.getId());
            session.delete(v);
            t.commit();
        }
        catch (Exception e)
        {
            t.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Vehicule vehicule) throws RemoteException {
        Transaction t = session.getTransaction();
        try
        {
            t.begin();
            session.update(vehicule);
            t.commit();
        }
        catch (Exception e)
        {
            t.rollback();
            e.printStackTrace();
        }
    }
}
