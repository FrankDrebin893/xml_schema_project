package xmlxpath;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLXPath 
{
    public static void main(String[] args) throws IOException, XPathExpressionException, 
            ParserConfigurationException, SAXException
    {
        File myxmlfile = new File("Employees.xml");
        
        String str1 = "//Employee[name='Jarl']/role/text()";
        String str2 = "count(//Employee[role='Manager'])";

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder.parse(myxmlfile);

        XPathFactory xFactory = XPathFactory.newInstance();
        XPath xpath = xFactory.newXPath();
        XPathExpression expr1, expr2; 

        expr1 = xpath.compile(str1); 
        Object result = expr1.evaluate(doc, XPathConstants.NODESET);
        NodeList results = (NodeList) result;
        for (int i=0; i<results.getLength(); i++)
        {
          System.out.println(results.item(i).getNodeValue());
        }

        expr2 = xpath.compile(str2);
        Double number = (Double) expr2.evaluate(doc, XPathConstants.NUMBER);
        System.out.printf("Number of Managers:%2.0f\n", number);
    }
}
