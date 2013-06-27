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

package com.ae.feeds.reader.read;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Parse the feed from a local file which resides on the user's system
 * 
 * @author Midhun Harikumar
 * 
 */
public class LocalRSSFeedParser extends RSSFeedParser {

	private String	localUrl;

	public LocalRSSFeedParser() {

	}

	@Override
	public void setFeedSource(String sourcePath) {
		localUrl = sourcePath;
	}

	@Override
	protected InputStream read() {
		InputStream fi = null;
		try {
			fi = new FileInputStream(localUrl);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fi;
	}

	@Override
	public String getFeedSource() {
		return localUrl;
	}
}