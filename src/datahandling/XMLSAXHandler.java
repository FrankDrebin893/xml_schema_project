/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datahandling;

/**
 *
 * @author Rasmus
 */
  import java.util.ArrayList;
    import java.util.List;

    import org.xml.sax.Attributes;
    import org.xml.sax.SAXException;
    import org.xml.sax.helpers.DefaultHandler;

    import datamodels.*;

// Create a subclass of DefaultHandler and override certain inherited methods in this subclass
public class XMLSAXHandler extends DefaultHandler 
{
    // One employee
    private PurchaseOrder purchaseOrder = null;
    private ShippingInformation shippingInformation = null;
    private Order order = null;
    private Customer customer = null;
    private Address address = null;
    private OrderItem orderItem = null;
    
    boolean bShippingInformation = false;
    boolean bOrder = false;
    
    boolean bFirstName = false;
    boolean bLastName = false;
    
    boolean bStreet = false;
    boolean bPostalCode = false;
    boolean bCity = false;
    boolean bCountry = false;
    
    boolean bProductName = false;
    boolean bPrice = false;
    
    boolean bTotalPrice = false;
    
    
    private List<OrderItem> orderItemList = null;

    
    public List<OrderItem> getPurchaseOrderList() 
    {
        return orderItemList;
    }

    @Override
    public void startElement(String uri, String localName, String nodeName, Attributes attributes) throws SAXException 
    {
        if (nodeName.equalsIgnoreCase("PurchaseOrder")) 
        {
            //create a new Employee and put it in Map
            purchaseOrder = new PurchaseOrder();


        // initialize list
        if (orderItemList == null)
                orderItemList = new ArrayList<>();
        } 

        // see what you have read
        if (nodeName.equalsIgnoreCase("ShippingInformation")) 
            bShippingInformation = true;
        else if (nodeName.equalsIgnoreCase("Order")) 
            bOrder = true;
                else if (nodeName.equalsIgnoreCase("Street")) 
            bStreet = true;
                else if (nodeName.equalsIgnoreCase("PostalCode")) 
            bPostalCode = true;
                else if (nodeName.equalsIgnoreCase("City")) 
            bCity = true;
                else if (nodeName.equalsIgnoreCase("Country")) 
            bCountry = true;
                else if (nodeName.equalsIgnoreCase("FirstName")) 
            bFirstName = true;
                else if (nodeName.equalsIgnoreCase("LastName")) 
            bLastName = true;
                else if (nodeName.equalsIgnoreCase("Product")) 
            bProductName = true;
                else if (nodeName.equalsIgnoreCase("Price")) 
            bPrice = true;
                else if (nodeName.equalsIgnoreCase("TotalPrice")) 
            bTotalPrice = true;
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException 
    {
        if (bProductName) 
        {
            orderItem.setProductName(new String(ch, start, length));
            bProductName = false;
        } 
        if (bPrice)
        {
            orderItem.setPrice(Integer.parseInt(new String(ch, start, length)));
        }
        else if (bStreet) 
        {
            address.setStreet(new String(ch, start, length));
            bStreet = false;
        } 
        else if (bPostalCode) 
        {
            address.setPostalCode(new String(ch, start, length));
            bCity = false;
        }
        else if (bCity) 
        {
            address.setCity(new String(ch, start, length));
            bCity = false;
        }
        else if (bCountry) 
        {
            address.setCountry(new String(ch, start, length));
            bCountry = false;
        }
        else if (bFirstName) {
            customer.setFirstName(new String(ch, start, length));
            bFirstName = false;
        }
        else if (bLastName) {
            customer.setLastName(new String(ch, start, length));
            bLastName = false;
        }
        else if (bTotalPrice) {
            order.setTotalPrice(Integer.parseInt(new String(ch, start, length)));
            bCountry = false;
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String nodeName) throws SAXException 
    {
        if (nodeName.equalsIgnoreCase("OrderItem")) 
        {
            //add Employee object to list
            orderItemList.add(orderItem);
        }
    }
}