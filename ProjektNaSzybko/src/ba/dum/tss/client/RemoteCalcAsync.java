package ba.dum.tss.client;

import ba.dum.tss.shared.Stats;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RemoteCalcAsync {

	void stats(String str, AsyncCallback<Stats> callback);

}
