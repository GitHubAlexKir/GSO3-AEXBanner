package shared;

import shared.IFonds;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IEffectenbeurs extends IRemotePublisher, Remote {
    List<IFonds> getKoersen() throws RemoteException;
}
