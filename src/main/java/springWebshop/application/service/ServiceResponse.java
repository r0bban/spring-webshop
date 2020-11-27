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

    void addErrorMessage(String error) {
        sucessfulFalse();
        this.errorMessages.add(error);
    }

    void addResponseObject(T object) {
        sucessfulTrue();
        this.responseObjects.add(object);
    }

    void setResponseObjects(List<T> objects) {
        sucessfulTrue();
        this.responseObjects = objects;
    }

    void setErrorMessages(List<String> errorMessages) {
        if (!errorMessages.isEmpty()) sucessfulFalse();
        this.errorMessages = errorMessages;
    }

    private void sucessfulTrue() {
        this.sucessful = true;
    }

    private void sucessfulFalse() {
        this.sucessful = false;
    }
//
//    public ServiceResponse(List<ServiceErrorMessages> errorMessages) {
//        this.errorMessages = errorMessages;
//    }
}
