package com.fthkara.numbermanager.model;

import com.fthkara.numbermanager.util.DateUtil;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "numbers")
public class NumberDocument {

    private Integer number;

    private Date insertDate;

    public NumberDocument() {
        this.insertDate = DateUtil.getDate();
    }

    public NumberDocument(Integer number, Date insertDate) {
        this.number = number;
        this.insertDate = insertDate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumberDocument that = (NumberDocument) o;

        if (number != null ? !number.equals(that.number) : that.number != null) return false;
        return insertDate != null ? insertDate.equals(that.insertDate) : that.insertDate == null;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (insertDate != null ? insertDate.hashCode() : 0);
        return result;
    }
}
