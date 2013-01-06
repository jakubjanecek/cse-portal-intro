package j;

public class Transaction {

    public void begin() {
        System.out.println("tx begin");
    }

    public void commit() {
        System.out.println("commit");
    }

    public void rollback() {
        System.out.println("rollback");
    }

}
