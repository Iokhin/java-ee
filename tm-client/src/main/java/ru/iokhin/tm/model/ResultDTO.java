package ru.iokhin.tm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class ResultDTO implements Serializable {

    private boolean success;

    public ResultDTO(boolean success) {
        this.success = success;
    }

}
