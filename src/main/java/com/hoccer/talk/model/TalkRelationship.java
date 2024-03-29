package com.hoccer.talk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@DatabaseTable(tableName = "relationship")
public class TalkRelationship {

    public static final String STATE_NONE = "none";
    public static final String STATE_FRIEND = "friend";
    public static final String STATE_BLOCKED = "blocked";

    public static boolean isValidState(String state) {
        return STATE_NONE.equals(state) || STATE_FRIEND.equals(state) || STATE_BLOCKED.equals(state);
    }

    private String _id;

    @DatabaseField(generatedId = true)
    private long relationshipId;

    @DatabaseField
    String clientId;

    @DatabaseField
    String otherClientId;

    @DatabaseField
    String state;

    @DatabaseField
    Date lastChanged;

    public TalkRelationship() {
    }

    @JsonIgnore
    public boolean isRelated() {
        return !state.equals(STATE_NONE);
    }

    @JsonIgnore
    public boolean isFriend() {
        return state.equals(STATE_FRIEND);
    }

    @JsonIgnore
    public boolean isBlocked() {
        return state.equals(STATE_BLOCKED);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOtherClientId() {
        return otherClientId;
    }

    public void setOtherClientId(String otherClientId) {
        this.otherClientId = otherClientId;
    }

    @Nullable
    public String getState() {
        return state;
    }

    public void setState(String state) {
        // TODO: validate state here (isValidState)
        this.state = state;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    @JsonIgnore
    public void updateWith(TalkRelationship r) {
        this.setClientId(r.getClientId());
        this.setOtherClientId(r.getOtherClientId());
        this.setState(r.getState());
        this.setLastChanged(r.getLastChanged());
    }

}
