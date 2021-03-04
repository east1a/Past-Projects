//Easton Anderson
//Mrs. Meyer
//Udub Project #8
//5/15/17

//This program interacts with the client by encapsulating fields and methods that are used to get information
//about places of interest for PlaceInformationClient


public class PlaceInformation {
   private String location;
   private String address;
   private String tags;
   private double latitude;
   private double longitude;

   public PlaceInformation(String location, String address, String tags, double latitude, double longitude){

      this.location = location;
      this.address = address;
      this.tags = tags;
      this.latitude= latitude;
      this.longitude= longitude;
   }

   //returns the name of the place of interest
   public String getName(){
      return location;
   }
   
   //returns the address of the plae of interest
   public String getAddress(){
      return address;
   }

   //returns any tags attatched to that place of interest
   public String getTag(){
      return tags;
   }

   //Prints out the string for latitude and longitude
   public String toString() {
        return location + " (latitude: " + latitude + ", longitude: " + longitude + ")";
   }

   //Constructs a new location and returns it
   public GeoLocation getLocation(){
      GeoLocation coordinates = new GeoLocation(latitude, longitude);
      return coordinates;
   }

   //returns the distance in miles between two locations
   public double distanceFrom(GeoLocation spot){
      GeoLocation difCoordinates = getLocation();
      return difCoordinates.distanceFrom(spot);
   }
}

