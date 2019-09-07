package pkj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class EarthQuakeParser {
	
	public EarthQuakeParser() {
		
	}
	
	public ArrayList<QuakeEntry> read(String source){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // to make an instance of document builder factory
		try {
			DocumentBuilder build  = factory.newDocumentBuilder(); //to make an instance of document builder
			
			Document document = null;	//to make a document to have the parse of xml on it
			
			if(source.startsWith("http")) {
				document  = build.parse(source); //it take the url as string to read xml from it
			}
			else {
				document = build.parse(new File(source));
			}
			
			NodeList nodelist = document.getDocumentElement().getChildNodes(); //return node list of all elements in the node
			
			ArrayList<QuakeEntry> list  = new ArrayList<QuakeEntry>();
			
			for(int i = 0; i<nodelist.getLength(); i++) {
				Node node = nodelist.item(i);
				
				if(node.getNodeName().equals("entry")) {
					Element elem = (Element)node;
					NodeList t1 = elem.getElementsByTagName("georss:point"); //get list of all values inside the tag
					NodeList t2 = elem.getElementsByTagName("title");
					NodeList t3 = elem.getElementsByTagName("georss:elev");
					
					double lat = 0, lon = 0,depth = 0,mag = 0;
					String title = "NO INFO";
					
					if(t1 != null) {
						String info = t1.item(0).getChildNodes().item(0).getNodeValue(); //get the value inside the georss:point tag
						String[] args = info.split(" ");
						lat = Double.parseDouble(args[0]);
						lon = Double.parseDouble(args[1]);
					}
					if(t2 != null) {
						String info = t2.item(0).getChildNodes().item(0).getNodeValue();
						String mags = info.substring(2, info.indexOf(" ",2));
						if(mags.contains("?")) {
							mag = 0;
							System.err.println("Unknown magnitude in data");
						}
						else {
							mag = Double.parseDouble(mags);
						}
						
						int sp = info.indexOf(" ",5);
						title = info.substring(sp+1);
						if(title.startsWith("-")) {
							int pos = title.indexOf(" ");
							title = title.substring(pos+1);
						}
					}
					if(t3 != null) {
						String info = t3.item(0).getChildNodes().item(0).getNodeValue();
						depth = Double.parseDouble(info);
					}
					QuakeEntry qk = new QuakeEntry(title, lat, lon, mag, depth);
					list.add(qk);
				}
			}
			return list;
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(ParserConfigurationException ex) {
			ex.printStackTrace();
		}
		catch(SAXException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public void testParser() {
		String source = "nov20quakedatasmall.atom";
		ArrayList<QuakeEntry> al = read(source);
	     Collections.sort(al);
		for(QuakeEntry qe: al) {
			System.out.println(qe);
		}
		System.out.println("# quakes = " + al.size());
	}
}
