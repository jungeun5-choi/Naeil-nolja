import java.util.*;

public class Hotel {
    private String hotelName; // 호텔 이름

    private TreeMap<Integer, Room> rooms = new TreeMap<>(); // 객실 여러개
    private int asset; // 보유 자산
//    private UUID reservationNumber; // 예약 번호 (uuid)

    /* 생성자 */
    public Hotel() {
    }

    public Hotel(String hotelName, TreeMap<Integer, Room> rooms, int asset) {//, UUID reservationNumber) {
        this.hotelName = hotelName;
        this.rooms = rooms;
        this.asset = asset;
//        this.reservationNumber = reservationNumber;
    }

    /* getter */
    public String getHotelName() {
        return hotelName;
    }

    public int getAsset() {
        return asset;
    }

    public int getPassword() {
        return 12345;
    }

    public TreeMap<Integer, Room> getRooms() { // 객실 전체
        return rooms;
    }

    public Room getRoom(int index) { // 객실 한 개
        return rooms.get(index);
    }

//    public UUID getReservationNumber() {
//        return reservationNumber;
//    }

    //    public Room getCheapestRoom()
//    {
//        return Collections.min(rooms);
//    }
    public void showRooms() {
        System.out.println(hotelName + " 객실 정보 :");
        for (Room room : this.rooms.values()) {
            room.showIntroduce();
        }
    }


//    public void sortCheap(){ //저렴한 가격순으로 정렬
//        Collections.sort(rooms);
//    }
//    public void sortExpansive(){ //비싼 가격순으로 정렬
//        Collections.sort(rooms, Collections.reverseOrder());
//    }


    /* setter */
    // 자산 더하기
    public void addHotelAsset(int asset) {
        this.asset += asset;
    }

    // 자산 빼기
    public void subtractHotelAsset(int asset) {
        this.asset -= asset;
    }
}
