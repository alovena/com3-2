import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class PooledDaytimeServer {
	public final static int PORT = 8888;

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(50);
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				try {
					Socket connection = server.accept();
					//Callable<Void> task = new DaytimeTask(connection);
					DaytimeTask run=new DaytimeTask(connection);
					Thread t=new Thread(run);
					pool.submit(t);
				} catch (IOException ex) {
				}
			}
		} catch (IOException ex) {
			System.err.println("Couldn't start server");
		}
	}

	// private static class DaytimeTask implements Callable<Void> {
	private static class DaytimeTask implements Runnable {
		private Socket connection;

		DaytimeTask(Socket connection) {
			this.connection = connection;
		}

		@Override
		public void run() {
			try {
				Writer out = new OutputStreamWriter(connection.getOutputStream());
				Date now = new Date();
				out.write(now.toString() + "\r\n");
				out.flush();
			} catch (IOException ex) {
				System.err.println(ex);
			} finally {
				try {
					connection.close();
				} catch (IOException e) {
					// ignore;
				}
			}
			
		}
	}
}