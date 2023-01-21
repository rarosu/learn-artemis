package learn.artemis.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SubmissionDTO implements Serializable {
    private String messageName;
    private String value;
}