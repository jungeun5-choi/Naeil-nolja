import java.time.LocalDateTime;
import java.util.*;

public class HotelReservationApp {
    Scanner sc;
    Customer customer;
    Hotel hotel;
    Reservation reservation;
    Room room;

    private TreeMap<Integer,Room> rooms = new TreeMap<>();
    private Map<UUID, Reservation> reservedRoom = new HashMap<>();
    private LinkedList<UUID> reservedUUIDList = new LinkedList<>();
    public static final String FONT_GREEN = "\u001B[32m";
    public static final String FONT_RESET = "\u001B[0m";
    public static final String FONT_BLUE = "\u001B[34m";

    public HotelReservationApp(Scanner sc, Hotel hotel, Room room, Reservation reservation, Customer customer) {
        this.sc = sc;
        this.hotel = hotel;
        this.room = room;
        this.reservation = reservation;
        this.customer = customer;
    }
    public void inputHotel() {
        //rooms에 key=호실번호, value= room객체를 담기
        rooms.put(101,new Room(101, RoomSize.Standard, 62000));
        rooms.put(102,new Room(102, RoomSize.Standard, 74000));
        rooms.put(201,new Room(201, RoomSize.Twin, 80000));
        rooms.put(202,new Room(202, RoomSize.Twin, 76000));
        rooms.put(301,new Room(301, RoomSize.Delux, 90000));
        rooms.put(402,new Room(402, RoomSize.Family, 110000));
        rooms.put(502,new Room(502, RoomSize.Suite, 150000));

        hotel = new Hotel("스파르타 호텔", rooms, hotel.getAsset());
    }
    /* 메서드 */

    // 최초 진입점
    public void startApp(){
        selectModeInput();
    }

    // 모드 선택
    // 고객, 호텔
    public void selectModeInput(){
        System.out.print("\n");
        System.out.println("==========스파르타 호텔==========");
        System.out.printf("%-10s %-10s %s\n","1. 고객", "2. 호텔","3. 종료");
        System.out.println("===============================");
        System.out.print("번호를 입력 하시오 : ");
        int selectMode = sc.nextInt();
        switch(selectMode){
            case 1 : // 고객모드
                customerMode();
                break;
            case 2 : // 호텔모드
                hotelMode();
                break;
            case 3 : // 종료
                System.out.println("프로그램을 종료합니다.");
                break;
        }
    }

    // 1. 고객 모드
    public void customerMode(){
        System.out.print("\n");
        System.out.println("===============================");
        System.out.println("스파르타 호텔에 오신것을 환영합니다.");
        System.out.println("1. 객실 조회");
        System.out.println("2. 예약 하기");
        System.out.println("3. 예약 조회");
        System.out.println("4. 예약 취소");
        System.out.println("5. 돌아 가기");
        System.out.println("===============================");
        System.out.print("번호를 입력 하시오 : ");
        int selectMenu = sc.nextInt();
        switch(selectMenu){
            case 1 : // 객실 조회
                showRoom();
                break;
            case 2 : // 예약 하기
                reserveRoom();
                break;
            case 3 : // 예약 조회
                //uuid 입력 후 맞으면
//                reserve.viewMyReservation(new UUID());
                break;
            case 4 : // 예약 취소

                break;
            case 5 : // 모드선택으로 돌아가기
                selectModeInput();
                break;
        }
    }

    // 1-1. 객실 목록 조회
    public void showRoom(){
        inputHotel();
        Scanner sc = new Scanner(System.in);
        System.out.println("===============================");
        System.out.println("\"스파르타 호텔에 오신 것을 환영합니다!\"");
        System.out.println(FONT_GREEN+"현재 투숙가능한 객실은 "+FONT_BLUE+rooms.size()+FONT_GREEN+"개 입니다."+FONT_RESET);
        System.out.println("조회할 방법을 선택하세요.");
        System.out.println("1. 전체 객실 2. 최저가 순 3. 최고가 순 4. 돌아가기"); //최저가, 최고가 정렬 현재 미구현
        System.out.println("===============================");
        System.out.print("번호를 입력 하시오 : ");
        int input = sc.nextInt();
        sc.nextLine();
        switch (input) {
            case 1: {
                selectAll();
                checkReserve();
            }
            case 2: {
//                    hotel.sortCheap(); 리스트에서
//                selectAll();
                checkReserve();
                break;
            }
            case 3: {
//                    hotel.sortExpansive();
//                selectAll();
                checkReserve();
                break;
            }
            case 4: {
                customerMode();
                break;
            }
            default:{
                System.out.println("잘못된 번호입력입니다.");
                selectAll();
                break;
            }
        }
    }
    public void selectAll() {
        hotel.showRooms();
    }
    public void checkReserve(){
        System.out.println("===============================");
        System.out.printf("%-10s %-10s\n","1. 예약하기", "2. 돌아가기");
        System.out.println("===============================");
        System.out.print("번호를 입력하시오 : ");
        int number = sc.nextInt();
        switch(number){
            case 1 : // 예약하기
                reserveRoom();
                break;
            case 2 : // 돌아가기
                customerMode();
                break;
        }
    }
    // 1-2-1. 이름, 번호, 소지금 입력
    public String enterNameInput(){
        return sc.next();
    }
    public String enterPhoneNumberInput(){
        return sc.next();
    }
    public int enterMoneyInput(){
        return sc.nextInt();
    }

