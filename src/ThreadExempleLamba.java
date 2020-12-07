
public class ThreadExempleLamba {
	
	public static void exempleWithoutLambda(String textToPrint) {
		new Thread(new Runnable() {
		    @Override
		    public void run() {
		        System.out.println(textToPrint);
		    }

		}).start();
	}
	
	public static void exempleWithLamba(String textToPrint) {
		new Thread(() -> System.out.println(textToPrint)).start();
	}
	
}
