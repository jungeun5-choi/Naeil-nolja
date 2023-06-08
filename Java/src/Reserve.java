//import java.time.LocalDate;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//public class Reserve {
//
//    /* 필드 */
//    private Reservation reservation;
//
//    // 예약정보
//    private Map<UUID, Reservation> reservedRoom = new HashMap<>();
//    // 예약 키 값
//    private LinkedList<UUID> reservedUUIDList = new LinkedList<>();
//
//    /* 생성자 */
//    public Reserve() {}
//    /*
//    public Reserve(Reservation reservation) {
//        this.reservation = reservation;
//    }*/
//
//    /* getter */
//    // 1개의 Reservation에서 room 정보만 호출 - uuid 필요
//    public Room getRoom(UUID myUUID) {
//        return reservedRoom.get(myUUID).getRoom();
//    }
//    // 1개의 Reservation에서 LocalDateTime만 호출 - uuid 필요
//    public ZonedDateTime getZonedDateTime(UUID myUUID) {
//        return reservedRoom.get(myUUID).getReservationDate();
//    }
//
//    // 1개의 Reservation에서 파싱한 날짜+시간 정보 호출 - uuid 필요
//    public String getParseDateTime(UUID myUUID) {
//
//        String parseDateTime = getZonedDateTime(myUUID).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
//
//        return parseDateTime;
//    }
//
//
//    public String getParseDate(UUID myUUID) {
//
//        String parseDate = getZonedDateTime(myUUID).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        return parseDate;
//    }
//
//    // 1개의 Reservation에서 고객 이름 정보만 호출 - uuid 필요
//    public String getCustomerName(UUID myUUID) {
//        return reservedRoom.get(myUUID).getCustomerName();
//    }
//    // 1개의 Reservation에서 고객 전화번호만 호출 - uuid 필요
//    public String getCustomerPhoneNumber(UUID myUUID) {
//        return reservedRoom.get(myUUID).getCustomerPhoneNumber();
//    }
//
//    // 전체 예약 개수를 호출
//    public int getReservedCount() {
//        return reservedRoom.size();
//    }
//    // 전체 키 값(uuid)을 호출
//    public LinkedList<UUID> getAllReservedUUID() {
//        return reservedUUIDList;
//    }
//
//    /* 메서드 */
//    // 1. 예약 생성 - 예약 번호 반환
//    public UUID createReservation(Room room, Customer customer){
//
//        // UUID 생성
//        UUID uuid = UUID.randomUUID();
//        // 예약 생성
//        ZoneOffset seoul = ZoneOffset.of("+09:00");
//        Reservation reservation = new Reservation(
//                room, customer.getName(), customer.getPhoneNumber(),
//                LocalDate.now(), uuid
//        );
//
//        // 생성한 예약을 추가
//        reservedRoom.put(uuid, reservation);
//        // list에 생성된 uuid 값 저장
//        reservedUUIDList.add(uuid);
//
//        // uuid 반환
//        return uuid;
//    }
//
//    // 2-1. 전체 예약 목록 조회
//    public Map<UUID, Reservation> viewAllReservation() {
//        return reservedRoom;
//    }
//    // 2-2. 전체 예약 목록 조회 - 출력 형식
//    public void printAllReservation() {
//        System.out.printf("[전체 예약 정보]\n");
//
//        for(int i = 0; i < reservedRoom.size(); i++) {
//            System.out.printf("▶ %d번 예약 건\n", i+1); // 번호
//            System.out.printf("%s\t|\t", getCustomerName(reservedUUIDList.get(i)));
//            System.out.printf("%s\t|\t", reservedUUIDList.get(i)); // UUID 출력
//            System.out.printf("%s", getParseDate(reservedUUIDList.get(i))); // 예약 출력
//            System.out.printf("\n\n");
//        }
//    }
//
//
//    // 3-1. 개별 예약 조회 (parameter: uuid)
//    public Reservation viewMyReservation(UUID myUUID) {
//        return reservedRoom.get(myUUID);
//    }
//    // 3-2. 개별 예약 조회 - 출력 형식
//    public void printMyReservation(UUID myUUID) {
//
//        System.out.printf("[%s 님의 예약 정보]\n", getCustomerName(myUUID));
//
//        System.out.printf("- 예약 번호: %s\n", myUUID.toString());
//        System.out.printf("- 예약 날짜: %s\n\n", getParseDateTime(myUUID));
//
//        System.out.printf("- 예약자 명: %s 님\n", getCustomerName(myUUID));
//        System.out.printf("- 예약자 전화번호: %s\n\n", getCustomerPhoneNumber(myUUID));
//
//        System.out.printf("- 예약 객실 번호: %s\n", getRoom(myUUID).getRoomNumber());
//        System.out.printf("- 예약 객실 크기: %s\n", getRoom(myUUID).getRoomSize());
//        System.out.printf("- 예약 객실 가격: %s 원\n", getRoom(myUUID).getRoomPrice());
//
//        System.out.printf("\n");
//    }
//
//    // 4. 예약 취소 (parameter: uuid)
//    public void cancelMyReservation(UUID myUUID) {
//        // 예약 내역 삭제
//        reservedRoom.remove(myUUID);
//        // 저장했던 key값도 삭제
//        reservedUUIDList.remove(myUUID);
//    }
//}
//
///*
//* 1. 예약 생성 - 예약 번호 반환
//* 2. 전체 예약 목록 조회
//* 3. 개별 예약 목록 조회 (parameter: uuid)
//* 4. 예약 취소 (parameter: uuid)
//* */