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
import com.ae.feeds.reader.model.Feed;

/**
 * Defines an RSS Feed Parser
 * 
 * @author Midhun Harikumar
 * 
 */
public interface IFeedParser {

	/**
	 * Set the source for the feed
	 * 
	 * @param source
	 * @throws AppException 
	 */
	void setFeedSource(String source);

	/**
	 * Return the feed source currently used
	 * 
	 * @return
	 */
	String getFeedSource();

	/**
	 * Reads a feed
	 * 
	 * @return
	 */
	Feed readFeed() throws AppException;

}
