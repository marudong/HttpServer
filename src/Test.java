

public class Test {

	private static final int PORT = 8000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create a new SimpleHttpServer
		System.out.println("start");
		SimpleHttpServer simpleHttpServer = new SimpleHttpServer(PORT);
		System.out.println("end");

		// Start the server
		simpleHttpServer.start();
		System.out.println("Server is started and listening on port " + PORT);
	}

}
