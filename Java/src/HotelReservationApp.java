import java.util.*;

public class HotelReservationApp {
    Scanner sc;
    Customer customer;
    Hotel hotel;
    Reservation reservation;
    Room room;
    Reserve reserve = new Reserve();
    Device device = new Device();

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
        device.rooms.put(101, new Room(101, RoomSize.Standard, 62000));
        device.rooms.put(102, new Room(102, RoomSize.Standard, 74000));
        device.rooms.put(201, new Room(201, RoomSize.Twin, 80000));
        device.rooms.put(202, new Room(202, RoomSize.Twin, 76000));
        device.rooms.put(301, new Room(301, RoomSize.Delux, 90000));
        device.rooms.put(402, new Room(402, RoomSize.Family, 110000));
        device.rooms.put(502, new Room(502, RoomSize.Suite, 150000));

        device.hotel = new Hotel("스파르타 호텔", device.rooms, hotel.getAsset());
    }
    /* 메서드 */
    // 최초 진입점
    public void startApp() {
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
                device.display();
                break;
            case 2: // 1-2. 예약 조회 -> UUID 값을 이용함
                myReservation(reservation.getReservationNumber());
                break;
            case 3: // 1-3. 예약 취소 -> UUID 값을 이용흠
                reserve.cancelMyReservation(reservation.getReservationNumber());
                break;
            case 4: // 1-4. 모드선택으로 돌아가기
                selectModeInput();
                break;
        }
    }
    // 1-3. 개인 예약조회
    public void myReservation(UUID myUUID) {
        try {
            System.out.print("예약번호를 입력하세요 : ");
            UUID id = UUID.fromString(sc.next());   // String의 형태를 UUID로 변환
            for (UUID uuid : reserve.reservedUUIDList) {
                if (uuid.equals(id)) {  // String으로 입력된 UUID를 리스트의 id값과 비교
                    reserve.printMyReservation(id);
                    customerMode();
                }
            }
        } catch (IllegalArgumentException e) {  // 예외처리
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
                    reserve.printAllReservation();
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
}
