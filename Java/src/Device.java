//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.util.*;
//
//public class Device {
//    Map<UUID, Reservation> reservatedMap = new HashMap<UUID, Reservation>(); // 예약리스트. 이미 예약된 객실을 조회목록에서 빼기 위한 테스트용
//    int reservatedCount = 0;
//    public static final String FONT_GREEN = "\u001B[32m";
//    public static final String FONT_RESET = "\u001B[0m";
//    public static final String FONT_BLUE = "\u001B[34m";
//
//    // 1-1. 객실 목록 조회
//    public void display() {
//        Room selectedRoom;
//        while (true) {
//            selectRoomList();   // 호텔 전체 객실 / 날짜가 겹칠 경우 리스트에서 제거
//            selectPrint();  // 날짜에 대한 요일 표기
//            System.out.println("1. 전체 객실 2. 최저가 순 3. 최고가 순 4.날짜 변경 5. 돌아 가기"); //최저가, 최고가 정렬
//            System.out.println("===============================");
//            System.out.print("번호를 입력하세요 : ");
//            if(selectSortOption(sc.nextInt())==0) continue;
//
//            System.out.println("1. 예약하기 2. 뒤로가기");
//            if (selectReservationOption(sc.nextInt())==0) continue;
//
//            selectedRoom = selectRoomNumber();
//            if(selectedRoom==null) continue;
//            else break;
//        }
//        //선택한 Room 객체 selectedRoom과 selectedDate 이용 예약진행
//    }
//    // 1-1-1. 호텔의 전체 객실
//    public void selectRoomList(){
//        reservatedCount=0;
//        rooms = new TreeMap<>(hotel.getRooms()); // rooms에 호텔 전체객실 getRooms() 붙여넣기
//        for(Reservation reservation : reservatedMap.values()){
//            if (selectedDate.isEqual(reservation.getDate())){//선택날짜와 예약된 날짜가 같은경우
//                rooms.remove(reservation.getRoom().getRoomNumber()); // 예약정보 중 객실번호를 가져와 rooms에서 제거
//                reservatedCount++;
//            }
//        }
//    }
//    // 1-1-2. 호텔 예약 날짜의 요일 표기 및 날짜별 예약 명수 확인
//    public void selectPrint() {
//        System.out.println("===============================");
//        System.out.println("\"스파르타 호텔에 오신 것을 환영합니다!\"");
//        System.out.print("예약날짜 : "+selectedDate);
//        int dayOfWeek = selectedDate.getDayOfWeek().getValue();//요일 구하기 1-월 ~ 7-일
//        switch(dayOfWeek){
//            case 1:System.out.println("(월)");break;
//            case 2:System.out.println("(화)");break;
//            case 3:System.out.println("(수)");break;
//            case 4:System.out.println("(목)");break;
//            case 5:System.out.println("(금)");break;
//            case 6:System.out.println("(토)");break;
//            case 7:System.out.println("(일)");break;
//        }
//        System.out.println(FONT_GREEN + "해당날짜의 투숙가능한 객실은 " + FONT_BLUE + rooms.size() + FONT_GREEN + "개 입니다." +FONT_BLUE+"(예약 "+reservatedCount+"명)" + FONT_RESET);
//        System.out.println("조회할 방법을 선택하세요.");
//
//    }
//    // 1-1-3. 객실 조회 옵션 선택
//    public int selectSortOption(int input) {
//        sc.nextLine();
//        switch (input) {
//            case 1: {  //전체 객실 조회
//                selectAll();
//                return 1;
//            }
//            case 2: { // 최저가 순 조회
//                ArrayList<Room> sortList = new ArrayList<>();
//                sortList.addAll(rooms.values());
//                Collections.sort(sortList);
//                selectAll(sortList);
//                return 1;
//            }
//            case 3: { //최고가 순 조회
//                ArrayList<Room> sortList = new ArrayList<>();
//                sortList.addAll(rooms.values());
//                Collections.sort(sortList, Collections.reverseOrder());
//                selectAll(sortList);
//                return 1;
//            }
//            case 4: {//예약 날짜 변경
//                changeselectedDate();
//                break;
//            }
//            default: {
//                System.out.println("잘못된 번호입력입니다.");
//            }
//        }
//        return 0;
//    }
//    // 1-1-4. 예약 날짜 변경
//    public void changeselectedDate() {
//        System.out.println("예약하고 싶은 날짜를 입력하세요. (yyyy-mm-dd 형식으로 입력하세요.)");
//        LocalDate date = null;
//        String inputDate = sc.nextLine();
//
//        while (true) {
//            try {
//                date = LocalDate.parse(inputDate, DateTimeFormatter.ISO_DATE);
//                break;
//            } catch (DateTimeParseException e) {
//                System.out.println("잘못된 형식의 날짜입력입니다.");
//                System.out.println("날짜를 다시 입력하세요. (yyyy-mm-dd형식으로 입력하세요.)");
//                inputDate = sc.nextLine();
////            throw new RuntimeException(e);
//            }
//        }
//        if (date.isBefore(LocalDate.now())) {//입력날짜가 오늘보다 작은 값일 경우
//            System.out.println("지난 날짜는 예약할 수 없습니다.");
//            System.out.println("처음 화면으로 돌아갑니다.");
//        } else {
//            selectedDate = date;
//            System.out.println("예약날짜가 변경되었습니다. 해당 날짜의 예약가능한 객실을 다시 불러옵니다.");
//        }
//    }
//    // 1-1-5. 객실 번호 선택
//    public int selectReservationOption(int input) {
//        switch (input) {
//            case 1: {
//                System.out.println("예약할 객실번호를 입력하세요.(돌아가기 - 0 )");
//                return 1;
//            }
//            case 2: {
//                System.out.println("초기 화면으로 돌아갑니다.");
//                break;
//            }
//            default: {
//                System.out.println("잘못된 입력입니다.");
//                break;
//            }
//        }
//        return 0;
//    }
//    // 1-2. 객실 예약하기
//    public Room selectRoomNumber() {
//        int roomNumber;
//        Room selectedRoom = null;
//        while (true) {
////            selectAll(); // 모든 객실의 정보를 보여줌
//            System.out.print("객실을 선택하세요 : ");
//            roomNumber = sc.nextInt();
//            sc.nextLine();
//            if (rooms.containsKey(roomNumber)) {
//                System.out.println("선택한 객실 :");
//                rooms.get(roomNumber).showIntroduce();  // 선택한 객실의 정보
//                System.out.println("이 객실의 예약을 진행하시겠습니까?");
//                System.out.printf("%-10s %-10s\n", "1. 예약하기", "2. 돌아가기");
//                if (sc.nextInt() == 1) {
//                    System.out.print("이름을 입력하세요 : ");
//                    String name = sc.next();
//                    System.out.print("번호를 입력하세요 : ");
//                    String phoneNumber = sc.next();
//                    System.out.print("소지금을 입력하세요 : ");
//                    int money = sc.nextInt();
//                    Customer customer = new Customer(name, phoneNumber, money); // Customer 생성(name, phoneNumber, money 중 하나를 빼오기 위함)
//
//                    if (customer.getMoney() >= rooms.get(roomNumber).getRoomPrice()) {  // Customer의 money와 선택한 객실의 roomPrice 비교
//                        reserve.createReservation(rooms.get(roomNumber),customer);
//                        System.out.println("예약이 완료되었습니다.");
//                        hotel.addHotelAsset(rooms.get(roomNumber).getRoomPrice());
//                        customer.subtractCustomerMoney(rooms.get(roomNumber).getRoomPrice());
//                        System.out.println(name + " 님의 id는 " + reserve.createReservation(rooms.get(roomNumber), customer) + " 입니다.");
//                        System.out.println(name + " 님의 소지금은 " + customer.getMoney() + "원 남았습니다.");
//                        app.customerMode();
//                    } else {
//                        System.out.println("소지금이 부족합니다.");
//                    }
//                    selectedRoom = rooms.get(roomNumber);
//                } else {
//                    System.out.println("예약할 객실번호를 입력하세요.(돌아가기 - 0 )");
//                }
//            } else if (roomNumber == 0) {
//                System.out.println("초기 화면으로 돌아갑니다.");
//                break;
//            } else {
//                System.out.println("선택한 객실 번호는 없습니다. 다시 입력해주세요.");
//                System.out.println("예약할 객실번호를 입력하세요.(돌아가기 - 0 )");
//            }
//        }
//        return selectedRoom;
//    }
//    public void selectAll() {
//        System.out.println(" 객실 정보 :");
//        for (Room room : rooms.values()) {
//            room.showIntroduce();
//        }
//    }
//    public void selectAll(ArrayList<Room> rooms) { //정렬한 객실정보 보여주기용
//        System.out.println(" 객실 정보 :");
//        for (Room room : rooms) {
//            room.showIntroduce();
//        }
//    }
//}