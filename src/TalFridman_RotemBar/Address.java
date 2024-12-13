package TalFridman_RotemBar;

public class Address {
    private String street;
    private String city;
    private String houseNumber;
    private String country;

    public Address(String country,String city, String street, String houseNumber) {
        this.street = street;
        this.city = city;
        this.houseNumber = houseNumber;
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("        Country: ").append(country).append("\n");
        builder.append("        City: ").append(city).append("\n");
        builder.append("        Street: ").append(street).append("\n");
        builder.append("        House number: ").append(houseNumber).append("\n");
        return builder.toString();
    }
}
