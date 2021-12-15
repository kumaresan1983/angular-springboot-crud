/*
 * (C) 2021 Dagangnet Technologies Sdn Bhd.
 */
package com.test.example.entity.base;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kumaresan Sinniah
 */
public interface BaseEntity extends Serializable {

    void setId(String id);

    void setCreatedDatetime(Date date);

    void setCreatedBy(String creator);

    void setUpdatedDatetime(Date date);

    void setUpdatedBy(String updater);

    String getId();

    Date getCreatedDatetime();

    String getCreatedBy();

    Date getUpdatedDatetime();

    String getUpdatedBy();
}
