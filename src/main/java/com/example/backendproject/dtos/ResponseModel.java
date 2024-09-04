package com.example.backendproject.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseModel implements Serializable {
    @SerializedName("data")
    private Object data;
    private String message;
}
