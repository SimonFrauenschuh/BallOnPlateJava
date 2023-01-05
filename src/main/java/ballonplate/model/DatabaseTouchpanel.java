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
@Table(name="postouchpanel")
public class DatabaseTouchpanel implements Serializable{
	
	@Id
    @GeneratedValue
    private int id;
	
	@NotNull
    @Range(min = -400, max = 400)
    private int xEst;
	
	@NotNull
    @Range(min = -400, max = 400)
    private int yEst;
	
	@NotNull
    @Range(min = -400, max = 400)
    private int xReal;
	
	@NotNull
    @Range(min = -400, max = 400)
    private int yReal;
	
	public void setId(int id) {
        this.id = id;
    }
	
	public int getId() {
        return id;
    }
    
	public void setXEst(int xEst) {
        this.xEst = xEst;
    }
	
    public int getXEst() {
        return xEst;
    }
    
    public void setYEst(int yEst) {
        this.yEst = yEst;
    }
    
    public int getYEst() {
        return yEst;
    }
    
    public void setXReal(int xReal) {
        this.xReal = xReal;
    }
	
    public int getXReal() {
        return xReal;
    }
    
    public void setYReal(int yReal) {
        this.yReal = yReal;
    }
    
    public int getYReal() {
        return yReal;
    }
}