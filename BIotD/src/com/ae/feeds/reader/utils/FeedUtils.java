/*
 * Copyright 2013 Midhun Harikumar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ae.feeds.reader.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ae.feeds.reader.model.FeedMessage;

public class FeedUtils {
	public static long fs;
	/**
	 * Returns a simple name, link pair removing duplicates from the FeedMessages
	 * 
	 * @param source
	 * @return
	 */
	public static Map<String, String> removeDuplicateFeeds(List<FeedMessage> source) {
		if (source == null) {
			return null;
		}
		// lets parse the data
		Map<String, String> uniqueUrlsMap = new HashMap<String, String>();
		String link = "";
		String imageName = "";

		for (FeedMessage fm : source) {
			link = fm.getLink();
			// Get just the image name from the url
			imageName = link.substring(link.indexOf("%2f") + 3, link.indexOf("_"));
			uniqueUrlsMap.put(imageName, link);
		}
		return uniqueUrlsMap;
	}

	/**
	 * Save an image url to the disk
	 * 
	 * @param imageUrl
	 * @param destinationFile
	 * @param overwrite 
	 * @throws IOException
	 */
	public static long saveImageAsFile(String imageUrl, String destinationFile, boolean overwrite) {
		URL url = null;
		long fileSize = 0;
		InputStream is = null;
		OutputStream os = null;
		try {
			if (overwrite == false) {
				// If overwrite is false, check if the file exists
				File file = new File(destinationFile);
				if (file.exists()) {
					// Short circuit the file saving logic
					throw new Exception("Skipping save as file already exists");
				}
			}
			
			url = new URL(imageUrl);
			is = url.openStream();
			os = new FileOutputStream(destinationFile);
			
			byte[] b = new byte[4096];
			int length;
			while ((length = is.read(b)) != -1) {
				fileSize += length;
				os.write(b, 0, length);
				//fs = (long) (fileSize*.000976562);
				setFileSize(fileSize);
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (is != null)
					is.close();
				if (os != null)
					os.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				return fileSize;
			}
		}
		return fileSize;
	}
	//get the size of file through saveImageAsFile()
	public static long getfileSize(){
		return fs;
	}
	//Set a static variable to the image file size in Kb
	public static void setFileSize(long fSize){
		fs =(long) (fSize*.000976562) ;
	}
}
