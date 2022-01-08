package models;

import entities.Outlet;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OutletManager implements Serializable {
    @Serial
    private static final long serialVersionUID = -6018568753591681351L;
    private final List<Outlet> outletList = new ArrayList<>();
    public void registerOutlet(Outlet outlet){
        outletList.add(outlet);
    }
    public List<Outlet> getOutletList() {
        return outletList;
    }
}
