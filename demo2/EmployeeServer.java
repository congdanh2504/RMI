package datastoredinserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.StringTokenizer;
import java.util.Vector;

public class EmployeeServer extends UnicastRemoteObject implements EmployeeMngInterface {
	String filename;

	protected EmployeeServer(String filename) throws RemoteException {
		super();
		this.filename = filename;
	}

	@Override
	public Vector getInitData() throws RemoteException {
		Vector data = new Vector();
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			StringTokenizer stringTokenizer;
			String code, name;
			int salary;
			while ((line = bufferedReader.readLine()) != null) {
				stringTokenizer = new StringTokenizer(line, ", ");
				Vector v = new Vector();
				v.add(stringTokenizer.nextToken());
				v.add(stringTokenizer.nextToken());
				v.add(Integer.parseInt(stringTokenizer.nextToken()));
				data.add(v);
			}
			bufferedReader.close();
			fileReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public boolean saveList(Vector data) throws RemoteException {
		try {
			FileWriter fileWriter = new FileWriter(filename);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for (int i=0; i<data.size(); ++i) {
				Vector v = (Vector) data.get(i);
				String s = v.get(0) + ", "  + v.get(1) + ", " + v.get(2);
				printWriter.println(s);
			}
			printWriter.close();
			fileWriter.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
