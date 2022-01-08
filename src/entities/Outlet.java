package entities;

import java.io.Serial;
import java.io.Serializable;

public class Outlet implements Serializable {
    @Serial
    private static final long serialVersionUID = 6453184284474197396L;
    private String id, name, address;
    private Location location;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    @Override
    public String toString() {
        return "Outlet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", location=" + location +
                '}';
    }
}
