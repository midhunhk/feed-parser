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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import com.ae.feeds.reader.exceptions.AppException;
import com.ae.feeds.reader.model.Feed;
import com.ae.feeds.reader.model.FeedMessage;

/**
 * Defines an RSS Feed Parser implementation that tries to parse an RSS that resides on the web
 * 
 * @author Midhun
 * 
 */
public class RSSFeedParser implements IFeedParser {

	static final String	TITLE		= "title";
	static final String	ITEM		= "item";
	static final String	DESC		= "description";
	static final String	ENCLOSURE	= "enclosure";
	static final String	PUB_DATE	= "pubDate";
	static final String	GUID		= "guid";
	static final String	LINK		= "link";

	private URL			url			= null;

	/**
	 * create a parser instance here
	 * 
	 * @param feedUrl
	 */
	public RSSFeedParser() {

	}

	@Override
	public void setFeedSource(String feedUrl) {
		try {
			if (feedUrl != null) {
				this.url = new URL(feedUrl);
			}
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		}
	}

	public Feed readFeed() {
		Feed feed = null;
		try {
			boolean isFeedHeader = true;

			String desc = "";
			String title = "";
			String link = "";
			String pubDate = "";

			// Setup the XML Reading Factory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			// Read from the source
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			// Loop through the DOM events while parsing
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPort = event.asStartElement().getName().getLocalPart();

					if (localPort.equalsIgnoreCase(ITEM)) {
						if (isFeedHeader) {
							isFeedHeader = false;
							// A new feed element is created
							feed = new Feed(title, link, desc, pubDate);
						}
						event = eventReader.nextEvent();
					} else if (localPort.equalsIgnoreCase(TITLE)) {
						// not working
						title = getCharacterData(event, eventReader);
					} else if (localPort.equalsIgnoreCase(LINK)) {
						link = getCharacterData(event, eventReader);
					} else if (localPort.equalsIgnoreCase(DESC)) {
						desc = getCharacterData(event, eventReader);
					} else if (localPort.equals(PUB_DATE)) {
						pubDate = getCharacterData(event, eventReader);
					} else if (localPort.equals(ENCLOSURE)) {
						// Working
						link = getAttribute(event, eventReader, "url");
					}
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart().equalsIgnoreCase(ITEM)) {
						FeedMessage message = new FeedMessage();
						message.setDescription(desc);
						message.setLink(link);
						message.setTitle(title);

						feed.getEntries().add(message);
						event = eventReader.nextEvent();
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return feed;
	}

	@SuppressWarnings("unchecked")
	private String getAttribute(XMLEvent event, XMLEventReader reader, String attributeName) {
		String result = "";
		String name = "";
		Iterator<Attribute> iter = event.asStartElement().getAttributes();
		while (iter.hasNext()) {
			Attribute attribute = iter.next();
			name = attribute.getName().getLocalPart();
			if (name.equals(attributeName)) {
				result = attribute.getValue();
				break;
			}
		}
		return result;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	protected InputStream read() throws AppException {
		try {
			return url.openStream();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			throw new AppException(e.getMessage());
		}
	}

	@Override
	public String getFeedSource() {
		return url.toString();
	}

}