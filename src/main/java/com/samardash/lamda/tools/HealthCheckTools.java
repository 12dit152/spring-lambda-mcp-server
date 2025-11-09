package com.samardash.lamda.tools;

import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckTools {

    @McpTool(name = "health_check", description = "Health check tool for mcp server")
    public String healthCheck() {
        return "Healthy";
    }
}
