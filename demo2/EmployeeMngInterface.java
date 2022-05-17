package datastoredinserver;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface EmployeeMngInterface extends Remote {
	
	Vector getInitData() throws RemoteException;
	
	boolean saveList(Vector data) throws RemoteException;
	
}
