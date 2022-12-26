/* Copyright (C) 2021 Simon Frauenschuh - All Rights Reserved
 * You may use and / or modify this code in
 * terms of private use.
 * Any caused damage or misbehaviour of any components are
 * under the responsibility of the user and and the editor
 * cannot be prosecuted for it
 */

package ballonplate.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Range;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name="result")
public class DatabaseResult implements Serializable{
	
	@Id
    @GeneratedValue
    private Long id;
	
	@NotNull
    private int result;
	
	@NotNull
    @Range(min = 0, max = 12)
    private int mode;
	
	public void setId(Long id) {
        this.id = id;
    }
	
	public Long getId() {
        return id;
    }
    
    public void setMode(int mode) {
        this.mode = mode;
    }
    
    public Integer getMode() {
        return mode;
    }
    
    public void setResult(int result) {
        this.result = result;
    }
    
    public Integer getResult() {
        return result;
    }
}