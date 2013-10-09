package api;

import java.io.IOException;

public class HattrickXMLCollector {
	
	public static void main(String[] args) {
		HattrickXMLCollector collector = new HattrickXMLCollector();
		collector.downloadMatchDetailsXML(453282171, 453282181);
	}
	
	private void downloadMatchDetailsXML(int fromMatchId, int toMatchId)
	{
		HattrickDownloader downloader = new HattrickDownloader();
		for(int i = fromMatchId; i <= toMatchId; i++)
		{
			try {
				downloader.createMatchDetailsXML(i);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					Thread.sleep(2000);
					System.out.println("ok");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
