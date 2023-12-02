package ETU.WebGWT.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ETU.WebGWT.shared.Musician;

import com.google.gwt.core.client.GWT;
import java.util.List;
/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	List<Musician> getMusicianList();
  /**
	 * Utility/Conveniece class.
	 */
  public static class App{
	  private static GreetingServiceAsync ourInstance = GWT.create(GreetingService.class);
	  
	  public static synchronized GreetingServiceAsync getInstance() {
		  return ourInstance;
	  }
  }
}
