package controllers;

import models.OutletManager;

public class OutletController {
    private final OutletManager  outletManager;

    public OutletController(OutletManager outletManager) {
        this.outletManager = outletManager;
    }

    public OutletManager getOutletManager() {
        return outletManager;
    }
}
