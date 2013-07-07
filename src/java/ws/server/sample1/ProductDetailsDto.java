/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.server.sample1;

import java.math.BigDecimal;

/**
 *
 * @author ungvarip
 */
public class ProductDetailsDto {

    private int id;
    private String desc;
    private int count;
    private BigDecimal amount;
    private boolean available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
