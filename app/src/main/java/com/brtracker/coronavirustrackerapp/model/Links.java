
package com.brtracker.coronavirustrackerapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    public String self;
    @SerializedName("git")
    @Expose
    public String git;
    @SerializedName("html")
    @Expose
    public String html;

}
