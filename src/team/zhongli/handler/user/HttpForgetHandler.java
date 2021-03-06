package team.zhongli.handler.user;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import team.zhongli.dao.UserDAO;
import team.zhongli.esse.User;

/**
 * @author ashraf
 *
 */
public class HttpForgetHandler implements HttpHandler {

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
	 * @throws Exception 
	 */
	private String createResponseFromQueryParams(URI uri) throws Exception {

		UserDAO userDAO = new UserDAO();
		User user = new User();
		Map<String, String> params;

		// Get the request query
		String query = uri.getQuery();

		if (query != null) {
			params = queryToList(query);
			System.out.println("size = " + params.size());
			if (params.size() == 0) {
				return "null";
			}

			user = userDAO.queryById(params.get("phone"));
			user.setPassword(params.get("password"));
			userDAO.updateUser(user);
		}
		return "Hello, " + user.getName() + " " + user.getPhone();
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