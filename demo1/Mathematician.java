package calculator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Mathematician extends UnicastRemoteObject implements MathService {

	protected Mathematician() throws RemoteException {}

	private static final long serialVersionUID = 1L;

	@Override
	public double add(double a, double b) throws RemoteException {
		return a + b;
	}

	@Override
	public double subtract(double a, double b) throws RemoteException {
		return a - b;
	}
	
}
