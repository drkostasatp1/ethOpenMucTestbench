package org.mposch.ethOpenmuc.updaters;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.mposch.ethOpenmuc.ChannelState;
import org.mposch.ethOpenmuc.Config;
import org.mposch.ethOpenmuc.contracts.ContractBean;
import org.mposch.ethOpenmuc.gui.MainWindow;
import org.mposch.ethOpenmuc.gui.GuiController;
import org.mposch.ethOpenmuc.gui.tableModels.TableModelChannelState;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.Channel;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.JsonChannelList;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.RecordDouble;
import org.mposch.ethOpenmuc.updaters.openMucDatatypes.RecordWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * This will pull channels from OpenMuc and put them into the ChannelState
 * 
 * @author mposch
 *
 */
@Component
public class OpenMucFetcher implements Runnable {
	// Auto create the OpenMuc channel state object and the ethereum Smart
	// contract Interface
	@Autowired
	private ChannelState				channelState;
	@Autowired
	private ContractBean				contractBean;
	@Autowired
	private TableModelChannelState		tableModelChannelState;
	@Autowired
	GuiController						gui;
	@Autowired
	private MainWindow					mainWindow;

	private JsonChannelList				cl;
	private ScheduledExecutorService	sheduler;

	// @Autowired
	// private RecordStorage storage;
	/**
	 * This Method will be called by Spring The start method has no parameters
	 */
	public void run() {
		try
		{
			this.cl = getOpenMucChannelList();
			for (Channel ch : cl.getChannels())
			{
				// System.out.println("LOOP:" + re.getId() + " record: " +
				// re.getRecord().toString());
				if ((ch.getRecord().getValue() != null) && (ch.getRecord() != null))
				{
					ch.setSource("OPENMUC");
					ch.setBlockchainStatus("READY");
					ch.setPriority(1);
					// now update the corresponding index of the table an put
					// the Value into the channelState
					int i = channelState.put(ch);

				}
			}

			
			// Update the table, if currently not beeing edited:
			
			tableModelChannelState.fireTableDataChanged();
			
			
			// Update all channels that need to be synced to OpenMuc
			for (int i = 0; i < channelState.getSize(); i++)
			{
				Channel ch = channelState.getChannelAtIndex(i);
				if (ch.isSyncToOpenMuc() == true)
				{
					this.updateChannel(ch);
				}
			}

		}
		catch (Exception e)
		{
			gui.Error("OPENMUC DATASYNC EXCEPTION: " + e.getMessage());
		}
		// this.tableModelChannelState.fireTableDataChanged();
	} // end run

	/**
	 * This method will accces the OpenMuc framework using the Spring provided
	 * Rest Template and parse the result using the Jackson library..
	 * 
	 * @return The Parsed list of Channels present in openmuc
	 */
	public JsonChannelList getOpenMucChannelList() {
		RestTemplate restTemplate = new RestTemplate();
		// Get the channel list from openMuc, and parse this list using the
		// fasterxml library
		// JsonRecord r =
		// restTemplate.getForObject("http://127.0.0.1:8888/rest/channels/power_grid",
		// JsonRecord.class);
		return restTemplate.getForObject("http://127.0.0.1:8888/rest/channels", JsonChannelList.class);
	}

	/**
	 * This method updates a channel in the openMuc Framework, using the Rest
	 * API. If a channel does not exist, it will fail silent but output an
	 * Errormessage to the commandline
	 * 
	 * @param ch
	 */
	public void updateChannel(Channel ch) {

		RestTemplate rt = new RestTemplate();
		// To comply with Json the specification we need to wrap the Record
		// object into a wrapper:
		RecordWrapper rw = new RecordWrapper();
		// OpenMuc has different values types, so we need to consider two cases,
		// String and Double
		// The default here is String.

		if (ch.getType().equals("DOUBLE"))
		{
			RecordDouble re_D = new RecordDouble(ch.getRecord());
			rw.record = re_D;
		}
		else if (ch.getType().equals("STRING"))
		{
			rw.record = ch.getRecord();
		}

		String url = "http://127.0.0.1:8888/rest/channels/" + ch.getId();
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		ResponseEntity<RecordWrapper> response;
		HttpEntity<String> httpRecord = new HttpEntity<>(rw.toJsonString(), headers);
		response = rt.exchange(url, HttpMethod.PUT, httpRecord, RecordWrapper.class);
		// System.out.println("OpenMucFetcher.updateChannel()");
		// System.out.println(response);
	}

	// Methods that control the periodic updater

	public void stopUpdater() {

		if (sheduler != null) sheduler.shutdown();
	}

	public void sheduleUpdater() {
		System.out.println("Start OpenMuc Updater scheduled..");
		sheduler = Executors.newScheduledThreadPool(1);
		sheduler.scheduleAtFixedRate(() -> {
			this.run();

		}, 0, Config.OPENMUCUPDATERATE, SECONDS);
	}
}
