public class Customer {

    private String name; // 이름
    private String phoneNumber; // 전화번호
    private int money; // 소지금

    /* 생성자 */
    Customer(){}
    public Customer(String name, String phoneNumber, int money) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.money = money;
    }

    /* getter */

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString(){
        return  name+","+phoneNumber+","+money;
    }
}
