package team.ml.mysql;

import java.net.UnknownHostException;
import java.util.concurrent.Executors;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.common.JISystem;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.common.NotConnectedException;
import org.openscada.opc.lib.da.AddFailedException;
import org.openscada.opc.lib.da.AutoReconnectController;
import org.openscada.opc.lib.da.DuplicateGroupException;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;
import org.openscada.opc.lib.da.Server;

public class SCADA {
 public static void main(String[] args) throws Exception{
  AutoReconnectController autos = null;
  try {
   JISystem.setAutoRegisteration(true);

   ConnectionInformation ci = new ConnectionInformation();
   ci.setHost("localhost");
   ci.setDomain("localhost");
   ci.setClsid("4B12BF21-3C60-4C48-A47F-E5F1E3BCFD34");
   ci.setUser("PC-CONFIG");
   ci.setPassword("admin");

   final Server s = new Server(ci,Executors.newSingleThreadScheduledExecutor());
         autos = new AutoReconnectController(s);
   autos.connect();Thread.sleep(100);
   
   syncWrite(s);

//   dumpTree(s.getTreeBrowser().browse(),0);

  

//   final AccessBase access = new Async20Access(s,100,false);
//            access.addItem ( "sim.test.D0", new DataCallbackDumper());
//            access.bind ();
//            Thread.sleep(100*1000);
//            access.unbind();

  } catch (IllegalArgumentException e) {
   e.printStackTrace();
  } catch (InterruptedException e) {
   e.printStackTrace();
  }finally{
   autos.disconnect();
  }  
 }
 
 /** 
  * 使用Item类write方法写入数据，并直接通过Item的read方法同步读数据 
  * @throws Exception 
  */  
 public static void syncWrite(Server server) throws Exception{  
     final String itemId="Bucket Brigade.Int4";  
     Group group = server.addGroup("test");  
     Item item = group.addItem(itemId); //get item for writing  
       
     //第一次读  
     ItemState itemState = item.read(true);  
     System.out.println("<<< first read: " + itemState.getValue());  
       
     final JIVariant value = new JIVariant(100);  
     try {  
         System.out.println(">>> writing value: " + value.getObjectAsInt());  
         item.write(value);  
     } catch (JIException e) {  
         e.printStackTrace();  
     }  
       
     itemState = item.read(true);  
     System.out.println("<<< after writing: " + itemState.getValue());  
 }  
}
