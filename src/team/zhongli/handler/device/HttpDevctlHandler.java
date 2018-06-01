package team.zhongli.handler.device;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URI;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.FileChannel.MapMode;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import team.zhongli.dao.UserDAO;
import team.zhongli.esse.Device;
import team.zhongli.esse.User;

/**
 * @author ashraf
 *
 */
public class HttpDevctlHandler implements HttpHandler {

	private static final int HTTP_OK_STATUS = 200;

	private static final String AND_DELIMITER = "&";
	private static final String EQUAL_DELIMITER = "=";

	public void handle(HttpExchange t) throws IOException {
		Executor executor = Executors.newFixedThreadPool(100);

		executor.execute(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				URI uri = t.getRequestURI();
				String response;

				try {
					response = createResponseFromQueryParams(uri);
					System.out.println("Response: " + response);
					// Set the response header status and length
					t.sendResponseHeaders(HTTP_OK_STATUS, response.getBytes().length);
					// Write the response string
					OutputStream os = t.getResponseBody();
					os.write(response.getBytes());
					os.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// Create a response form the request query parameters
	}

	/**
	 * Creates the response from query params.
	 *
	 * @param uri
	 *            the uri
	 * @return the string
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws SQLException
	 */
	private String createResponseFromQueryParams(URI uri) throws IOException, InterruptedException, SQLException {

		UserDAO userDAO = new UserDAO();
		User user = new User();
		Device device = new Device();
		Map<String, String> params;
		
		RandomAccessFile raf;
        //获取随机存取文件对象，建立文件和内存的映射，即时双向同步
        raf = new RandomAccessFile("E:/127.0.0.1", "rw");
        
        FileChannel fc = raf.getChannel();      //获取文件通道
        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, 1024);  //获取共享内存缓冲区
        FileLock flock=null; 
        String name = "name";
        

		// Get the request query
		String query = uri.getQuery();

		if (query != null) {
			params = queryToList(query);
			System.out.println("size = " + params.size());
			if (params.size() == 0) {
				return "null";
			}

			if (params.get("onstatus") != null) {
				flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + 'a');
	            mbb.put(0,(byte)'o');
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
			} 
			if (params.get("offstatus") != null) {
				flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + 'a');
	            mbb.put(0,(byte)'f');
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
			} 
			if (params.get("roomtemp") != null) {
				flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + params.get("roomtemp"));
	            mbb.put(0,(byte)'t');
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
	            
	            byte[] recv = new byte[2];
	            flock = fc.lock();
	            mbb.get(recv);
	            if(recv[0] == 't') 
	            flock.release();
	            System.out.println("------------" + new String(recv));
	            String str = new String(recv);
	            device.setRoomtemp(Integer.parseInt(str));
	            mbb.clear();
	            flock.release();
	            Thread.sleep(1000);
			} 
			if (params.get("current") != null) {
				flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + 'a');
	            mbb.put(0,(byte)'c');
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
	            
	            byte[] recv = new byte[12];
	            flock = fc.lock();
	            mbb.get(recv);
	            device.setCurrent(new String(recv));
	            mbb.clear();
	            flock.release();
	            Thread.sleep(1000);
			} 
			if (params.get("fireadd") != null) {
				flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + params.get("fireadd"));
	            mbb.put(0,(byte)'a');
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
	            
	            byte[] recv = new byte[1];
	            flock = fc.lock();
	            mbb.get(recv);
	            device.setFire(Integer.parseInt(new String(recv)));
	            mbb.clear();
	            flock.release();
	            Thread.sleep(1000);
			} 
			if (params.get("firedec") != null) {
				flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + params.get("firedec"));
	            mbb.put(0,(byte)'b');
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
	            
	            byte[] recv = new byte[1];
	            flock = fc.lock();
	            mbb.get(recv);
	            device.setFire(Integer.parseInt(new String(recv)));
	            mbb.clear();
	            flock.release();
	            Thread.sleep(1000);
			} 
			if (params.get("fanadd") != null) {
				flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + 'a');
	            mbb.put(0,(byte)'d');
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
	            
	            byte[] recv = new byte[1];
	            flock = fc.lock();
	            mbb.get(recv);
	            device.setFan(Integer.parseInt(new String(recv)));
	            mbb.clear();
	            flock.release();
	            Thread.sleep(1000);
			} 
			if (params.get("fandec") != null) {
				flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + 'a');
	            mbb.put(0,(byte)'e');
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
	            
	            byte[] recv = new byte[1];
	            flock = fc.lock();
	            mbb.get(recv);
	            device.setFan(Integer.parseInt(new String(recv)));
	            mbb.clear();
	            flock.release();
	            Thread.sleep(1000);
			} 
			if (params.get("modeset") != null) {
				flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + 'a');
	            mbb.put(0,(byte)'m');
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
	            
	            byte[] recv = new byte[100];
	            flock = fc.lock();
	            mbb.get(recv);
	            device.setMode(new String(recv));
	            mbb.clear();
	            Thread.sleep(1000);
			} 
			if (params.get("weekset") != null) {
				flock=fc.lock();
	            System.out.println(System.currentTimeMillis() +  ":write:" + 'a');
	            mbb.put(0,(byte)'w');
	            flock.release();        //释放锁  
	            Thread.sleep(1000);
	            
	            byte[] recv = new byte[100];
	            flock = fc.lock();
	            mbb.get(recv);
	            device.setWeek(new String(recv));
	            mbb.clear();
	            Thread.sleep(1000);
			}
		}
		
		return "Hello, " + device.getCurrent()+ " " + device.getRoomtemp()+" " + device.getFire();
	}

	// Get URL parameters
	public Map<String, String> queryToList(String query) {
		Map<String, String> result = new HashMap<String, String>();
		for (String param : query.split(AND_DELIMITER)) {
			String pair[] = param.split(EQUAL_DELIMITER);
			if (pair.length > 1) {
				result.put(pair[0], pair[1]);
			} else {
				result.put(pair[0], "");
			}
		}
		return result;
	}
}