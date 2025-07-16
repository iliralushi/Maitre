package model;

public class Classroom extends Room
{
    private boolean hasBlackboard;
    private boolean hasVideoProjector;

    public Classroom(String roomID, int roomCapacity, boolean hasBlackboard, boolean hasVideoProjector)
    {
        super(roomID, roomCapacity, "Classroom");

        this.hasBlackboard = hasBlackboard;
        this.hasVideoProjector = hasVideoProjector;
    }

    public boolean getHasBlackboard()
    {
        return hasBlackboard;
    }

    public boolean getHasVideoProjector()
    {
        return hasVideoProjector;
    }

    @Override
    protected boolean isReservationValid(Reservation newReservation)
    {
        int bookedTime = newReservation.getEndingTime() - newReservation.getStartingTime();

        if (isWithinBookableHours(newReservation) && bookedTime >= 1 && bookedTime <= 8)
            return true;

        return false;
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + getHasBlackboard() + " " + getHasVideoProjector();
    }
}