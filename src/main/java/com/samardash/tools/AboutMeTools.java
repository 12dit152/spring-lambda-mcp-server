package com.samardash.tools;

import com.samardash.service.MyService;
import lombok.AllArgsConstructor;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AboutMeTools {

    private final MyService myService;

    @McpTool(name = "about_samar_dash", description = "Get comprehensive information about Samar Dash - answers any question about personal details, professional background, or biographical information")
    public String aboutMe() {
        return myService.getDetails();
    }
}
