package model;
import java.time.LocalDate;

public class Reservation
{
    private LocalDate reservationDate;

    private int startingTime;
    private int endingTime;

    private String reservationName;
    private String reservationReason;

    public Reservation(LocalDate reservationDate, int startingTime, int endingTime, String reservationName, String reservationReason)
    {
        this.startingTime = startingTime;
        this.endingTime = endingTime;

        this.reservationDate = reservationDate;

        this.reservationName = reservationName;
        this.reservationReason = reservationReason;
    }

    public LocalDate getReservationDate()
    {
        return reservationDate;
    }

    public int getStartingTime()
    {
        return startingTime;
    }

    public int getEndingTime()
    {
        return endingTime;
    }

    public String getReservationName()
    {
        return reservationName;
    }

    public String getReservationReason()
    {
        return reservationReason;
    }

    @Override 
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        
        if (!(obj instanceof Reservation))
            return false;

        Reservation other = (Reservation) obj;

        return (this.getStartingTime() == other.getStartingTime() && this.getEndingTime() == other.getEndingTime() &&
                this.getReservationName().equals(other.getReservationName()) && 
                this.getReservationDate().equals(other.getReservationDate()));
    }  

    @Override
    public String toString()
    {
        return getReservationDate() + " " + getStartingTime() + " " + getEndingTime() + " " + 
               getReservationName() + " " + getReservationReason();
    }
}