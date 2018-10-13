
package customerdeposit;

import java.math.BigDecimal;

/**
 * derived from Deposit class
 * 
 * @author Meysam Gholami
 */
public class ShortDeposit extends Deposit{
    
    /**
     * sets interest rate to 10%  and calls its parent constructor 
     * 
     * @param dd
     * @param db 
     */
    public ShortDeposit(int dd,BigDecimal db){
    super(dd,db);
        interestRate=0.10f;
    }
    
}
