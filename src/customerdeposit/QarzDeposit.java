
package customerdeposit;

import java.math.BigDecimal;

/**
 * derived from Deposit class
 * 
 * @author Meysam Gholami
 */
public class QarzDeposit extends Deposit{
    
    /**
     * sets interest rate to 0%  and calls its parent constructor
     * 
     * @param dd
     * @param db 
     */
    public QarzDeposit(int dd,BigDecimal db){
    super(dd,db);
        interestRate=0;
    }
}
