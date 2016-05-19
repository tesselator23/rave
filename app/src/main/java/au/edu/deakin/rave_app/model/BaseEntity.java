package au.edu.deakin.rave_app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class BaseEntity implements Serializable {

    @SerializedName("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
