package com.hoccer.talk.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Represents a (generated) one-time token
 *
 * This is used to establish client pairing but can also
 * be applied to other things in the future.
 *
 */
@DatabaseTable(tableName = "token")
public class TalkToken {

    public static final String PURPOSE_PAIRING = "pairing";

    public static final String STATE_USED   = "used";
    public static final String STATE_UNUSED = "unused";

    public static boolean isValidPurpose(String purpose) {
        if(purpose.equals(PURPOSE_PAIRING)) {
            return true;
        }
        return false;
    }

    private String _id;

    /** ID of the client that generated this token */
    @DatabaseField
    String clientId;

    /** Current state of this token */
    @DatabaseField
    String state;

    /** Purpose of this token */
    @DatabaseField
    String purpose;

    /** The token secret itself */
    @DatabaseField
    String secret;

    /** Time at which the token was generated */
    @DatabaseField
    Date generationTime;

    /** Time at which the token will expire */
    @DatabaseField
    Date expiryTime;

    public TalkToken() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Date getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(Date generationTime) {
        this.generationTime = generationTime;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

}
