delete from hour_forecast;
delete from air_quality;
delete from realtime_weather;
delete from forecastday;
delete from weather_condition;
delete from weather_fetch_location;
delete from weather_fetch;

ALTER TABLE weather_fetch
    ALTER COLUMN id RESTART WITH 1;

ALTER TABLE weather_fetch_location
    ALTER COLUMN id RESTART WITH 1;

ALTER TABLE forecastday
    ALTER COLUMN id RESTART WITH 1;

ALTER TABLE realtime_weather
    ALTER COLUMN id RESTART WITH 1;

ALTER TABLE hour_forecast
    ALTER COLUMN id RESTART WITH 1;