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
    @Range(min = 0, max = 16000)
    private int positionXEst;
	
	@NotNull
    @Range(min = 0, max = 16000)
    private int positionYEst;
	
	@NotNull
    @Range(min = 0, max = 16000)
    private int positionXReal;
	
	@NotNull
    @Range(min = 0, max = 16000)
    private int positionYReal;
	
	@NotNull
    @Range(min = 0, max = 12)
    private int mode;
	
	@NotNull
    @Range(min = 0, max = 12)
    private int error;
	
	public void setId(Long id) {
        this.id = id;
    }
	
	public Long getId() {
        return id;
    }
    
	public void setPositionXEst(int positionXEst) {
        this.positionXEst = positionXEst;
    }
	
    public int getPositionXEst() {
        return positionXEst;
    }
    
    public void setPositionYEst(int positionYEst) {
        this.positionYEst = positionYEst;
    }
    
    public int getPositionYEst() {
        return positionYEst;
    }
    
    public void setPositionXReal(int positionXReal) {
        this.positionXReal = positionXReal;
    }
	
    public int getPositionXReal() {
        return positionXReal;
    }
    
    public void setPositionYReal(int positionYReal) {
        this.positionYReal = positionYReal;
    }
    
    public int getPositionYReal() {
        return positionYReal;
    }
    
    public void setMode(int mode) {
        this.mode = mode;
    }
    
    public Integer getMode() {
        return mode;
    }
    
    public void setError(int error) {
        this.error = error;
    }
    
    public Integer getError() {
        return error;
    }
}