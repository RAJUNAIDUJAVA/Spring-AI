package com.td.AI.tools;

import com.td.AI.service.TempService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherTool {
    @Autowired
    TempService tempService;

    @Tool(description = "Get Weather information of given city")
    public String getWeather(@ToolParam(description = "city of which we want to get weather information") String city){
        var response = tempService.getResponse(city);
        return response.toString();
    }

}
