package nl.davefemi.weatherdashboard.client.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private final boolean success;
    private final String location;
    private final String response;
}
