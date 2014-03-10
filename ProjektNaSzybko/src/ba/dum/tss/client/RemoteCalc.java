package ba.dum.tss.client;

import ba.dum.tss.shared.Stats;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("calc")
public interface RemoteCalc extends RemoteService {

	Stats stats(String str);
}
