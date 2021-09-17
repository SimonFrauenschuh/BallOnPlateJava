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
@Table
public class DatabaseModel implements Serializable{
	
	@Id
    @GeneratedValue
    private Long id;
	
	@NotNull
    @Range(min = 0, max = 60)
    private double positionX;
	
	@NotNull
    @Range(min = 0, max = 60)
    private double positionY;
	
	@NotNull
    @Range(min = 0, max = 12)
    private int action;
	
	public void setId(Long id) {
        this.id = id;
    }
	
	public Long getId() {
        return id;
    }
    
	public void setPositionX(Double positionX) {
        this.positionX = positionX;
    }
	
    public Double getPositionX() {
        return positionX;
    }
    
    public void setPositionY(Double positionY) {
        this.positionY = positionY;
    }
    
    public Double getPositionY() {
        return positionY;
    }
    
    public void setId(int action) {
        this.action = action;
    }
    
    public Integer getAction() {
        return action;
    }
}