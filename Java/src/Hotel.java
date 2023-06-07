import java.util.*;

public class Hotel {

    private String hotelName; // 호텔 이름
    private ArrayList<Room> rooms = new ArrayList<>(); // 객실 여러개
    private int asset; // 보유 자산
    private UUID reservationNumber; // 예약 번호 (uuid)
    private int hotelPassword;

    /* 생성자 */
    public Hotel(){}
    public Hotel(String hotelName, ArrayList<Room> rooms, int asset, UUID reservationNumber, int hotelPassword) {
        this.hotelName = hotelName;
        this.rooms = rooms;
        this.asset = asset;
        this.reservationNumber = reservationNumber;
        this.hotelPassword = hotelPassword;
    }

    /* getter */
    public String getHotelName() {
        return hotelName;
    }
    public int getAsset() {
        return asset;
    }
    public ArrayList<Room> getRooms() { // 객실 전체
        return rooms;
    }
    public Room getRoom(int index) { // 객실 한 개
        return rooms.get(index);
    }
    public UUID getReservationNumber() {
        return reservationNumber;
    }
    public int getHotelPassword(){
        return hotelPassword;
    }
}
