package calculator;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MathServer {

	public static void main(String[] args) {
		String serviceName = "rmi://localhost:6789/rmiTest";
		Mathematician server;
		try {
			LocateRegistry.createRegistry(6789);
			server = new Mathematician();
			Naming.bind(serviceName, server);
			System.out.println("Service is running");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
