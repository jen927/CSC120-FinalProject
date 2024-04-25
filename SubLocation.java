public class SubLocation {
    
    String locationName;
    String description;
    Area currentArea;
    boolean northPath;
    boolean southPath;
    boolean westPath;
    boolean eastPath;

    public SubLocation(String locationName, boolean northPath, boolean southPath, boolean westPath, boolean eastPath){
        this.locationName = locationName;
        this.northPath = northPath;
        this.southPath = southPath;
        this.westPath = westPath;
        this.eastPath = eastPath;
    }

    public String getDescription(){
        //will read area file to get description
        return description;
    }

}
