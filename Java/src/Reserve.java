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

    /* getter */
    // 1개의 Reservation에서 room 정보만 호출 - uuid 필요
    public Room getRoom(UUID myUUID) {
        return reservedRoom.get(myUUID).getRoom();
    }
    // 1개의 Reservation에서 room 정보만 호출 - uuid 필요

    /* 메서드 */
    // 1. 예약 생성 - 예약 번호 반환
    public UUID createReservation(Room room, Customer customer){

        // UUID 생성
        UUID uuid = UUID.randomUUID();
        // 예약 생성
        Reservation reservation = new Reservation(
                room, customer.getName(), customer.getPhoneNumber(),
                LocalDateTime.now(), uuid
        );
        
        // 생성한 예약을 추가
        reservedRoom.put(uuid, reservation);

        // uuid 반환
        return uuid;
    }

    // 2. 전체 예약 목록 조회
    public Map<UUID, Reservation> viewAllReservation() {
        return reservedRoom;
    }
    
    // 3. 개별 예약 조회 (parameter: uuid)
    public Reservation viewMyReservation(UUID myUUID) {
        return reservedRoom.get(myUUID);
    }

    // 4. 예약 취소 (parameter: uuid)
    public void cancelMyReservation(UUID myUUID) {
        reservedRoom.remove(myUUID);
    }
}

/*
* 1. 예약 생성 - 예약 번호 반환
* 2. 전체 예약 목록 조회
* 3. 개별 예약 목록 조회 (parameter: uuid)
* 4. 예약 취소 (parameter: uuid)
* */