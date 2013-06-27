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

import com.ae.feeds.reader.exceptions.AppException;
import com.ae.feeds.reader.utils.PropertyReader;

public class ParserReaderFactory {

	public static final int	LOCAL	= 0;
	public static final int	REMOTE	= 1;

	/**
	 * Returns an IFeedParser implementation
	 * 
	 * @param type
	 * @param data
	 * @return
	 * @throws AppException 
	 */
	public static IFeedParser getParser(int type) throws AppException {
		IFeedParser parser = null;
		if (type == LOCAL) {
			parser = new LocalRSSFeedParser();
			parser.setFeedSource(PropertyReader.readProperty("feed.path.local"));
		} else if (type == REMOTE) {
			parser = new RSSFeedParser();
			parser.setFeedSource(PropertyReader.readProperty("feed.path.remote"));
		}
		return parser;
	}
}
