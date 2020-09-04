package k.c.module.http;


import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class Result<M>{

    @SerializedName("Data")
    public Collection<M> value;

    @SerializedName("Result")
    public BaseResult result;
}

