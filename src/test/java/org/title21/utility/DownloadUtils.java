package org.title21.utility;
import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;

import org.title21.utility.*;

public class DownloadUtils {

	static Logger log = Logger.getLogger(DownloadUtils.class);

	/**
	 * Wait Till downloading of file is completed. This way we can verify whether file 
	 * has been downloaded.
	 * 
	 * @param fileName
	 *            - name of the downloaded file name
	 * @return file object of a downloaded file 
	 * @throws Exception
	 */
	public static File waitForDownloadToComplete(String fileName) {
		boolean isFileFound = false;
		int waitCounter = 0;
		while (!isFileFound) {
			log.info("Waiting For Download To Complete....");
			for (File tempFile : new File(BaseClass.downloadPath).listFiles()) {
				if (tempFile.getName().contains(fileName)) {
					String tempEx = FilenameUtils.getExtension(tempFile.getName());
					// crdownload - For Chrome, part - For Firefox
					if (tempEx.equalsIgnoreCase("crdownload") || tempEx.equalsIgnoreCase("part")) {
						BaseClass.sleep(1);
					} else {
						isFileFound = true;
						log.info("Download To Completed....");
						return tempFile;
					}
				}
			}
			BaseClass.sleep(2);
			waitCounter++;
			if (waitCounter > 10) {
				isFileFound = true;
			}
		}
		
		Assert.fail("File Not Downloaded");
		return null;
	}
}

