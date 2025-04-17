package nl.davefemi.weatherdashboard.domain.service.registry;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.domain.model.ContinentModel;
import nl.davefemi.weatherdashboard.domain.model.LocationModel;

@Data
@RequiredArgsConstructor
public class LocationDescription {
    private final LocationModel location;
    private final ContinentModel continent;
}
