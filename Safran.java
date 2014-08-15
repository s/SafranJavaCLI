/*
    SafranJavaCLI - http://safran.io
    Said Özcan - 15 Aug
    http://github.com/s
*/


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


class Safran
{
    private static final String RSS_URL = "http://www.safran.io/feed.rss";
    private static final int MAX_FEED = 10;

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    
    public static void main(String[] args) 
    {
        try 
        {
            new Safran().start();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    private void start() throws Exception
    {
        URL url = new URL(this.RSS_URL);
        URLConnection connection = url.openConnection();

        Document document = this.parseReturnedXMLDocument(connection.getInputStream());
        NodeList feedNodes = document.getElementsByTagName("item");
        this.printFeed(feedNodes);
    }

    private Document parseReturnedXMLDocument (InputStream stream) throws Exception
    {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document document = null;
        try
        {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            document = objDocumentBuilder.parse(stream);
        }
        catch(Exception ex)
        {
            throw ex;
        }       

        return document;
    }

    private void printFeed(NodeList feedNodes)
    {
        for(int i=0; i<this.MAX_FEED;i++)
        {
            String lines[] = feedNodes.item(i).getTextContent().split("\\r?\\n");
            System.out.println(this.ANSI_GREEN + lines[1]);
            System.out.println(this.ANSI_BLUE + lines[2]);
            System.out.println();
        }
    }
}