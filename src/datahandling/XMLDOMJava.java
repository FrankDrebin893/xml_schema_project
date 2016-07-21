
package xmldomjava;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLDOMJava 
{
    
    public static void main(String[] args) 
    {
        try 
        {
            File myxmlfile = new File("employees.xml");
            
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            
            Document doc = builder.parse(myxmlfile);           
            
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            if (doc.hasChildNodes()) 
            {
                printNode(doc.getChildNodes());
            }

        } catch (Exception e) 
        {
          System.out.println(e.getMessage());
        }
    }
 
private static void printNode(NodeList nodeList) 
{
    for (int i = 0; i < nodeList.getLength(); i++) 
    { 
        Node mynode = nodeList.item(i);
        if (mynode.getNodeType() == Node.ELEMENT_NODE) 
        {
            System.out.print("\n");
            System.out.println("Node " + mynode.getNodeName() + " opened");
            System.out.print("Node " + mynode.getNodeName() + " value:");
            System.out.println(" { " + mynode.getTextContent() +  " }");

            if (mynode.hasAttributes()) 
            {
                  NamedNodeMap nodeMap = mynode.getAttributes();

                  for (int j = 0; j < nodeMap.getLength(); j++) 
                  {
                        Node attnode = nodeMap.item(j);
                        System.out.print("Node " + mynode.getNodeName() + " attributes: ");
                        System.out.print(" [attr name = " + attnode.getNodeName());
                        System.out.print(", attr value = " + attnode.getNodeValue() + "]\n");
                  }
            }

            if (mynode.hasChildNodes()) 
            {
                printNode(mynode.getChildNodes());
            }
            System.out.println("Node " + mynode.getNodeName() + " closed");
        }
       
    }

}

}
