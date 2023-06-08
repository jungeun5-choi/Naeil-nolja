import java.util.*;

public class Device {
    private TreeMap<Integer, Room> rooms = new TreeMap<>();
    public static final String FONT_GREEN = "\u001B[32m";
    public static final String FONT_RESET = "\u001B[0m";
    public static final String FONT_BLUE = "\u001B[34m";
    private Hotel hotel;
    Scanner sc = new Scanner(System.in);

    public void inputHotel() {
        //rooms에 key=호실번호, value= room객체를 담기
        rooms.put(101, new Room(101, RoomSize.Standard, 62000));
        rooms.put(102, new Room(102, RoomSize.Standard, 74000));
        rooms.put(201, new Room(201, RoomSize.Twin, 80000));
        rooms.put(202, new Room(202, RoomSize.Twin, 76000));
        rooms.put(301, new Room(301, RoomSize.Delux, 90000));
        rooms.put(402, new Room(402, RoomSize.Family, 110000));
        rooms.put(502, new Room(502, RoomSize.Suite, 150000));

        hotel = new Hotel("스파르타 호텔", rooms, 0);


    }

    public void showRoomList() {
        int again;
        Room selectedRoom;

        while (true) {
            selectPrint(); //
            System.out.println("1. 전체 객실 조회   2. 최저가 순 조회 3. 최고가 순 조회"); //최저가, 최고가 정렬
            if(selectSortOption(sc.nextInt())==0) continue;

            System.out.println("1. 예약하기 2. 뒤로가기");
            if (selectReservationOption(sc.nextInt())==0) continue;

            selectedRoom = selectRoomNumber();
            if(selectedRoom==null) continue;
            else break;
        }
        //선택한 Room 객체 selectedRoom 이용 예약진행
    }

    public void selectPrint() {
        System.out.println("\"스파르타 호텔에 오신 것을 환영합니다!\"");
        System.out.println(FONT_GREEN + "현재 투숙가능한 객실은 " + FONT_BLUE + rooms.size() + FONT_GREEN + "개 입니다." + FONT_RESET);
        System.out.println("조회할 방법을 선택하세요.");

    }

    public int selectSortOption(int input) {//객실조회옵션선택
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
            default: {
                System.out.println("잘못된 번호입력입니다.");
            }
        }
        return 0;

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

    public Room selectRoomNumber() {
        int roomNumber;
        Room selectedRoom=null;
        while (true) {
            roomNumber = sc.nextInt();
            if (rooms.containsKey(roomNumber)) {
                System.out.println("선택한 객실 :");
                rooms.get(roomNumber).showIntroduce();
                System.out.println("이 객실의 예약을 진행하시겠습니까?");
                System.out.println("1. 예    2.아니오");
                if(sc.nextInt()==1){
                    selectedRoom = rooms.get(roomNumber);
                }
                else {
                    System.out.println("예약할 객실번호를 입력하세요.(돌아가기 - 0 )");
                }
            } else if (roomNumber == 0) {
                System.out.println("초기 화면으로 돌아갑니다.");
                break;
            }
            else
            {
                System.out.println("선택한 객실 번호는 없습니다. 다시 입력해주세요.");
                System.out.println("예약할 객실번호를 입력하세요.(돌아가기 - 0 )");
                continue;
            }
        }
        return selectedRoom;

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
}
