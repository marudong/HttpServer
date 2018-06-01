
import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import team.zhongli.handler.admin.HttpFreezeHandler;
import team.zhongli.handler.admin.HttpThawHandler;
import team.zhongli.handler.admin.HttpUpdatepswHandler;
import team.zhongli.handler.admin.HttpUseraddHandler;
import team.zhongli.handler.admin.HttpUserdelHandler;
import team.zhongli.handler.device.HttpDevUpdateHandler;
import team.zhongli.handler.device.HttpDevctlHandler;
import team.zhongli.handler.device.HttpDevdelHandler;
import team.zhongli.handler.device.HttpDevstatusHandler;
import team.zhongli.handler.user.HttpBundHandler;
import team.zhongli.handler.user.HttpForgetHandler;
import team.zhongli.handler.user.HttpLoginHandler;
import team.zhongli.handler.user.HttpMessageHandler;
import team.zhongli.handler.user.HttpRegistertHandler;
import team.zhongli.handler.user.HttpUpdateHandler;

/**
 * @author ashraf
 * 
 */
@SuppressWarnings("restriction")
public class SimpleHttpServer {

	private HttpServer httpServer;
	//user
	private static final String REGISTER = "/app/user/register";
	private static final String login = "/app/user/login";
	private static final String forget = "/app/user/forget";
	private static final String update = "/app/user/update";
	private static final String message = "/app/user/message";
	private static final String bund = "/app/user/bund";
	
	//admin
	private static final String useradd = "/app/admin/useradd";
	private static final String userdel = "/app/admin/userdelete";
	private static final String updatepsw = "/app/admin/updatepsw";
	private static final String freeze = "/app/admin/userfreeze";
	private static final String thaw = "/app/admin/userthaw";
	
	//device
	private static final String devupdate = "/app/device/update";
	private static final String devctl = "/app/device/control";
	private static final String devstatus = "/app/device/status";
	private static final String devdelete = "/app/device/delete";

	/**
	 * Instantiates a new simple http server.
	 *
	 * @param port the port
	 * @param context the context
	 * @param handler the handler
	 */
	public SimpleHttpServer(int port) {
		try {
			//Create HttpServer which is listening on the given port 
			httpServer = HttpServer.create(new InetSocketAddress(port), 0);
			//Create a new context for the given context and handler
			//user
			httpServer.createContext(REGISTER, new HttpRegistertHandler());
			httpServer.createContext(login, new HttpLoginHandler());
			httpServer.createContext(forget, new HttpForgetHandler());
			httpServer.createContext(message, new HttpMessageHandler());
			httpServer.createContext(update, new HttpUpdateHandler());
			httpServer.createContext(bund, new HttpBundHandler());
			
			//admin
			httpServer.createContext(useradd, new HttpUseraddHandler());
			httpServer.createContext(userdel, new HttpUserdelHandler());
			httpServer.createContext(updatepsw, new HttpUpdatepswHandler());
			httpServer.createContext(freeze, new HttpFreezeHandler());
			httpServer.createContext(thaw, new HttpThawHandler());
			
			//device
			httpServer.createContext(devupdate, new HttpDevUpdateHandler());
			httpServer.createContext(devctl, new HttpDevctlHandler());
			httpServer.createContext(devstatus, new HttpDevstatusHandler());
			httpServer.createContext(devdelete, new HttpDevdelHandler());
			
			//Create a default executor
			httpServer.setExecutor(null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Start.
	 */
	public void start() {
		this.httpServer.start();
	}

}