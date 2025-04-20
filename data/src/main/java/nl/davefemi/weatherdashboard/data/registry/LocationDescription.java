package nl.davefemi.weatherdashboard.data.registry;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.davefemi.weatherdashboard.data.model.ContinentModel;
import nl.davefemi.weatherdashboard.data.model.LocationModel;

@Data
@RequiredArgsConstructor
public class LocationDescription {
    private final LocationModel location;
    private final ContinentModel continent;
}
