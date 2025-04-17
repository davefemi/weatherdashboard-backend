package nl.davefemi.weatherdashboard.etl.domain.service.registry;


import java.util.Objects;

public class ApiClientIdentifier {
    private String name;
    private String endpoint;

    public ApiClientIdentifier(String name, String endpoint) {
        this.name = name;
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiClientIdentifier)) return false;
        ApiClientIdentifier that = (ApiClientIdentifier) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(endpoint, that.endpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, endpoint);
    }

    @Override
    public String toString() {
        return "ApiClientIdentifier{" +
                "name='" + name + '\'' +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}
