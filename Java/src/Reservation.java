import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {

    private Room room; // 객실
    private String customerName; // 고객 이름
    private String customerPhoneNumber; // 고객 전화번호
    private LocalDateTime reservationDate; // 예약날짜
    private UUID reservationNumber; // 예약번호 (uuid)

    /* 생성자 */
    Reservation(){}
    public Reservation(Room room, String customerName, String customerPhoneNumber, LocalDateTime reservationDate, UUID reservationNumber) {
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

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public UUID getReservationNumber() {
        return reservationNumber;
    }
}
