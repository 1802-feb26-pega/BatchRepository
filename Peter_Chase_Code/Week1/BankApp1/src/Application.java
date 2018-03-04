import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class Application {
	private static List<User> users = new LinkedList<>();
	private static final String fileName = "users.dat";
	private static User currentUser = null;
	
	public static void main(String[] args) {
		readUsers();
		launchLogIn();
	}
	
	
	public static void logOut() {
		currentUser = null;
		launchLogIn();
	}
	
	private static void launchLogIn() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn login = new LogIn();
					login.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void launchBank() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Banking bank = new Banking();
					bank.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private Application() { }
	
	public static User getUser() {
		return currentUser;
	}
	
	public static synchronized void addUser(User u) {
		users.add(u);
		writeUsers();
	}
	
	public static synchronized void attemptSignIn(String nameOrEmail, String password) {
		for (User u : users) {
			if (nameOrEmail.equals(u.getName()) || nameOrEmail.equals(u.getEmail())
			&& password.equals(u.getPassword())) {
				currentUser = u;
				return;
			}
		}
		
		currentUser = null;
	}
	
	public static void writeUsers() {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void readUsers() {
		try {
			FileInputStream fos = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fos);
			Object obj = ois.readObject();
			if (obj != null) {
				users = (List<User>) obj;
			}
			ois.close();
		} catch (FileNotFoundException e) {
			System.out.println("User file not created yet");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
