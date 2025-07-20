package model;

import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class RoomManager
{
    List<Room> rooms;

    public RoomManager(String inputFile)
    {
        rooms = new ArrayList<>();

        loadRoomsFromFile(inputFile);
    }

    public List<Room> getRooms()
    {
        return rooms;
    }

    public boolean loadRoomsFromFile(String inputFile)
    {
        try
        {
            getRooms().clear();

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            Room room = null;

            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] data = line.split(" ");
                String roomType = data[2];

                if (roomType.equals("Classroom") || roomType.equals("Laboratory"))
                {
                    String roomID = data[0];

                    int roomCapacity = Integer.parseInt(data[1]);

                    boolean has1 = Boolean.parseBoolean(data[3]);
                    boolean has2 = Boolean.parseBoolean(data[4]);

                    if (roomType.equals("Classroom"))
                    {
                        room = new Classroom(roomID, roomCapacity, has1, has2);
                    }
                    else if (roomType.equals("Laboratory"))
                    {
                        room = new Laboratory(roomID, roomCapacity, has1, has2);
                    }

                    getRooms().add(room);
                } 
                else if (room != null)
                {
                    LocalDate date = LocalDate.parse(data[0]);

                    int startingTime = Integer.parseInt(data[1]);
                    int endingTime = Integer.parseInt(data[2]);

                    String reservationName = data[3];
                    String reservationReason = String.join(" ", Arrays.copyOfRange(data, 4, data.length));

                    Reservation fileReservation = new Reservation(date, startingTime, endingTime, reservationName, reservationReason);

                    room.addReservation(fileReservation);
                }
            }

            reader.close();
            return true;
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found.");
            return false;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveRoomsToFile(String filePath)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (Room room : getRooms())
            {
                writer.write(room.getRoomID() + " " + room.getRoomCapacity() + " " + room.getRoomType() + " ");

                if (room instanceof Classroom)
                {
                    Classroom classroom = (Classroom) room;
                    writer.write(classroom.getHasBlackboard() + " " + classroom.getHasVideoProjector());
                    writer.newLine();
                }
                else if (room instanceof Laboratory)
                {
                    Laboratory laboratory = (Laboratory) room;
                    writer.write(laboratory.getHasPC() + " " + laboratory.getHasElectricalSocket());
                    writer.newLine();
                }
                
                for (Reservation reservation : room.getRoomReservations())
                {
                    writer.write(reservation.getReservationDate() + " " + reservation.getStartingTime() + " " +
                                 reservation.getEndingTime() + " " + reservation.getReservationName() + " " +
                                 reservation.getReservationReason());
                    writer.newLine();
                }
            }

            writer.close();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Room getRoom(String roomID)
    {
        for (Room tempRoom : getRooms())
        {
            if (tempRoom.getRoomID().equals(roomID))
                return tempRoom;
        }

        return null;
    }

    public boolean addReservation(String roomID, Reservation newReservation)
    {
        Room room = getRoom(roomID);

        if (room != null)
        {
            return room.addReservation(newReservation);
        }

        return false;
    }

    public boolean editReservation(String roomID, Reservation oldReservation, Reservation newReservation)
    {
        Room room = getRoom(roomID);

        if (room != null)
        {
            return room.editReservation(oldReservation, newReservation);
        }

        return false;
    }

    public boolean deleteReservation(String roomID, Reservation oldReservation)
    {
        Room room = getRoom(roomID);

        if (room != null)
        {
            return room.deleteReservation(oldReservation);
        }   

        return false;
    }
}