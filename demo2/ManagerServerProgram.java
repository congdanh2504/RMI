package datastoredinserver;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ManagerServerProgram {

	public static void main(String[] args) {
		String serviceName = "rmi://localhost:6789/rmiTest";
		String filename = "employees.txt";
		EmployeeServer employeeServer = null;
		try {
			employeeServer = new EmployeeServer(filename);
			LocateRegistry.createRegistry(6789);
			Naming.bind(serviceName, employeeServer);
			System.out.println("Service is running");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
