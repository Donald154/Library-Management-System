//Class will represent patron's stores in the system
// Each patron's contain a unique 7 digit ID

public class Patron {

    //attributes
    private String patronId;
    private String name;
    private String address;
    private double overdueFine;

    //Constructor
    public Patron(String patronId, String name, String address, double overdueFine){
        this.patronId = patronId;
        this.address = address;
        this.name = name;
        this.overdueFine = overdueFine;
    }

    //Getters
    public String getPatronId(){
        return patronId;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public double getOverdueFine(){
        return overdueFine;
    }

    //toString
    @Override
    public String toString(){
        return String.format(
                "ID: %s%nName: %s%nAddress: %s%nOverdue Fine: $%.2f", patronId, name, address, overdueFine
        );
    }
}
