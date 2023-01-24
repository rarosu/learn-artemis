package learn.artemis.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class SubmissionDTO implements Serializable {
    private String messageName;
    private String value;
}
