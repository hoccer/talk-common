package com.hoccer.talk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "groupMember")
public class TalkGroupMember {

    public static final String STATE_NONE          = "none";
    public static final String STATE_INVITED       = "invited";
    public static final String STATE_JOINED        = "joined";
    public static final String STATE_GROUP_REMOVED = "groupRemoved";

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_MEMBER = "member";

    public static boolean isValidRole(String role) {
        return role.equals(ROLE_ADMIN) || role.equals(ROLE_MEMBER);
    }

    public static final String[] ACTIVE_STATES = {
            TalkGroupMember.STATE_INVITED,
            TalkGroupMember.STATE_JOINED
    };

    private String _id;

    @DatabaseField(generatedId = true)
    private long memberId;

    @DatabaseField
    String groupId;

    @DatabaseField
    String clientId;

    @DatabaseField
    String role;

    @DatabaseField
    String state;

    @DatabaseField
    String memberKeyId;

    @DatabaseField
    String encryptedGroupKey;

    @DatabaseField
    String sharedKeyId;

    @DatabaseField
    String sharedKeyIdSalt;

    @DatabaseField
    Date sharedKeyDate;

    @DatabaseField
    String keySupplier;

    @DatabaseField
    Date lastChanged;

    public TalkGroupMember() {
        this.role = ROLE_MEMBER;
        this.state = STATE_NONE;
    }

    @JsonIgnore
    public boolean isAdmin() {
        return this.state.equals(STATE_JOINED) && this.role.equals(ROLE_ADMIN);
    }

    @JsonIgnore
    public boolean isMember() {
        return this.state.equals(STATE_JOINED) && (this.role.equals(ROLE_ADMIN) || this.role.equals(ROLE_MEMBER));
    }

    @JsonIgnore
    public boolean isJoined() {
        return this.state.equals(STATE_JOINED);
    }

    @JsonIgnore
    public boolean isInvited() {
        return this.state.equals(STATE_INVITED);
    }

    @JsonIgnore
    public boolean isJoinedOrInvited() {
        return isJoined() || isInvited();
    }

    @JsonIgnore
    public boolean isGroupRemoved() {
        return this.state.equals(STATE_GROUP_REMOVED);
    }

    @JsonIgnore
    public boolean isInvolved() {
        return !this.state.equals(STATE_NONE);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        // TODO: validate state
        this.state = state;
    }

    public String getMemberKeyId() {
        return memberKeyId;
    }

    public void setMemberKeyId(String memberKeyId) {
        this.memberKeyId = memberKeyId;
    }

    public String getEncryptedGroupKey() {
        return encryptedGroupKey;
    }

    public void setEncryptedGroupKey(String encryptedGroupKey) {
        this.encryptedGroupKey = encryptedGroupKey;
    }

    public Date getLastChanged() {
        return lastChanged;
    }

    public void setLastChanged(Date lastChanged) {
        this.lastChanged = lastChanged;
    }

    public String getSharedKeyId() {
        return sharedKeyId;
    }

    public void setSharedKeyId(String sharedKeyId) {
        this.sharedKeyId = sharedKeyId;
    }

    public String getSharedKeyIdSalt() {
        return sharedKeyIdSalt;
    }

    public void setSharedKeyIdSalt(String sharedKeyIdSalt) {
        this.sharedKeyIdSalt = sharedKeyIdSalt;
    }

    public String getKeySupplier() {
        return keySupplier;
    }

    public void setKeySupplier(String keySupplier) {
        this.keySupplier = keySupplier;
    }

    public Date getSharedKeyDate() {
        return sharedKeyDate;
    }

    public void setSharedKeyDate(Date sharedKeyDate) {
        this.sharedKeyDate = sharedKeyDate;
    }

    @JsonIgnore
    public void updateWith(TalkGroupMember m) {
        this.setClientId(m.getClientId());
        this.setGroupId(m.getGroupId());
        this.setRole(m.getRole());
        this.setState(m.getState());
        this.setMemberKeyId(m.getMemberKeyId());
        this.setEncryptedGroupKey(m.getEncryptedGroupKey());
        this.setLastChanged(m.getLastChanged());
        this.setSharedKeyId(m.getSharedKeyId());
        this.setSharedKeyIdSalt(m.getSharedKeyIdSalt());
        this.setKeySupplier(m.getKeySupplier());
        this.setSharedKeyDate(m.getSharedKeyDate());
    }

    // only copies the field where a foreign member is interested in
    @JsonIgnore
    public void foreignUpdateWith(TalkGroupMember m) {
        this.setClientId(m.getClientId());
        this.setGroupId(m.getGroupId());
        this.setRole(m.getRole());
        this.setState(m.getState());
        //this.setMemberKeyId(m.getMemberKeyId());
        //this.setEncryptedGroupKey(m.getEncryptedGroupKey());
        this.setLastChanged(m.getLastChanged());
        //this.setSharedKeyId(m.getSharedKeyId());
        //this.setSharedKeyIdSalt(m.getSharedKeyIdSalt());
        //this.setKeySupplier(m.getKeySupplier());
        //this.setSharedKeyDate(m.getSharedKeyDate());
    }

    @JsonIgnore
    public void trashPrivate() {
        this.setSharedKeyDate(null);
        this.setSharedKeyIdSalt(null);
        this.setMemberKeyId(null);
        this.setSharedKeyId(null);
        this.setEncryptedGroupKey(null);
        this.setKeySupplier(null);
    }

    }
