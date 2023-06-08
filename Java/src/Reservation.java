import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {

    private Room room; // 객실
    private String customerName; // 고객 이름
    private String customerPhoneNumber; // 고객 전화번호
    private LocalDate reservationDate; // 예약날짜
    private UUID reservationNumber; // 예약번호 (uuid)

    /* 생성자 */
    Reservation(){}
    public Reservation(Room room, String customerName, String customerPhoneNumber, LocalDate reservationDate, UUID reservationNumber) {
        this.room = room;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.reservationDate = reservationDate;
        this.reservationNumber = reservationNumber;
    }

    /* getter */
    public Room getRoom() {
        return room;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public UUID getReservationNumber() {
        return reservationNumber;
    }

    public LocalDate setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
        return reservationDate;
    }

    public UUID setReservationNumber(UUID reservationNumber) {
        this.reservationNumber = reservationNumber;
        return reservationNumber;
    }

    @Override
    public String toString(){
        return  "객실("+room+")"+",\n"+"고객이름:"+customerName+","+"전화번호:"+customerPhoneNumber+","+"예약날짜:"+reservationDate+","+"예약번호:"+reservationNumber;
    }
}
