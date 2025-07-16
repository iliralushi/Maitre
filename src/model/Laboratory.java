package model;

public class Laboratory extends Room
{
    private boolean hasPC;
    private boolean hasElectricalSocket;

    public Laboratory(String roomID, int roomCapacity, boolean hasPC, boolean hasElectricalSocket)
    {
        super(roomID, roomCapacity, "Laboratory");

        this.hasPC = hasPC;
        this.hasElectricalSocket = hasElectricalSocket;
    }

    public boolean getHasPC()
    {
        return hasPC;
    }

    public boolean getHasElectricalSocket()
    {
        return hasElectricalSocket;
    }

    @Override
    protected boolean isReservationValid(Reservation newReservation)
    {
        int hours = newReservation.getEndingTime() - newReservation.getStartingTime();

        if (isWithinBookableHours(newReservation) && hours >= 2 && hours <= 4 && (hours % 2 == 0))
            return true;
        
        return false;
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + getHasPC() + " " + getHasElectricalSocket();
    }
}