
package datahandling;
/**
 *
 * @author Dora
 */
 
import java.io.File;
import java.io.IOException;
import java.util.List;
 
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
 
import datamodels.PurchaseOrder;

public class XMLSAXTest 
{
    public static void main(String[] args) 
    {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try 
        {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLSAXHandler handler = new XMLSAXHandler();
            
            File myfile = new File("src/XML/productOrder.xml");
            saxParser.parse(myfile, handler);
            
            //Get Employees list
            PurchaseOrder po = handler.getPurchaseOrder();
            
            //print employee information
                System.out.println(po);
        }
        catch (ParserConfigurationException | SAXException | IOException e) 
        {
            e.printStackTrace();
        }
    }
}