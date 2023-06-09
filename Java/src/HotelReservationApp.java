import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HotelReservationApp {
    Scanner sc;
    Customer customer;
    Hotel hotel;
    Reservation reservation;
    Room room;
    Reserve reserve;
    int reservatedCount = 0;
    public static final String FONT_GREEN = "\u001B[32m";
    public static final String FONT_RESET = "\u001B[0m";
    public static final String FONT_BLUE = "\u001B[34m";
    private LocalDate selectedDate = LocalDate.now();
    TreeMap<Integer, Room> rooms = new TreeMap<>();

    /* 생성자 */
    public HotelReservationApp(Scanner sc, Hotel hotel, Room room, Reservation reservation, Customer customer) {
        this.sc = sc;
        this.hotel = hotel;
        this.room = room;
        this.reservation = reservation;
        this.customer = customer;
    }
    /* 메서드 */
    // 최초 진입점
    public void startApp() {
        inputHotel();
        selectModeInput();
    }

    // 모드 선택
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
                display();
                break;
            case 2: // 1-2. 예약 조회 -> UUID 값을 이용함
                reserve.myReservation(reservation.getReservationNumber());
                break;
            case 3: // 1-3. 예약 취소 -> UUID 값을 이용흠
                reserve.cancelMyReservation(reservation.getReservationNumber());
                break;
            case 4: // 1-4. 모드선택으로 돌아가기
                selectModeInput();
                break;
        }
    }
    // 1-1. 객실 목록 조회
    public void display() {
        Room selectedRoom;
        while (true) {
            selectRoomList();   // 호텔 전체 객실 / 날짜가 겹칠 경우 리스트에서 제거
            selectPrint();  // 날짜에 대한 요일 표기
            System.out.println("1. 전체 객실 2. 최저가 순 3. 최고가 순 4.날짜 변경 5. 돌아 가기"); //최저가, 최고가 정렬
            System.out.println("===============================");
            System.out.print("번호를 입력하세요 : ");
            if(selectSortOption(sc.nextInt())==0) continue;

            System.out.println("1. 예약하기 2. 뒤로가기");
            if (selectReservationOption(sc.nextInt())==0) continue;

            selectedRoom = reserve.selectRoomNumber();
            if(selectedRoom==null) continue;
            else break;
        }
        //선택한 Room 객체 selectedRoom과 selectedDate 이용 예약진행
    }
    // 1-1-1. 호텔의 전체 객실
    public void selectRoomList(){
        reservatedCount=0;
        rooms = new TreeMap<>(hotel.getRooms()); // rooms에 호텔 전체객실 getRooms() 붙여넣기
        for(Reservation reservation : reserve.reservatedMap.values()){
            if (selectedDate.isEqual(reservation.getDate())){//선택날짜와 예약된 날짜가 같은경우
                rooms.remove(reservation.getRoom().getRoomNumber()); // 예약정보 중 객실번호를 가져와 rooms에서 제거
                reservatedCount++;
            }
        }
    }
    // 1-1-2. 호텔 예약 날짜의 요일 표기 및 날짜별 예약 명수 확인
    public void selectPrint() {
        System.out.println("===============================");
        System.out.println("\"스파르타 호텔에 오신 것을 환영합니다!\"");
        System.out.print("예약날짜 : "+selectedDate);
        int dayOfWeek = selectedDate.getDayOfWeek().getValue();//요일 구하기 1-월 ~ 7-일
        switch(dayOfWeek){
            case 1:System.out.println("(월)");break;
            case 2:System.out.println("(화)");break;
            case 3:System.out.println("(수)");break;
            case 4:System.out.println("(목)");break;
            case 5:System.out.println("(금)");break;
            case 6:System.out.println("(토)");break;
            case 7:System.out.println("(일)");break;
        }
        System.out.println(FONT_GREEN + "해당날짜의 투숙가능한 객실은 " + FONT_BLUE + rooms.size() + FONT_GREEN + "개 입니다." +FONT_BLUE+"(예약 "+reservatedCount+"명)" + FONT_RESET);
        System.out.println("조회할 방법을 선택하세요.");
    }
    // 1-1-3. 객실 조회 옵션 선택
    public int selectSortOption(int input) {
        sc.nextLine();
        switch (input) {
            case 1: {  //전체 객실 조회
                selectAll();
                return 1;
            }
            case 2: { // 최저가 순 조회
                ArrayList<Room> sortList = new ArrayList<>();
                sortList.addAll(rooms.values());
                Collections.sort(sortList);
                selectAll(sortList);
                return 1;
            }
            case 3: { //최고가 순 조회
                ArrayList<Room> sortList = new ArrayList<>();
                sortList.addAll(rooms.values());
                Collections.sort(sortList, Collections.reverseOrder());
                selectAll(sortList);
                return 1;
            }
            case 4: {//예약 날짜 변경
                changeselectedDate();
                break;
            }
            default: {
                System.out.println("잘못된 번호입력입니다.");
            }
        }
        return 0;
    }
    // 1-1-4. 예약 날짜 변경
    public void changeselectedDate() {
        System.out.println("예약하고 싶은 날짜를 입력하세요. (yyyy-mm-dd 형식으로 입력하세요.)");
        LocalDate date = null;
        String inputDate = sc.nextLine();

        while (true) {
            try {
                date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_DATE);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("잘못된 형식의 날짜입력입니다.");
                System.out.println("날짜를 다시 입력하세요. (yyyy-mm-dd형식으로 입력하세요.)");
                inputDate = sc.nextLine();
//            throw new RuntimeException(e);
            }
        }
        if (date.isBefore(LocalDate.now())) {//입력날짜가 오늘보다 작은 값일 경우
            System.out.println("지난 날짜는 예약할 수 없습니다.");
            System.out.println("처음 화면으로 돌아갑니다.");
        } else {
            selectedDate = date;
            System.out.println("예약날짜가 변경되었습니다. 해당 날짜의 예약가능한 객실을 다시 불러옵니다.");
        }
    }
    // 1-1-5. 객실 번호 선택
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
