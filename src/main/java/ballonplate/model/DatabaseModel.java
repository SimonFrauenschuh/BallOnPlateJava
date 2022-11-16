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
    @Range(min = 0, max = 400)
    private int xEst;
	
	@NotNull
    @Range(min = 0, max = 400)
    private int yEst;
	
	@NotNull
    @Range(min = 0, max = 400)
    private int xReal;
	
	@NotNull
    @Range(min = 0, max = 400)
    private int yReal;
	
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
    
	public void setPositionXEst(int xEst) {
        this.xEst = xEst;
    }
	
    public int getPositionXEst() {
        return xEst;
    }
    
    public void setPositionYEst(int yEst) {
        this.yEst = yEst;
    }
    
    public int getPositionYEst() {
        return yEst;
    }
    
    public void setPositionXReal(int xReal) {
        this.xReal = xReal;
    }
	
    public int getPositionXReal() {
        return xReal;
    }
    
    public void setPositionYReal(int yReal) {
        this.yReal = yReal;
    }
    
    public int getPositionYReal() {
        return yReal;
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