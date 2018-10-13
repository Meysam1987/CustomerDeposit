
package customerdeposit;


/**
 * this class implements Comparable which is necessary for
 * compare and then sorting this objects 
 * every customer has one deposit
 * 
 * @author Meysam Gholami
 */
public class Customer implements Comparable<Customer>{

    /**
     * customer deposit
     */
    private final Deposit custDeposit;
    
    /**
     * customer's number
     */
    private final String number;
    
    /**
     * 
     * @param num
     * @param depo 
     */
    public Customer(String num,Object depo){
        number=num;
        custDeposit=(Deposit)depo;
    }

    
    /**
     * 
     * @param o is the second customer which this one will compare to
     * @return 
     */
    @Override
    public int compareTo(Customer o) {

      return this.custDeposit.getPayedInterest().compareTo(o.custDeposit.getPayedInterest());
      
    }

  

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return the custDeposit
     */
    public Deposit getCustDeposit() {
        return custDeposit;
    }
    
    
    
}
