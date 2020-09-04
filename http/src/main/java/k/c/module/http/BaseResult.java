package k.c.module.http;

import com.google.gson.annotations.SerializedName;


public class BaseResult {
    @SerializedName("State")
    public int code;

    @SerializedName("Msg")
    public String message;
}