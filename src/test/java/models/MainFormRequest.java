package models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MainFormRequest {
    private String device;
    private String language;
    private String platform;
    private Integer screenHeight;
    private Integer screenWidth;
    private String userAgent;
    private ArrayList<String> queryParameters;
}



