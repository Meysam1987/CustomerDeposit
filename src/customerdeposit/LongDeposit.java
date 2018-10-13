
package customerdeposit;

import java.math.BigDecimal;

/**
 * derived from Deposit class
 * 
 * @author Meysam Gholami
 */
public class LongDeposit extends Deposit{
    
    /**
     * sets interest rate to 20%  and calls its parent constructor 
     * 
     * @param dd
     * @param db 
     */
    public LongDeposit(int dd,BigDecimal db){
    super(dd,db);
        interestRate=0.20f;
    }
}