    // 1-2. 객실 예약하기
    public void reserveRoom(){
        inputHotel();
        System.out.print("이름을 입력하시오 : ");
        String name = enterNameInput();
        System.out.print("번호를 입력하시오 : ");
        String phoneNumber = enterPhoneNumberInput();
        System.out.print("소지금을 입력하시오 : ");
        int money = enterMoneyInput();
        Customer customer = new Customer(name, phoneNumber, money);
        System.out.println("===============================");
        selectAll();
        System.out.println("===============================");
        System.out.print("객실을 선택하시오 : ");
        int roomNumber = sc.nextInt();
        if(customer.getMoney() >= rooms.get(roomNumber).getRoomPrice()){
            System.out.println("예약이 완료되었습니다.");
            hotel.addHotelAsset(rooms.get(roomNumber).getRoomPrice());
            customer.subtractCustomerMoney(rooms.get(roomNumber).getRoomPrice());
            System.out.println(name+" 님의 id는 "+createReservation(rooms.get(roomNumber), customer)+" 입니다.");
            System.out.println(name+" 님의 소지금은 "+customer.getMoney()+"원 남았습니다.");
            customerMode();
        } else{
            System.out.println("소지금이 부족합니다.");
            reserveRoom();
        }
    }
    // 1-2-1. 예약 생성 및 리스트 저장
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
        // list에 생성된 uuid 값 저장
        reservedUUIDList.add(uuid);
        // uuid 반환
        return uuid;
    }

    // 1-3. 객실 예약조회
    public Reservation viewMyReservation(UUID myUUID) {
        return reservedRoom.get(myUUID);
    }
    public void myReserve(UUID myUUID){
        System.out.print("예약번호를 입력하시오 : ");
        String id = sc.next();
//        if(id.equals(reservedUUIDList.get))
    }
    // 1-4. 객실 예약취소
    public void cancelMyReservation(UUID myUUID) {
        // 예약 내역 삭제
        reservedRoom.remove(myUUID);
        // 저장했던 key값도 삭제
        reservedUUIDList.remove(myUUID);
    }
    // 2. 호텔 모드
    public void hotelMode(){
//        System.out.print("\n");
//        System.out.println("관리자 비밀번호를 입력하세요.");
        // 비밀번호가 같다면 관리자모드로 진입
        System.out.print("\n");
        System.out.println("===============================");
        System.out.printf("%-8s %-8s %s\n","1. 보유자산", "2. 예약목록", "3. 돌아가기");
        System.out.println("===============================");
        System.out.print("번호를 입력 하시오 : ");
        int selectMenu = sc.nextInt();
        switch(selectMenu){
            case 1 : // 보유자산 조회
                hotelAsset();
                break;
            case 2 : // 예약목록 조회
                viewReservedRoom();
                break;
            case 3 : // 돌아가기
                selectModeInput();
                break;
        }
    }

    // 2-1 보유자산 조회
    public void hotelAsset(){
        System.out.print("\n");
        System.out.println("===============================");
        System.out.println("호텔의 총 자산은 : " + hotel.getAsset() + " 원 입니다.");
        System.out.println("===============================");
        System.out.printf("%-10s %-10s\n","1. 돌아가기", "2. 종료");
        System.out.print("번호를 입력 하시오 : ");
        int selectMenu = sc.nextInt();
        switch(selectMenu){
            case 1 : // 돌아가기
                hotelMode();
                break;
            case 2 : // 종료
                System.out.println("프로그램을 종료합니다.");
                break;
        }
    }
    // 2-2 예약목록 조회
    public void viewReservedRoom(){
        for(Map.Entry<UUID, Reservation> entrySet : reservedRoom.entrySet()){
            System.out.println(entrySet.getValue());
        }
        hotelMode();
    }
    // 2-3 남은 객실 조회?
}
