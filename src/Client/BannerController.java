package Client;

import shared.IEffectenbeurs;
import shared.IListener;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BannerController extends UnicastRemoteObject implements IListener {

    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    private Registry registry;

    public BannerController(AEXBanner banner,String ipAdress, int portNumber) throws RemoteException, NotBoundException {

        this.banner = banner;
        this.registry = locateRegistry(ipAdress,portNumber);
        this.effectenbeurs = (IEffectenbeurs) registry.lookup("effectenBeurs");
        this.effectenbeurs.addListener(this);
    }

    private Registry locateRegistry(String ipAdress, int portNumber)
    {
        try
        {
         return LocateRegistry.getRegistry(ipAdress, portNumber);
        }
         catch (RemoteException ex) {
        System.out.println("Client: Cannot locate registry");
        System.out.println("Client: RemoteException: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public void setKoersen(String fondsen) throws RemoteException {
        banner.setKoersen(fondsen);
    }

    public void stop() throws RemoteException {
        this.effectenbeurs.removeListener(this);
    }
}
