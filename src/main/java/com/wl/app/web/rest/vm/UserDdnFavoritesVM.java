package com.wl.app.web.rest.vm;

public class UserDdnFavoritesVM {
    private long user_id;
    private long ddn_id;


    public UserDdnFavoritesVM() {
    }

    public UserDdnFavoritesVM(long user_id, long ddn_id) {
        this.user_id = user_id;
        this.ddn_id = ddn_id;
    }



    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getDdn_id() {
        return ddn_id;
    }

    public void setDdn_id(long ddn_id) {
        this.ddn_id = ddn_id;
    }
}
