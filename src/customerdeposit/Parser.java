
package customerdeposit;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * this class just made for parsing the xml docs
 * 
 * @author Meysam Gholami
 */
public class Parser {
    
    /**
     * 
     * @param path of the xml file
     * @return NodeList of deposit nodes 
     */
    public static NodeList  getDOM(String path) {
        NodeList depostsList=null;
        DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(path);
            depostsList=document.getElementsByTagName("deposit");
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return depostsList;
    }
    
}
