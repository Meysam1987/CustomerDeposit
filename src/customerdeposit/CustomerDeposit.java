
package customerdeposit;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * includes main method of the program
 *
 * @author Meysam Gholami
 */
public class CustomerDeposit {

    /**
     *
     * @param args
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, NoSuchFieldException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        // TODO code application logic here
        NodeList depositList = Parser.getDOM("data.xml");
        List customers = new ArrayList<>();
        String outPutMessage="";
        for (int i = 0; i < depositList.getLength(); i++) {
            Node node = depositList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                NodeList depositFieldsList = node.getChildNodes();

                boolean allowedToCreate = true;
                Object customerObject = null;
                Object depositObject = null;
                String custNumber = "";
                String depositType = "";
                BigDecimal depositBalance = BigDecimal.ZERO;
                int durationInDays = 0;
                for (int j = 0; j < depositFieldsList.getLength(); j++) {
                    if (depositFieldsList.item(j).getNodeType() == Node.ELEMENT_NODE) {

                        Node nodeField = depositFieldsList.item(j);
                        if (nodeField.getNodeName().equalsIgnoreCase("customerNumber")) {
                            custNumber = nodeField.getTextContent();
                        }
                        if (nodeField.getNodeName().equalsIgnoreCase("depositType")) {
                            depositType = nodeField.getTextContent();
                            if (!(depositType.equalsIgnoreCase("ShortTerm") || depositType.equalsIgnoreCase("Qarz") || depositType.equalsIgnoreCase("LongTerm"))) {
//                                System.out.println("Deposit type is Unknown!");
                                outPutMessage+="Deposit type is Unknown!"+"\n";
                                allowedToCreate = false;
                                break;
                            }

                        }

                        if (nodeField.getNodeName().equalsIgnoreCase("depositBalance")) {
                            depositBalance = new BigDecimal(nodeField.getTextContent());
                            if (depositBalance.compareTo(BigDecimal.ZERO) < 0) {
                            //System.out.println("Deposit Balance is negative!");
                            outPutMessage+="Deposit Balance is negative!"+"\n";
                                allowedToCreate = false;
                                break;
                            }

                        }

                        if (nodeField.getNodeName().equalsIgnoreCase("durationInDays")) {
                            durationInDays = Integer.parseInt(nodeField.getTextContent());
                            if ((durationInDays < 0 || durationInDays == 0)) {
                            // System.out.println("Duration time is inconsistent!");
                            outPutMessage+="Duration time is inconsistent!"+"\n";
                                allowedToCreate = false;
                                break;
                            }

                        }

                    }
                }
                Constructor<?> constructor = null;
                if (depositType.equalsIgnoreCase("ShortTerm")) {
                    try {
                        constructor = ShortDeposit.class.getConstructor(int.class, BigDecimal.class);
                    } catch (NoSuchMethodException | SecurityException ex) {
                        Logger.getLogger(CustomerDeposit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (depositType.equalsIgnoreCase("LongTerm")) {
                    try {
                        constructor = LongDeposit.class.getConstructor(int.class, BigDecimal.class);
                    } catch (NoSuchMethodException | SecurityException ex) {
                        Logger.getLogger(CustomerDeposit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (depositType.equalsIgnoreCase("Qarz")) {
                    try {
                        constructor = QarzDeposit.class.getConstructor(int.class, BigDecimal.class);
                    } catch (NoSuchMethodException | SecurityException ex) {
                        Logger.getLogger(CustomerDeposit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (allowedToCreate) {
                    depositObject = constructor.newInstance(durationInDays, depositBalance);
                    Method method = null;
                    try {
                        method = depositObject.getClass().getMethod("calculatePayedInterest");
                    } catch (NoSuchMethodException | SecurityException ex) {
                        Logger.getLogger(CustomerDeposit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    method.invoke(depositObject);
                    try {
                        constructor = Customer.class.getConstructor(String.class, Object.class);
                    } catch (NoSuchMethodException | SecurityException ex) {
                        Logger.getLogger(CustomerDeposit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    customerObject = constructor.newInstance(custNumber, depositObject);
                    customers.add(customerObject);
                    

                }

            }
        }
        Collections.sort(customers);//acsending
        Collections.reverse(customers);//decsending
        
        for (Object customer : customers) {
//            System.out.println(((Customer) customer).getNumber() + "#" + ((Customer) customer).getCustDeposit().getPayedInterest());
              outPutMessage+=((Customer) customer).getNumber() + "#" + ((Customer) customer).getCustDeposit().getPayedInterest()+"\n";
        }
        
        PrintOut.printToFile("out", outPutMessage);
    }

}
