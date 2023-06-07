import java.time.LocalDateTime;
import java.util.*;

public class Reserve {

    /* 필드 */
    private Reservation reservation;

    // 예약정보
    private Map<UUID, Reservation> reservedRoom = new HashMap<>();

    /* 생성자 */
    public Reserve() {}
    public Reserve(Reservation reservation) {
        this.reservation = reservation;

    }


    /* 메서드 */
    // 예약 생성 - 예약 번호 반환
    public UUID createReservation(Room room, Customer customer){

        UUID uuid = UUID.randomUUID();
        Reservation reservation = new Reservation(
                room, customer.getName(), customer.getPhoneNumber(),
                LocalDateTime.now(), uuid
        );

        reservedRoom.put(uuid, reservation);

        return uuid;
    }
}

/*
* 1. 예약 생성 - 예약 번호 반환
* 2. 전체 예약 목록 조회
* 3. 개별 예약 목록 조회 (parameter: uuid)
* 4. 예약 취소 (parameter: uuid)
* */