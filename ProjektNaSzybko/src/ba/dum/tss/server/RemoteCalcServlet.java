package ba.dum.tss.server;

import ba.dum.tss.client.RemoteCalc;
import ba.dum.tss.shared.Stats;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RemoteCalcServlet extends RemoteServiceServlet implements
		RemoteCalc {

	private static final long serialVersionUID = 1L;

	@Override
	public Stats stats(String str) {
		String[] result = str.split(" ");
		Stats stats = new Stats();
		String test = str.replaceAll("\\s+", "");
		stats.setChars(test.length());
		stats.setWords(result.length);
		return stats;
	}

}
