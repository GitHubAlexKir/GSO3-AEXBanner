package shared;

import java.rmi.*;

public interface IRemotePublisher {
    /**
     * listener abonneert zich op PropertyChangeEvent's zodra property is
     * gewijzigd
     * @param listener
     */
    void addListener(IListener listener)
            throws RemoteException;

    /**
     * het abonnement van listener voor PropertyChangeEvent's mbt property wordt
     * opgezegd
     * @param listener PropertyListener
     * opgezegd
     */
    void removeListener(IListener listener)
            throws RemoteException;
}
