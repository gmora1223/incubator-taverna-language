package org.apache.taverna.baclava;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

public class TestRoundTrip {
	
	private static String[] fileNames = new String[] {"example1.xml", "example2.xml"};

	@Test
	public void testRoundTrips() throws JAXBException, FileNotFoundException {
		for (String fileName : fileNames) {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(fileName).getFile());
			DataThingMapType o = BaclavaReader.readBaclava(new FileReader(file));
		
			StringWriter initialWriter = new StringWriter();
			BaclavaWriter.writeBaclava(o,  initialWriter);
			String initialString = initialWriter.toString();
			
			DataThingMapType reread = BaclavaReader.readBaclava(new StringReader(initialString));
			StringWriter rewriteWriter = new StringWriter();
			BaclavaWriter.writeBaclava(reread, rewriteWriter);
			String rewrittenString = rewriteWriter.toString();
			Assert.assertEquals(initialString, rewrittenString);
		}

	}


}
