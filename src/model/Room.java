package model;
import java.util.*;

public abstract class Room
{
    private String roomID;
    private int roomCapacity;
    private String roomType;

    private List<Reservation> roomReservations;

    public Room(String roomID, int roomCapacity, String roomType)
    {
        this.roomID = roomID;
        this.roomCapacity = roomCapacity;
        this.roomType = roomType;
        
        this.roomReservations = new ArrayList<>();
    }

    public String getRoomID()
    {
        return roomID;
    }

    public int getRoomCapacity()
    {
        return roomCapacity;
    }

    public String getRoomType()
    {
        return roomType;
    }

    public List<Reservation> getRoomReservations()
    {
        return roomReservations;
    }

    protected abstract boolean isReservationValid(Reservation newReservation);

    protected boolean isReservationOverlapping(Reservation newReservation)
    {
        for (Reservation oldReservation : getRoomReservations())
        {
            if (newReservation.getStartingTime() < oldReservation.getEndingTime() &&
                newReservation.getEndingTime() > oldReservation.getStartingTime() &&
                newReservation.getReservationDate().equals(oldReservation.getReservationDate()))
                return true;
        }

        return false;
    }

    protected boolean isWithinBookableHours(Reservation newReservation)
    {
        if (newReservation.getStartingTime() >= 8 && newReservation.getEndingTime() <= 18)
            return true;
        
        return false;
    }

    protected boolean addReservation(Reservation newReservation)
    {
        if (!isReservationOverlapping(newReservation) && isReservationValid(newReservation))
        {
            getRoomReservations().add(newReservation);
            return true;
        }

        return false;
    }

    protected boolean editReservation(Reservation oldReservation, Reservation newReservation)
    {
        List<Reservation> roomList = getRoomReservations();

        if (roomList.contains(oldReservation) && isReservationValid(newReservation))
        {
            int index = roomList.indexOf(oldReservation);
            roomList.set(index, newReservation);
            return true;
        }

        return false;
    }

    protected boolean deleteReservation(Reservation oldReservation)
    {
        if (getRoomReservations().contains(oldReservation))
        {
            getRoomReservations().remove(oldReservation);
            return true;
        }

        return false;
    }

    @Override
    public String toString()
    {
        return getRoomID() + " " + getRoomCapacity() + " " +
               getRoomType() + " " + getRoomReservations();
    }
}