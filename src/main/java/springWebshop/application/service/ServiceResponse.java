package springWebshop.application.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ServiceResponse<T> {
    boolean sucessful;
    List<T> responseObjects;
    List<String> errorMessages;

    public ServiceResponse() {
        this.responseObjects = new ArrayList<T>();
        this.errorMessages = new ArrayList<>();
    }

//
    public ServiceResponse(List<T> responseObjects, List<String> errorMessages) {
        this.responseObjects = responseObjects;
        this.errorMessages = errorMessages;
        this.sucessful = CollectionUtils.isEmpty(errorMessages);
    }
//
//    public ServiceResponse(List<ServiceErrorMessages> errorMessages) {
//        this.errorMessages = errorMessages;
//    }
}
