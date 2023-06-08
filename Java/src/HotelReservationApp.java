import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HotelReservationApp {
    Scanner sc;

    Customer customer;
    Hotel hotel;
    Reservation reservation;
    Room room;
    private LocalDate selectedDate = LocalDate.now();
    int reservatedCount = 0;
    private TreeMap<Integer, Room> rooms = new TreeMap<>();
    private Map<UUID, Reservation> reservedRoom = new HashMap<>();
    private LinkedList<UUID> reservedUUIDList = new LinkedList<>();
    public static final String FONT_GREEN = "\u001B[32m";
    public static final String FONT_RESET = "\u001B[0m";
    public static final String FONT_BLUE = "\u001B[34m";
    ZoneOffset seoul = ZoneOffset.of("+09:00");

    /* 생성자 */
    public HotelReservationApp(Scanner sc, Hotel hotel, Room room, Reservation reservation, Customer customer) {
        this.sc = sc;
        this.hotel = hotel;
        this.room = room;
        this.reservation = reservation;
        this.customer = customer;
    }

    public void inputHotel() {
        //rooms에 key=호실번호, value= room객체를 담기
        rooms.put(101, new Room(101, RoomSize.Standard, 62000));
        rooms.put(102, new Room(102, RoomSize.Standard, 74000));
        rooms.put(201, new Room(201, RoomSize.Twin, 80000));
        rooms.put(202, new Room(202, RoomSize.Twin, 76000));
        rooms.put(301, new Room(301, RoomSize.Delux, 90000));
        rooms.put(402, new Room(402, RoomSize.Family, 110000));
        rooms.put(502, new Room(502, RoomSize.Suite, 150000));

        hotel = new Hotel("스파르타 호텔", rooms, hotel.getAsset());
    }

    /* getter */
    // 1개의 Reservation에서 room 정보만 호출 - uuid 필요
    public Room getRoom(UUID myUUID) {
        return reservedRoom.get(myUUID).getRoom();
    }

    // 1개의 Reservation에서 LocalDateTime만 호출 - uuid 필요
    public ZonedDateTime getZonedDateTime(UUID myUUID) {
        return reservedRoom.get(myUUID).getReservationDate();
    }

    // 1개의 Reservation에서 파싱한 날짜:시간 정보 호출 - uuid 필요
    public String getParseDateTime(UUID myUUID) {

//        String parseDateTime = getZonedDateTime(myUUID).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); 1234-56-78 12:34:56 형태변환
        String parseDateTime = getZonedDateTime(myUUID).now(seoul).withNano(0).toString(); // 2016-10-27T17:13:40+00:00 형식

        return parseDateTime;
    }

    // 1개의 Reservation에서 파싱한 날짜 정보 호출 - uuid 필요
    public String getParseDate(UUID myUUID) {

        String parseDate = getZonedDateTime(myUUID).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 1234-56-78 형태변환
//        String parseDate = getZonedDateTime(myUUID).now(seoul).withNano(0).toString(); // 2016-10-27T17:13:40+00:00 형식
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

    /* 메서드 */
    // 최초 진입점
    public void startApp() {
        inputHotel();
        selectModeInput();
    }

    // 모드 선택
    // 고객, 호텔
    public void selectModeInput() {
        System.out.print("\n");
        System.out.println("==========스파르타 호텔==========");
        System.out.printf("%-10s %-10s %s\n", "1. 고객", "2. 호텔", "3. 종료");
        System.out.println("===============================");
        System.out.print("번호를 입력하세요 : ");
        int selectMode = sc.nextInt();
        sc.nextLine();
        switch (selectMode) {
            case 1: // 고객모드
                customerMode();
                break;
            case 2: // 호텔모드
                hotelMode();
                break;
            case 3: // 종료
                System.out.println("\n프로그램을 종료합니다.");
                break;
        }
    }

    // 1. 고객 모드
    public void customerMode() {
        System.out.print("\n");
        System.out.println("===============================");
        System.out.println("스파르타 호텔에 오신것을 환영합니다.");
        System.out.println("1. 객실 조회");
        System.out.println("2. 예약 조회");
        System.out.println("3. 예약 취소");
        System.out.println("4. 돌아 가기");
        System.out.println("===============================");
        System.out.print("번호를 입력하세요 : ");
        int selectMenu = sc.nextInt();
        sc.nextLine();
        switch (selectMenu) {
            case 1: // 1-1. 객실 조회
                showRoom();
                break;
            case 2: // 1-2. 예약 조회 -> UUID 값을 이용함
                myReservation(reservation.getReservationNumber());
                break;
            case 3: // 1-3. 예약 취소 -> UUID 값을 이용흠
                cancelMyReservation(reservation.getReservationNumber());
                break;
            case 4: // 1-4. 모드선택으로 돌아가기
                selectModeInput();
                break;
        }
    }

    // 1-1. 객실 목록 조회
    public void showRoom() {
        Room selectedRoom;
        while (true) {
            selectRoomList();   // 호텔 전체 객실 / 날짜가 겹칠 경우 리스트에서 제거
            selectPrint();  // 날짜에 대한 요일 표기
            System.out.println("1. 전체 객실 2. 최저가 순 3. 최고가 순 4. 날짜 변경 5. 돌아 가기"); //최저가, 최고가 정렬
            System.out.println("===============================");
            System.out.print("번호를 입력하세요 : ");
            if (selectSortOption(sc.nextInt()) == 0) continue;
            System.out.println("1. 예약하기 2. 뒤로가기");
            if (selectReservationOption(sc.nextInt()) == 0) continue;
            sc.nextLine();
            selectedRoom = selectRoomNumber();  // 1-2. 예약하기로 이동
            if (selectedRoom == null);
            else break;
        }
    }
    // 1-1-1. 호텔의 전체 객실
    public void selectRoomList() {
        reservatedCount = 0;
        rooms = new TreeMap<>(hotel.getRooms()); // rooms에 호텔 전체객실 getRooms() 붙여넣기
        for (Reservation reservation : reservedRoom.values()) {
            if (selectedDate.isEqual(reservation.getDate())) {//선택날짜와 예약된 날짜가 같은경우
                rooms.remove(reservation.getRoom().getRoomNumber()); // 예약정보 중 객실번호를 가져와 rooms에서 제거
                reservatedCount++;
            }
        }
    }
    // 1-1-2. 호텔 예약 날짜의 요일 표기
    public void selectPrint() {
        System.out.println("===============================");
        System.out.println("\"스파르타 호텔에 오신 것을 환영합니다!\"");
        System.out.print("예약날짜 : " + selectedDate);
        int dayOfWeek = selectedDate.getDayOfWeek().getValue();//요일 구하기 1-월 ~ 7-일
        switch (dayOfWeek) {
            case 1:
                System.out.println("(월)");
                break;
            case 2:
                System.out.println("(화)");
                break;
            case 3:
                System.out.println("(수)");
                break;
            case 4:
                System.out.println("(목)");
                break;
            case 5:
                System.out.println("(금)");
                break;
            case 6:
                System.out.println("(토)");
                break;
            case 7:
                System.out.println("(일)");
                break;
        }
        System.out.println(FONT_GREEN + "해당날짜의 투숙가능한 객실은 " + FONT_BLUE + rooms.size() + FONT_GREEN + "개 입니다." + FONT_BLUE + "(예약 " + reservatedCount + "명)" + FONT_RESET);
        System.out.println("조회할 방법을 선택하세요.");
    }
    // 1-1-3. 객실 조회 옵션 선택
    public int selectSortOption(int input) {
        sc.nextLine(); // 입력버퍼
        switch (input) {
            case 1: {  //전체 객실 조회
                selectRoomNumber();
                break;
            }
            case 2: { // 최저가 순 조회
                ArrayList<Room> sortList = new ArrayList<>();
                sortList.addAll(rooms.values());
                Collections.sort(sortList);
                selectAll(sortList);
                break;
            }
            case 3: { //최고가 순 조회
                ArrayList<Room> sortList = new ArrayList<>();
                sortList.addAll(rooms.values());
                Collections.sort(sortList, Collections.reverseOrder());
                selectAll(sortList);
                break;
            }
            case 4: {//예약 날짜 변경
                changeselectedDate();
                break;
            }
            case 5: {
                selectModeInput();
                break;
            }
            default: {
                System.out.println("잘못된 번호입력입니다.");
            }
        }
        return 0;
    }
    public void selectAll() {
        System.out.println(" 객실 정보 :");
        for (Room room : rooms.values()) {
            room.showIntroduce();
        }
    }

    public void selectAll(ArrayList<Room> rooms) { //정렬한 객실정보 보여주기용
        System.out.println(" 객실 정보 :");
        for (Room room : rooms) {
            room.showIntroduce();
        }
    }

    public void changeselectedDate() {
        System.out.print("예약하고 싶은 날짜를 입력하세요. (yyyy-mm-dd 형식) : ");
        LocalDate date = null;
        String inputDate = sc.next();
        while (true) {
            try {
                date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_DATE);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("잘못된 형식의 날짜입력입니다.");
                System.out.println("날짜를 다시 입력하세요. (yyyy-mm-dd형식으로 입력하세요.)");
                inputDate = sc.nextLine();
            }
        }
        if (date.isBefore(LocalDate.now())) {//입력날짜가 오늘보다 작은 값일 경우
            System.out.println("지난 날짜는 예약할 수 없습니다.");
            System.out.println("처음 화면으로 돌아갑니다.");
            customerMode();
        } else {
            selectedDate = date;
            System.out.println("예약날짜가 변경되었습니다. 해당 날짜의 예약가능한 객실을 다시 불러옵니다.");
        }
    }

    public int selectReservationOption(int input) {
        switch (input) {
            case 1: {
                System.out.println("예약할 객실번호를 입력하세요.(돌아가기 - 0 )");
                return 1;
            }
            case 2: {
                System.out.println("초기 화면으로 돌아갑니다.");
                break;
            }
            default: {
                System.out.println("잘못된 입력입니다.");
                break;
            }
        }
        return 0;
    }

    // 1-2-1. 이름, 번호, 소지금 입력
    public String enterNameInput() {
        return sc.next();
    }

    public String enterPhoneNumberInput() {
        return sc.next();
    }

    public int enterMoneyInput() {
        return sc.nextInt();
    }

    // 1-2. 객실 예약하기
    public Room selectRoomNumber() {
        int roomNumber;
        Room selectedRoom = null;
        while (true) {
            selectAll(); // 모든 객실의 정보를 보여줌
            System.out.print("객실을 선택하세요 : ");
            roomNumber = sc.nextInt();
            sc.nextLine();
            if (rooms.containsKey(roomNumber)) {
                System.out.println("선택한 객실 :");
                rooms.get(roomNumber).showIntroduce();  // 선택한 객실의 정보
                System.out.println("이 객실의 예약을 진행하시겠습니까?");
                System.out.printf("%-10s %-10s\n", "1. 예약하기", "2. 돌아가기");
                if (sc.nextInt() == 1) {
                    System.out.print("이름을 입력하세요 : ");
                    String name = enterNameInput();
                    System.out.print("번호를 입력하세요 : ");
                    String phoneNumber = enterPhoneNumberInput();
                    System.out.print("소지금을 입력하세요 : ");
                    int money = enterMoneyInput();
                    Customer customer = new Customer(name, phoneNumber, money); // Customer 생성(name, phoneNumber, money 중 하나를 빼오기 위함)

                    if (customer.getMoney() >= rooms.get(roomNumber).getRoomPrice()) {  // Customer의 money와 선택한 객실의 roomPrice 비교
                        System.out.println("예약이 완료되었습니다.");
                        hotel.addHotelAsset(rooms.get(roomNumber).getRoomPrice());
                        customer.subtractCustomerMoney(rooms.get(roomNumber).getRoomPrice());
                        System.out.println(name + " 님의 id는 " + createReservation(rooms.get(roomNumber), customer) + " 입니다.");
                        System.out.println(name + " 님의 소지금은 " + customer.getMoney() + "원 남았습니다.");
                        customerMode();
                    } else {
                        System.out.println("소지금이 부족합니다.");
                    }
                    selectedRoom = rooms.get(roomNumber);
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

    // 1-2-1. 예약 생성 및 리스트 저장
    public UUID createReservation(Room room, Customer customer) {

        // UUID 생성
        UUID uuid = UUID.randomUUID();
        // 예약 생성
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

    // 1-3. 개인 예약조회
    public void myReservation(UUID myUUID) {
        try {
            System.out.print("예약번호를 입력하세요 : ");
            UUID id = UUID.fromString(sc.next());   // String의 형태를 UUID로 변환
            for (UUID uuid : reservedUUIDList) {
                if (uuid.equals(id)) {  // String으로 입력된 UUID를 리스트의 id값과 비교
                    printMyReservation(id);
                    customerMode();
                }
            }
        } catch (IllegalArgumentException e) {  // 예외처리
            System.out.println("잘못된 id 입니다.");
            customerMode();
        }
    }
    // 1-3-1. 개인 예약 상세내역
    public void printMyReservation(UUID myUUID) {

        System.out.printf("[%s 님의 예약 정보]\n", getCustomerName(myUUID));

        System.out.printf("- 예약 번호: %s\n", myUUID.toString());
        System.out.printf("- 예약 날짜: %s\n\n", getParseDateTime(myUUID));
        // System.out.printf("- 예약 날짜: %s\n\n", getParseDate(myUUID)); -> 날짜만 출력

        System.out.printf("- 예약자 명: %s 님\n", getCustomerName(myUUID));
        System.out.printf("- 예약자 전화번호: %s\n\n", getCustomerPhoneNumber(myUUID));

        System.out.printf("- 예약 객실 번호: %s\n", getRoom(myUUID).getRoomNumber());
        System.out.printf("- 예약 객실 크기: %s\n", getRoom(myUUID).getRoomSize());
        System.out.printf("- 예약 객실 가격: %s 원\n", getRoom(myUUID).getRoomPrice());
    }
    // 1-4. 객실 예약취소
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
                        customerMode();
                    } else if (number == 2) {
                        System.out.println("메인메뉴로 돌아갑니다.");
                        customerMode();
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 id 입니다.");
            customerMode();
        }
    }

    // 2. 호텔 모드
    public void hotelMode() {
        System.out.print("\n");
        System.out.print("관리자 비밀번호를 입력하세요 : ");
        int pw = sc.nextInt();
        sc.nextLine();
        if (pw == hotel.getPassword()) {
            // 비밀번호가 같다면 관리자모드로 진입
            System.out.print("\n");
            System.out.println("===============================");
            System.out.printf("%-8s %-8s %s\n", "1. 보유자산", "2. 예약목록", "3. 돌아가기");
            System.out.println("===============================");
            System.out.print("번호를 입력하세요 : ");
            int selectMenu = sc.nextInt();
            sc.nextLine();
            switch (selectMenu) {
                case 1: // 보유자산 조회
                    hotelAsset();
                    break;
                case 2: // 예약목록 조회
                    viewReservedRoom();
                    break;
                case 3: // 돌아가기
                    selectModeInput();
                    break;
            }
        } else {
            System.out.println("비밀번호가 틀렸습니다.");
            selectModeInput();
        }
    }

    // 2-1 보유자산 조회
    public void hotelAsset() {
        System.out.print("\n");
        System.out.println("===============================");
        System.out.println("호텔의 총 자산은 : " + hotel.getAsset() + " 원 입니다.");
        System.out.println("===============================");
        hotelMode();
    }

    // 2-2 예약목록 조회
    public void viewReservedRoom() {
        if (reservedRoom.size() != 0) {
            System.out.printf("[전체 예약 정보]\n");

            for (int i = 0; i < reservedRoom.size(); i++) {
                System.out.printf("▶ %d번 예약 건\n", i + 1); // 번호
                System.out.printf("%s\t|\t", getCustomerName(reservedUUIDList.get(i)));
                System.out.printf("%s\t|\t", reservedUUIDList.get(i)); // UUID 출력
                System.out.printf("%s", getParseDate(reservedUUIDList.get(i))); // 예약 출력
                System.out.printf("\n\n");
            }
            hotelMode();
        } else {
            System.out.println("\n예약된 객실이 없습니다.");
            hotelMode();
        }
    }
}
