import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Reserve {
    Scanner sc = new Scanner(System.in);
    Customer customer = new Customer();
    Hotel hotel = new Hotel();
    Reservation reservation = new Reservation();
    Room room = new Room();
    HotelReservationApp app = new HotelReservationApp(sc, hotel, room, reservation, customer);
    /* 필드 */
    // 예약정보
    Map<UUID, Reservation> reservedRoom = new HashMap<>();
    // 예약 키 값
    LinkedList<UUID> reservedUUIDList = new LinkedList<>();
    Map<UUID, Reservation> reservatedMap = new HashMap<UUID, Reservation>(); // 예약리스트. 이미 예약된 객실을 조회목록에서 빼기 위한 테스트용
    /* 생성자 */
    public Reserve() {}
    /* getter */
    // 1개의 Reservation에서 room 정보만 호출 - uuid 필요
    public Room getRoom(UUID myUUID) {
        return reservedRoom.get(myUUID).getRoom();
    }
    // 1개의 Reservation에서 LocalDateTime만 호출 - uuid 필요
    public ZonedDateTime getZonedDateTime(UUID myUUID) {
        return reservedRoom.get(myUUID).getReservationDate();
    }

    // 1개의 Reservation에서 파싱한 날짜+시간 정보 호출 - uuid 필요
    public String getParseDateTime(UUID myUUID) {
        String parseDateTime = getZonedDateTime(myUUID).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        return parseDateTime;
    }
    public String getParseDate(UUID myUUID) {
        String parseDate = getZonedDateTime(myUUID).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return parseDate;
    }

    // 1개의 Reservation에서 고객 이름 정보만 호출 - uuid 필요
    public String getCustomerName(UUID myUUID) {
        return reservedRoom.get(myUUID).getCustomerName();
    }
    // 1개의 Reservation에서 고객 전화번호만 호출 - uuid 필요
    public String getCustomerPhoneNumber(UUID myUUID) {
        return reservedRoom.get(myUUID).getCustomerPhoneNumber();
    }
    // 전체 예약 개수를 호출
    public int getReservedCount() {
        return reservedRoom.size();
    }
    // 전체 키 값(uuid)을 호출
    public LinkedList<UUID> getAllReservedUUID() {
        return reservedUUIDList;
    }
    /* 메서드 */
    // 1-2. 객실 예약하기
    public Room selectRoomNumber() {
        int roomNumber;
        Room selectedRoom = null;
        while (true) {
//            selectAll(); // 모든 객실의 정보를 보여줌
            System.out.print("객실을 선택하세요 : ");
            roomNumber = sc.nextInt();
            sc.nextLine();
            if (app.rooms.containsKey(roomNumber)) {
                System.out.println("선택한 객실 :");
                app.rooms.get(roomNumber).showIntroduce();  // 선택한 객실의 정보
                System.out.println("이 객실의 예약을 진행하시겠습니까?");
                System.out.printf("%-10s %-10s\n", "1. 예약하기", "2. 돌아가기");
                if (sc.nextInt() == 1) {
                    System.out.print("이름을 입력하세요 : ");
                    String name = sc.next();
                    System.out.print("번호를 입력하세요 : ");
                    String phoneNumber = sc.next();
                    System.out.print("소지금을 입력하세요 : ");
                    int money = sc.nextInt();
                    Customer customer = new Customer(name, phoneNumber, money); // Customer 생성(name, phoneNumber, money 중 하나를 빼오기 위함)

                    if (customer.getMoney() >= app.rooms.get(roomNumber).getRoomPrice()) {  // Customer의 money와 선택한 객실의 roomPrice 비교
                        createReservation(app.rooms.get(roomNumber),customer);
                        System.out.println("예약이 완료되었습니다.");
                        hotel.addHotelAsset(app.rooms.get(roomNumber).getRoomPrice());
                        customer.subtractCustomerMoney(app.rooms.get(roomNumber).getRoomPrice());
                        System.out.println(name + " 님의 id는 " + createReservation(app.rooms.get(roomNumber), customer) + " 입니다.");
                        System.out.println(name + " 님의 소지금은 " + customer.getMoney() + "원 남았습니다.");
                        app.customerMode();
                    } else {
                        System.out.println("소지금이 부족합니다.");
                    }
                    selectedRoom = app.rooms.get(roomNumber);
                } else {
                    System.out.println("예약할 객실번호를 입력하세요.(돌아가기 - 0 )");
                }
            } else if (roomNumber == 0) {
                System.out.println("초기 화면으로 돌아갑니다.");
                break;
            } else {
                System.out.println("선택한 객실 번호는 없습니다. 다시 입력해주세요.");
                System.out.println("예약할 객실번호를 입력하세요.(돌아가기 - 0 )");
            }
        }
        return selectedRoom;
    }
    // 1-2-1. 예약 생성 및 리스트 저장 -> UUID 반환
    public UUID createReservation(Room room, Customer customer){

        // UUID 생성
        UUID uuid = UUID.randomUUID();
        // 예약 생성
        ZoneOffset seoul = ZoneOffset.of("+09:00");
        Reservation reservation = new Reservation(
                room, customer.getName(), customer.getPhoneNumber(),
                ZonedDateTime.now(seoul).withNano(0), uuid
        );

        // 생성한 예약을 추가
        reservedRoom.put(uuid, reservation);
        // list에 생성된 uuid 값 저장
        reservedUUIDList.add(uuid);

        // uuid 반환
        return uuid;
    }

    // 2-1. 전체 예약 목록 조회
    public Map<UUID, Reservation> viewAllReservation() {
        return reservedRoom;
    }
    // 2-2. 전체 예약 목록 조회 - 출력 형식
    public void printAllReservation() {
        if (reservedRoom.size() != 0) {
            System.out.printf("[전체 예약 정보]\n");

            for (int i = 0; i < reservedRoom.size(); i++) {
                System.out.printf("▶ %d번 예약 건\n", i + 1); // 번호
                System.out.printf("%s\t|\t", getCustomerName(reservedUUIDList.get(i)));
                System.out.printf("%s\t|\t", reservedUUIDList.get(i)); // UUID 출력
                System.out.printf("%s", getParseDate(reservedUUIDList.get(i))); // 예약 출력
                System.out.printf("\n\n");
            }
            app.hotelMode();
        } else {
            System.out.println("\n예약된 객실이 없습니다.");
            app.hotelMode();
        }
    }
    // 1-3. 개별 예약 조회 (parameter : uuid)
//    public Reservation viewMyReservation(UUID myUUID) {
//        return reservedRoom.get(myUUID);
//    }
    // 1-3. 개인 예약조회
    public void myReservation(UUID myUUID) {
        try {
            System.out.print("예약번호를 입력하세요 : ");
            UUID id = UUID.fromString(sc.next());   // String의 형태를 UUID로 변환
            for (UUID uuid : reservedUUIDList) {
                if (uuid.equals(id)) {  // String으로 입력된 UUID를 리스트의 id값과 비교
                    printMyReservation(id);
                    app.customerMode();
                }
            }
        } catch (IllegalArgumentException e) {  // 예외처리
            System.out.println("잘못된 id 입니다.");
            app.customerMode();
        }
    }
    // 1-3-1. 개인 예약 상세내역 (parameter : uuid)
    public void printMyReservation(UUID myUUID) {
        System.out.printf("[%s 님의 예약 정보]\n", getCustomerName(myUUID));

        System.out.printf("- 예약 번호: %s\n", myUUID.toString());
        System.out.printf("- 예약 날짜: %s\n\n", getParseDateTime(myUUID));

        System.out.printf("- 예약자 명: %s 님\n", getCustomerName(myUUID));
        System.out.printf("- 예약자 전화번호: %s\n\n", getCustomerPhoneNumber(myUUID));

        System.out.printf("- 예약 객실 번호: %s\n", getRoom(myUUID).getRoomNumber());
        System.out.printf("- 예약 객실 크기: %s\n", getRoom(myUUID).getRoomSize());
        System.out.printf("- 예약 객실 가격: %s 원\n", getRoom(myUUID).getRoomPrice());
    }

    // 4. 예약 취소 (parameter: uuid)
    public void cancelMyReservation(UUID myUUID) {
        try {
            System.out.print("예약번호를 입력하세요 : ");
            UUID id = UUID.fromString(sc.next());
            for (UUID uuid : reservedUUIDList) {
                if (uuid.equals(id)) {
                    System.out.println("===============================");
                    printMyReservation(id);
                    System.out.println("===============================");
                    System.out.printf("%-10s %-10s\n", "1. 취소하기", "2. 돌아가기");
                    System.out.print("번호를 입력하세요 : ");
                    int number = sc.nextInt();
                    sc.nextLine();
                    if (number == 1) {
                        // 예약 취소된 객실의 가격만큼 호텔 자산에서 감소
                        hotel.subtractHotelAsset(reservedRoom.get(id).getRoom().getRoomPrice());
                        // 예약 내역 삭제
                        reservedRoom.remove(id);
                        // 저장했던 key값도 삭제
                        reservedUUIDList.remove(id);
                        System.out.println("예약이 취소되었습니다.");
                        app.customerMode();
                    } else if (number == 2) {
                        System.out.println("메인메뉴로 돌아갑니다.");
                        app.customerMode();
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 id 입니다.");
            app.customerMode();
        }
    }
}

/*
* 1. 예약 생성 - 예약 번호 반환
* 2. 전체 예약 목록 조회
* 3. 개별 예약 목록 조회 (parameter: uuid)
* 4. 예약 취소 (parameter: uuid)
* */