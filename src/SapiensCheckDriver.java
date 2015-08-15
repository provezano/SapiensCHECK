
public class SapiensCheckDriver {
	public static void main(String[] args) {
		Thread t1 = new Thread(new SapiensCheck());
		t1.start();
	}
}
