package com.ae.feeds.reader.tests;

import com.ae.feeds.reader.utils.FeedUtils;

public class UtilsTest {
	public static void main(String[] args) {

		testGetFileExtension();
		testReadableFileSize();
	}

	public static void testGetFileExtension() {
		System.out.print(" testGetFileExtension ");
		String realExtension = "jpg";
		String fileName = "d:\\wallpapers\\bing\\image_1290." + realExtension;
		String result = FeedUtils.getFileExtension(fileName);
		if (realExtension.equals(result)) {
			System.out.println("passed");
		} else {
			System.out.println("failed");
		}
	}

	public static void testReadableFileSize() {
		System.out.print(" testReadableFileSize ");
		long fileSize = 1024 * 1024 * 1024;
		String result = FeedUtils.readableFileSize(fileSize);
		if (result.equals("1 GiB")) {
			System.out.println("passed");
		} else {
			System.out.println("failed");
		}
	}
}
