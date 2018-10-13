
package customerdeposit;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * an abstract class which defines the main function of calculation
 * that the derived classes will just inherit and use it 
 * every deposit belongs to one customer
 *
 * @author Meysam Gholami
 */
public abstract class Deposit {
    
    private int duration;
    protected float interestRate;
    private BigDecimal depositBalance;
    private BigDecimal payedInterest;
    
    
    
    public Deposit(int durationDay,BigDecimal depositMoney){
        duration=durationDay;
        depositBalance=depositMoney;
        payedInterest=BigDecimal.ZERO;
    }
    
    /**
     * the most important method that calculates payed interest
     * 
     */
    public void calculatePayedInterest(){
//        System.out.println("");
//        payedInterest=(depositBalance.multiply(new BigDecimal(interestRate))
//                .multiply(new BigDecimal(duration))).divide(new BigDecimal(36500));
        
 payedInterest=(depositBalance.multiply(new BigDecimal(interestRate)).
         multiply(new BigDecimal(duration))).divide(new BigDecimal(36500), RoundingMode.FLOOR);

//        System.out.println("");
    }

    
    /**
     * @return the payedInterest
     */
    public BigDecimal getPayedInterest() {
        return payedInterest;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return the interestRate
     */
    public float getInterestRate() {
        return interestRate;
    }

    /**
     * @return the depositBalance
     */
    public BigDecimal getDepositBalance() {
        return depositBalance;
    }
    
}

