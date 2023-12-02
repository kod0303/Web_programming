package ETU.WebGWT.server;

import ETU.WebGWT.client.GreetingService;
import ETU.WebGWT.shared.Musician;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;
/**
 * The server side implementation of the RPC service.
 */
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {

    private static List<Musician> musicians = null;
    static {
    	musicians = new ArrayList<>();
    	musicians.add(new Musician("Иванов Юрий", "Альт", 3));
    	musicians.add(new Musician("Ежова Арина", "Скрипка", 5));
    	musicians.add(new Musician("Гришин Артем", "Труба", 9));
  }

@Override
public List<Musician> getMusicianList() {
	return musicians;
}
}
