package Server;

import shared.Fonds;
import shared.IEffectenbeurs;
import shared.IFonds;
import shared.IListener;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Effectenbeurs extends UnicastRemoteObject implements IEffectenbeurs {
    private List<IFonds> fonds = new ArrayList<>();
    private List<IListener> listeners = new ArrayList<>();
    private Timer pollingTimer;
    private Random random;


    public Effectenbeurs() throws RemoteException {
        fonds.add(new Fonds("Apple",980.29));
        fonds.add(new Fonds("Samsung",560.24));
        fonds.add(new Fonds("OnePlus",356.34));
        fonds.add(new Fonds("Sony",479.34));
        fonds.add(new Fonds("BLU",236.34));
        fonds.add(new Fonds("Acer",486.34));
        random = new Random();
        pollingTimer = new Timer();
        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Update();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }, 0 , 2000);
    }

    private void Update() throws RemoteException {
        for (IFonds fond: fonds) {
            String koers = String.valueOf((random.nextInt() + random.nextDouble())).substring(0,4);
            fond.setKoers(Double.parseDouble(koers));
        }
        StringBuilder koersbuilder = new StringBuilder();
        for(IFonds fond : fonds){
            koersbuilder.append(fond.toString());
        }
        String koersen = koersbuilder.toString();
        for (IListener listener: listeners){
            listener.setKoersen(koersen);
        }

    }

   @Override
   public List<IFonds> getKoersen() {
       return fonds;
   }

    @Override
    public void addListener(IListener listener) throws RemoteException {
        listeners.add(listener);
    }

    @Override
    public void removeListener(IListener listener) throws RemoteException {
        listeners.remove(listener);
    }
}
