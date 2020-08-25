package main.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * @author fissban
 */
public class XMLDocumentFactory
{
	private DocumentBuilder builder;

	public XMLDocumentFactory()
	{
		try
		{
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setIgnoringComments(true);

			builder = factory.newDocumentBuilder();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public final Document loadDocument(final String filePath) throws Exception
	{
		return loadDocument(new File(filePath));
	}

	public final Document loadDocument(final File file) throws Exception
	{
		if (!file.exists() || !file.isFile())
		{
			throw new Exception("File: " + file.getAbsolutePath() + " doesn't exist and/or is not a file.");
		}

		return builder.parse(file);
	}

	public final Document newDocument()
	{
		return builder.newDocument();
	}

	private static class SingletonHolder
	{
		protected static final XMLDocumentFactory INSTANCE = new XMLDocumentFactory();
	}

	public static final XMLDocumentFactory getInstance()
	{
		return SingletonHolder.INSTANCE;
	}
}
