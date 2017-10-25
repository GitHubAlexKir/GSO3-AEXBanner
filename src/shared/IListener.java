package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IListener extends Remote {
    void setKoersen(String fondsen) throws RemoteException;
}
